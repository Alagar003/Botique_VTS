// import React, { createContext, useState, useEffect, useContext } from "react";
//
// export const CartContext = createContext();
//
// export const CartProvider = ({ children }) => {
//     const [cart, setCart] = useState({ items: [], totalPrice: 0 });
//     const [user, setUser ] = useState(null);
//     const [token, setToken] = useState(null);  // Store token separately
//
//     // Utility function to validate ObjectId format
//     const isValidObjectId = (id) => {
//         return /^[0-9a-fA-F]{24}$/.test(id);
//     };
//
//     useEffect(() => {
//         const storedToken = localStorage.getItem("token");
//         setToken(storedToken); // Store token in state
//         if (storedToken) {
//             fetchUserDetails(storedToken);
//         }
//     }, []);
//
//     const fetchUserDetails = async (storedToken) => {
//         try {
//             const response = await fetch("http://localhost:8081/api/users/user", {
//                 method: "GET",
//                 headers: {
//                     Authorization: `Bearer ${storedToken}`,
//                     "Content-Type": "application/json",
//                 },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch user details");
//
//             const data = await response.json();
//             console.log("✅ Updated User State:", data);
//
//             setUser (data);
//             fetchCartDetails(data.id, storedToken); // Fetch cart details after setting user
//         } catch (error) {
//             console.error("❌ Error fetching user details:", error);
//         }
//     };
//
//     const fetchCartDetails = async (userId, storedToken) => {
//         if (!userId || !storedToken) return;
//
//         try {
//             const response = await fetch(`http://localhost:8081/api/cart/${userId}`, {
//                 headers: { Authorization: `Bearer ${storedToken}` },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch cart");
//
//             const cartData = await response.json();
//             console.log("✅ Fetched Cart Data:", cartData);
//             setCart({
//                 items: cartData.cartItems || [],
//                 totalPrice: cartData.totalPrice || 0,
//             });
//         } catch (error) {
//             console.error("❌ Error fetching cart:", error);
//         }
//     };
//
//     const addToCart = async (productId) => {
//         const storedToken = localStorage.getItem("token");
//         const userId = user ? user.id : null; // Use the user ID from the state
//
//         if (!storedToken) {
//             console.warn("⚠️ Token is missing");
//             return;
//         }
//
//         console.log("User  ID:", userId); // Log the user ID for debugging
//         console.log("Product ID:", productId); // Log the product ID for debugging
//
//         if (!isValidObjectId(userId)) {
//             console.error("❌ Invalid user ID format");
//             alert("Invalid user ID format");
//             return;
//         }
//
//         if (!isValidObjectId(productId)) {
//             console.error("❌ Invalid product ID format");
//             alert("Invalid product ID format");
//             return;
//         }
//
//         const requestBody = { userId, productId, quantity: 1 }; // Include userId in the request body
//         console.log("📦 Sending addToCart request:", requestBody);
//
//         try {
//             const response = await fetch("http://localhost:8081/api/cart/add", {
//                 method: "POST",
//                 headers: {
//                     Authorization: `Bearer ${storedToken}`,
//                     "Content-Type": "application/json",
//                 },
//                 body: JSON.stringify(requestBody),
//             });
//
//             // Check if the response is OK
//             if (!response.ok) {
//                 const errorData = await response.json(); // Parse the error response as JSON
//                 console.error("❌ Failed to update cart:", errorData);
//                 alert(errorData.message || "Failed to add to cart"); // Show error message
//                 return;
//             }
//
//             const responseData = await response.json(); // Parse the successful response
//             console.log("✅ Item added to cart:", responseData);
//             setCart(prevCart => ({
//                 items: responseData.cartItems || [], // Update cart items
//                 totalPrice: responseData.totalPrice || 0 // Update total price
//             }));
//         } catch (error) {
//             console.error("❌ Error adding to cart:", error);
//             alert("An error occurred while adding to the cart.");
//         }
//     };
//     return (
//         <CartContext.Provider value={{ cart, setCart, addToCart }}>
//             {children}
//         </CartContext.Provider>
//     );
// };
//
// export const useCart = () => useContext(CartContext);
//
//
// import React, { createContext, useState, useEffect, useContext } from "react";
//
// export const CartContext = createContext();
//
// export const CartProvider = ({ children }) => {
//     const [cart, setCart] = useState({ items: [], totalPrice: 0 });
//     const [user, setUser ] = useState(null);
//     const [token, setToken] = useState(null); // Store token separately
//     const [loading, setLoading] = useState(true); // Loading state
//     const [error, setError] = useState(null); // Error state
//
//     // Utility function to validate ObjectId format
//     const isValidObjectId = (id) => {
//         return /^[0-9a-fA-F]{24}$/.test(id);
//     };
//
//     useEffect(() => {
//         const storedToken = localStorage.getItem("token");
//         setToken(storedToken); // Store token in state
//         if (storedToken) {
//             fetchUserDetails(storedToken);
//         } else {
//             setLoading(false); // No token, stop loading
//         }
//     }, []);

