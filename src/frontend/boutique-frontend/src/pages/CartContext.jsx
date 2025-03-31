
import React, { createContext, useState, useEffect, useContext } from "react";
import axios from "axios";

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState({ items: [], totalPrice: 0 });
    const [user, setUser] = useState(null);
    const [userId, setUserId] = useState(localStorage.getItem("userId") || null);
    const [token, setToken] = useState(localStorage.getItem("token") || null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const storedUserId = localStorage.getItem("userId");
        if (storedUserId) {
            setUserId(storedUserId);
        }

        if (token) {
            fetchUserDetails();
        } else {
            setLoading(false);
        }
    }, [token]);

    const fetchUserDetails = async () => {
        if (!token) {
            console.error("❌ No token found!");
            setError("Authentication token is missing.");
            setLoading(false);
            return;
        }

        try {
            const response = await fetch("http://localhost:8081/api/users/user", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            });

            if (!response.ok) {
                throw new Error(`Failed to fetch user details, status: ${response.status}`);
            }

            const data = await response.json();
            if (!data || !data.id) {
                throw new Error("User ID is missing in API response!");
            }

            console.log("✅ User details fetched:", data);
            setUser(data);
            setUserId(data.id);
            localStorage.setItem("userId", data.id);

            await fetchCartDetails(data.id, token);
        } catch (error) {
            console.error("❌ Error fetching user:", error);
            setError(error.message);
        } finally {
            setLoading(false);
        }
    };

    const fetchCartDetails = async (userId) => {
        try {
            const response = await fetch(`/api/cart/${userId}`);
            const cartData = await response.json();

            if (!cartData.userId) {
                console.error("Cart API returned null userId, check backend");
            }

            setCart(cartData);
        } catch (error) {
            console.error("Error fetching cart:", error);
        }
    };

    const handleAddToCart = async (productId, quantity) => {
        const storedUserId = localStorage.getItem("userId"); // Ensure userId is retrieved
        const storedToken = localStorage.getItem("token"); // Ensure token is retrieved

        if (!storedUserId || !storedToken) {
            alert("❌ You need to log in before adding to the cart.");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8081/api/cart/add`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${storedToken}`,
                },
                body: JSON.stringify({ userId: storedUserId, productId, quantity }),
            });

            if (!response.ok) {
                throw new Error("Failed to add product to cart");
            }

            const updatedCart = await response.json();
            console.log("✅ Product added to cart:", updatedCart);
            setCart({
                items: Array.isArray(updatedCart.items) ? updatedCart.items : [],
                totalPrice: updatedCart.totalPrice || 0,
            });
        } catch (error) {
            console.error("❌ Error adding to cart:", error);
            alert("Stock Not Available or Invalid Product");
            setError(error.message);
        }
    };

    return (
        <CartContext.Provider value={{ cart, setCart, loading, error, user, userId, handleAddToCart }}>
            {children}
        </CartContext.Provider>
    );
};

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context) {
        throw new Error("useCart must be used within a CartProvider");
    }
    return context;
};
