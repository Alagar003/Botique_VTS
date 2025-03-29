
import React, { createContext, useState, useEffect, useContext } from "react";
import axios from "axios";

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState({ items: [], totalPrice: 0 });
    const [user, setUser] = useState(null);
    const [userId, setUserId] = useState(localStorage.getItem("userId"));
    const [token, setToken] = useState(localStorage.getItem("token") || null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch user and cart data when token changes
    useEffect(() => {
        console.log("Token is: ", token);  // Log token to inspect
        console.log(userId);
        if (token) {
            fetchUserDetails();
        } else {
            console.log("❌ Token is missing");
            setLoading(false);
        }
    }, [token]);
    const fetchUserDetails = async () => {
        const token = localStorage.getItem("token"); // Ensure token is stored properly
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
                    Authorization: `Bearer ${token}`, // Ensure correct format
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

            // Fetch cart details only if userId exists
            if (data.id) {
                await fetchCartDetails(data.id, token);
            }

        } catch (error) {
            console.error("❌ Error fetching user:", error);
            setError(error.message);
        } finally {
            setLoading(false);
        }
    };


    const fetchCartDetails = async () => {
        const storedUser = localStorage.getItem("user");
        if (!storedUser) {
            console.warn("User not found in localStorage");
            return;
        }

        const user = JSON.parse(storedUser);
        if (!user.id) {
            console.error("User ID is missing in localStorage");
            return;
        }

        try {
            const response = await fetch(`/api/cart/${user.id}`);
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
        if (!userId || !token) {
            console.error("❌ Cannot add to cart: Missing userId or token.");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8081/api/cart/add`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({ userId, productId, quantity }),
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
            alert("Stock Not Available or Invalid Product")
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

// import React, { createContext, useState, useEffect, useContext } from "react";
//
// export const CartContext = createContext();
//
// export const CartProvider = ({ children }) => {
//     const [cart, setCart] = useState({ items: [], totalPrice: 0 });
//     const [user, setUser] = useState(null);
//     const [userId, setUserId] = useState(null);
//     const [token, setToken] = useState(localStorage.getItem("token") || null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//
//     // Fetch user details when token changes
//     useEffect(() => {
//         if (token) {
//             fetchUserDetails();
//         } else {
//             setLoading(false);
//         }
//     }, [token]);
//
//     const fetchUserDetails = async () => {
//         try {
//             const response = await fetch("http://localhost:8081/api/users/user", {
//                 method: "GET",
//                 headers: {
//                     "Content-Type": "application/json",
//                     Authorization: `Bearer ${token}`,
//                 },
//             });
//
//             if (!response.ok) {
//                 throw new Error("Failed to fetch user details");
//             }
//
//             const data = await response.json();
//             setUser(data);
//             setUserId(data.id);
//             fetchCartDetails(data.id, token);
//         } catch (error) {
//             console.error("Error fetching user:", error);
//             setError(error.message);
//         } finally {
//             setLoading(false);
//         }
//     };
//
//     const fetchCartDetails = async (userId, token) => {
//         if (!userId || !token) return;
//
//         try {
//             const response = await fetch(`http://localhost:8081/api/cart/${userId}`, {
//                 method: "GET",
//                 headers: { Authorization: `Bearer ${token}` },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch cart");
//
//             const data = await response.json();
//             setCart({
//                 items: Array.isArray(data.items) ? data.items : [],
//                 totalPrice: data.totalPrice || 0,
//             });
//         } catch (error) {
//             console.error("Error fetching cart:", error);
//             setError(error.message);
//         }
//     };
//
//     // ✅ Define handleAddToCart function
//     const handleAddToCart = async (productId, quantity) => {
//         if (!token) {
//             console.error("❌ Cannot add to cart: Missing token.");
//             return;
//         }
//
//         try {
//             const response = await fetch("http://localhost:8081/api/cart/add", {
//                 method: "POST",
//                 headers: {
//                     "Content-Type": "application/json",
//                     Authorization: `Bearer ${token}`, // Send token correctly
//                 },
//                 body: JSON.stringify({ productId, quantity }), // Remove userId (handled by backend)
//             });
//
//             if (!response.ok) throw new Error("Failed to add product to cart");
//
//             const updatedCart = await response.json();
//
//             // Update cart state
//             setCart((prevCart) => ({
//                 items: Array.isArray(updatedCart.items) ? updatedCart.items : prevCart.items || [],
//                 totalPrice: updatedCart.totalPrice || prevCart.totalPrice || 0,
//             }));
//
//         } catch (error) {
//             console.error("❌ Error adding to cart:", error);
//             setError(error.message);
//         }
//     };
//
//     return (
//         <CartContext.Provider value={{ cart, setCart, loading, error, user, userId, handleAddToCart }}>
//             {children}
//         </CartContext.Provider>
//     );
// };
//
// // ✅ Custom Hook to Use Cart Context
// export const useCart = () => {
//     const context = useContext(CartContext);
//     if (!context) {
//         throw new Error("useCart must be used within a CartProvider");
//     }
//     return context;
// };