//     // const fetchUserDetails = async (storedToken) => {
//     //     try {
//     //         const response = await fetch("http://localhost:8081/api/users/user", {
//     //             method: "GET",
//     //             headers: {
//     //                 Authorization: `Bearer ${storedToken}`,
//     //                 "Content-Type": "application/json",
//     //             },
//     //         });
//     //
//     //         if (!response.ok) throw new Error("Failed to fetch user details");
//     //
//     //         const data = await response.json();
//     //         console.log("✅ Updated User State:", data);
//     //         setUser (data);
//     //         fetchCartDetails(data.id, storedToken); // Fetch cart details after setting user
//     //     } catch (error) {
//     //         console.error("❌ Error fetching user details:", error);
//     //         setError(error.message); // Set error message
//     //     } finally {
//     //         setLoading(false); // Stop loading
//     //     }
//     // };
//
//
//     const fetchUserDetails = async () => {
//         try {
//             const response = await fetch("http://localhost:8081/api/user/me", {
//                 headers: {
//                     Authorization: `Bearer ${token}`,
//                 },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch user");
//
//             const data = await response.json();
//             console.log("✅ User Data:", data);
//
//             if (data && data._id) { // Check if API returns _id
//                 setUserId(data._id);
//                 setUser(data);
//             } else {
//                 console.error("User ID is missing in API response!");
//             }
//         } catch (error) {
//             console.error("Error fetching user:", error);
//         }
//     };
//
//
//
//
//     const fetchCartDetails = async () => {
//         console.log("Fetching cart for userId:", userId);
//
//         if (!userId) {
//             console.error("❌ User ID is null! Cannot fetch cart.");
//             return;
//         }
//
//         try {
//             const response = await fetch(`http://localhost:8081/api/cart/${userId}`);
//             if (!response.ok) throw new Error("Failed to fetch cart");
//
//             const data = await response.json();
//             console.log("✅ Cart Data:", data);
//             setCart(data);
//         } catch (error) {
//             console.error("Error fetching cart:", error);
//         }
//     };
//
//
//
//
//
//
//     // const addToCart = async (productId) => {
//     //     const storedToken = localStorage.getItem("token");
//     //     const userId = user ? user.id : null; // Use the user ID from the state
//     //
//     //     if (!storedToken) {
//     //         console.warn("⚠️ Token is missing");
//     //         return;
//     //     }
//     //
//     //     console.log("User  ID:", userId); // Log the user ID for debugging
//     //     console.log("Product ID:", productId); // Log the product ID for debugging
//     //
//     //     if (!isValidObjectId(userId)) {
//     //         console.error("❌ Invalid user ID format");
//     //         alert("Invalid user ID format");
//     //         return;
//     //     }
//     //
//     //     if (!isValidObjectId(productId)) {
//     //         console.error("❌ Invalid product ID format");
//     //         alert("Invalid product ID format");
//     //         return;
//     //     }
//     //
//     //     const requestBody = { userId, productId, quantity: 1 }; // Include userId in the request body
//     //     console.log("📦 Sending addToCart request:", requestBody);
//     //
//     //     try {
//     //         const response = await fetch("http://localhost:8081/api/cart/add", {
//     //             method: "POST",
//     //             headers: {
//     //                 Authorization: `Bearer ${storedToken}`,
//     //                 "Content-Type": "application/json",
//     //             },
//     //             body: JSON.stringify(requestBody),
//     //         });
//     //
//     //         // Check if the response is OK
//     //         if (!response.ok) {
//     //             const errorData = await response.json(); // Parse the error response as JSON
//     //             console.error("❌ Failed to update cart:", errorData);
//     //             alert(errorData.message || "Failed to add to cart"); // Show error message
//     //             return;
//     //         }
//     //
//     //         const responseData = await response.json(); // Parse the successful response
//     //         console.log("✅ Item added to cart:", responseData);
//     //         setCart(prevCart => ({
//     //             items: responseData.cartItems || [], // Update cart items
//     //             totalPrice: responseData.totalPrice || 0 // Update total price
//     //         }));
//     //     } catch (error) {
//     //         console.error("❌ Error adding to cart:", error);
//     //         alert("An error occurred while adding to the cart.");
//     //     }
//     // };
//
//
//     const addToCart = async (productId) => {
//         const storedToken = localStorage.getItem("token");
//
//         if (!storedToken) {
//             console.warn("⚠️ Token is missing");
//             return;
//         }
//
//         console.log("User ID:", userId); // Log user ID
//         console.log("Product ID:", productId); // Log product ID
//
//         if (!userId) {
//             console.error("❌ User ID is missing");
//             alert("User ID is missing");
//             return;
//         }
//
//         const requestBody = { userId, productId, quantity: 1 };
//         console.log("📦 Sending addToCart request:", requestBody);
//
//         try {
//             const response = await fetch("http://localhost:8081/api/cart/add", {
//                 method: "POST",
//                 headers: {
//                     Authorization: `Bearer ${storedToken}`,
//                     "Content-Type": "application/json",
//                 },
//                 body: JSON.stringify(requestBody),
//             });
//
//             if (!response.ok) {
//                 const errorData = await response.json();
//                 console.error("❌ Failed to update cart:", errorData);
//                 alert(errorData.message || "Failed to add to cart");
//                 return;
//             }
//
//             const responseData = await response.json();
//             console.log("✅ Item added to cart:", responseData);
//             setCart({
//                 items: responseData.cartItems || [],
//                 totalPrice: responseData.totalPrice || 0,
//             });
//         } catch (error) {
//             console.error("❌ Error adding to cart:", error);
//             alert("An error occurred while adding to the cart.");
//         }
//     };
//
//
//
//
//     return (
//         <CartContext.Provider value={{ cart, setCart, addToCart, loading, error }}>
//             {children}
//         </CartContext.Provider>
//     );
// };
//
// export const useCart = () => useContext(CartContext);

//
// import React, { createContext, useState, useEffect, useContext } from "react";
//
// export const CartContext = createContext();
//
// export const CartProvider = ({ children }) => {
//     const [cart, setCart] = useState({ items: [], totalPrice: 0 });
//     const [user, setUser] = useState(null);
//     const [userId, setUserId] = useState(null);
//     const [token, setToken] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//
//     useEffect(() => {
//         const storedToken = localStorage.getItem("token");
//         if (storedToken) {
//             setToken(storedToken);
//             fetchUserDetails(storedToken);
//         } else {
//             setLoading(false);
//         }
//     }, []);
//
//     const fetchUserDetails = async () => {
//         try {
//             const response = await fetch("http://localhost:8081/api/users/user", {
//                 method: "GET",
//                 headers: {
//                     "Content-Type": "application/json",
//                     Authorization: `Bearer ${localStorage.getItem("token")}`,
//                 },
//             });
//
//             const data = await response.json();
//             console.log("✅ User Data:", data);
//
//             if (!data || !data.id) {
//                 console.error("User ID is missing in API response!");
//                 return;
//             }
//
//             setUser(data);  // Assuming setUser updates global state
//         } catch (error) {
//             console.error("❌ Error fetching user:", error);
//         }
//     };
//
//
//     const fetchCartDetails = async (userId, storedToken) => {
//         if (!userId) {
//             console.error("❌ User ID is null! Cannot fetch cart.");
//             return;
//         }
//
//         try {
//             const response = await fetch(`http://localhost:8081/api/cart/${userId}`, {
//                 headers: {
//                     Authorization: `Bearer ${storedToken}`,
//                 },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch cart");
//
//             const data = await response.json();
//             console.log("✅ Cart Data:", data);
//             setCart(data);
//         } catch (error) {
//             console.error("❌ Error fetching cart:", error);
//             setError(error.message);
//         }
//     };
//
//     const addToCart = async (productId) => {
//         if (!token) {
//             console.warn("⚠️ Token is missing");
//             return;
//         }
//
//         if (!userId) {
//             console.error("❌ User ID is missing");
//             alert("User ID is missing");
//             return;
//         }
//
//         console.log("📦 Adding to cart:", { userId, productId });
//
//         try {
//             const response = await fetch("http://localhost:8081/api/cart/add", {
//                 method: "POST",
//                 headers: {
//                     Authorization: `Bearer ${token}`,
//                     "Content-Type": "application/json",
//                 },
//                 body: JSON.stringify({ userId, productId, quantity: 1 }),
//             });
//
//             if (!response.ok) {
//                 const errorData = await response.json();
//                 console.error("❌ Failed to update cart:", errorData);
//                 alert(errorData.message || "Failed to add to cart");
//                 return;
//             }
//
//             const responseData = await response.json();
//             console.log("✅ Item added to cart:", responseData);
//             setCart({
//                 items: responseData.cartItems || [],
//                 totalPrice: responseData.totalPrice || 0,
//             });
//         } catch (error) {
//             console.error("❌ Error adding to cart:", error);
//             alert("An error occurred while adding to the cart.");
//         }
//     };
//
//     return (
//         <CartContext.Provider value={{ cart, setCart, addToCart, loading, error }}>
//             {children}
//         </CartContext.Provider>
//     );
// };
//
// export const useCart = () => useContext(CartContext);

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
        try {
            const response = await fetch("http://localhost:8081/api/users/user", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            });

            if (!response.ok) {
                throw new Error("Failed to fetch user details");
            }

            const data = await response.json();
            if (!data || !data.id) {
                throw new Error("User ID is missing in API response!");
            }

            console.log("User details fetched:", data);  // Log user data
            setUser(data);
            setUserId(data.id);  // Ensure userId is set correctly
            await fetchCartDetails(data.id, token);
            setUserId(data.id); // Ensure it’s set after fetching

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
