// import { useState, useEffect } from "react";
// import axios from "axios";
//
// const Cart = () => {
//     const [cart, setCart] = useState([]);
//     const [user, setUser] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//     const [cartLoading, setCartLoading] = useState(false);
//
//     useEffect(() => {
//         const fetchUser = async () => {
//             const token = localStorage.getItem("token");
//             if (!token) {
//                 setLoading(false);
//                 return;
//             }
//
//             try {
//                 const response = await axios.get("http://localhost:8081/api/users/user", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//
//                 console.log("👤 User Data:", response.data);
//
//                 if (response.data.id) {
//                     setUser(response.data); // Update user state
//                     fetchCart(response.data.id, token);
//                 } else {
//                     console.error("❌ User ID is missing from response");
//                 }
//             } catch (error) {
//                 console.error("Error fetching user data:", error);
//                 setError("Failed to fetch user data");
//             } finally {
//                 setLoading(false);
//             }
//         };
//
//         fetchUser();
//     }, []);
//
//     const fetchCart = async (userId, token) => {
//         if (!userId) return console.error("⚠️ User ID is missing");
//
//         try {
//             setTimeout(async () => { // ✅ Slight delay to ensure backend updates first
//                 const response = await axios.get(`http://localhost:8081/api/cart/${userId}`, {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//
//                 console.log("🛒 Cart API Response:", response.data);
//
//                 if (!response.data.cartItems) {
//                     console.error("⚠️ Cart data missing `cartItems`.");
//                     setCart([]);
//                 } else {
//                     // ✅ Ensure price is correctly set
//                     setCart(
//                         response.data.cartItems.map(item => ({
//                             ...item,
//                             price: item.product?.price || item.price || 0 // ✅ Ensure price is never undefined
//                         }))
//                     );
//                 }
//             }, 500); // ✅ Wait 500ms before fetching
//         } catch (error) {
//             console.error("❌ Error fetching cart data:", error.response?.data || error.message);
//             setError("Failed to fetch cart data");
//         }
//     };
//
//
//
//
//     const updateCart = async (productId, action) => {
//         const token = localStorage.getItem("token");
//         if (!token) {
//             console.error("⚠️ No token found, user might not be logged in");
//             return;
//         }
//
//         // ✅ Debugging: Check if productId is valid
//         console.log("🛒 Updating cart with productId:", productId);
//
//         if (!productId || typeof productId !== "string" || productId.length !== 24) {
//             console.error("❌ Invalid productId:", productId);
//             return;
//         }
//
//         const payload = { productId, action };
//
//         console.log("Sending updateCart request with payload:", payload);
//
//         try {
//             const response = await axios.post("http://localhost:8081/api/cart/updateQuantity", payload, {
//                 headers: {
//                     "Authorization": `Bearer ${token}`,
//                     "Content-Type": "application/json",
//                 },
//             });
//
//             console.log("✅ Cart updated successfully:", response.data);
//         } catch (error) {
//             console.error("❌ Error updating cart:", error.response?.data || error.message);
//         }
//     };
//
//
//
//
//
//
//     const handleRemoveFromCart = async (productId) => {
//         if (!productId) return;
//         const token = localStorage.getItem("token");
//         if (!token) return;
//
//         setCartLoading(true);
//         try {
//             const response = await axios.delete(
//                 `http://localhost:8081/api/cart/remove/${productId}`,
//                 { headers: { Authorization: `Bearer ${token}` } }
//             );
//             setCart(response.data.cartItems || []);
//         } catch (err) {
//             setError("Failed to remove item");
//         } finally {
//             setCartLoading(false);
//         }
//     };
//
//     return (
//         <div>
//             <h1>Shopping Cart</h1>
//             {loading && <p>Loading...</p>}
//             {error && <p style={{ color: "red" }}>{error}</p>}
//             {cartLoading && <p>Updating cart...</p>}
//             {!loading && !error && cart.length === 0 && <p>Your cart is empty.</p>}
//
//             {cart.map((item, index) => (
//                 <div key={item.productId || index}>
//                     <img src={item.imageUrl || 'placeholder.jpg'} alt={item.productName || 'Product Image'}
//                          style={{width: "100px", height: "100px"}}/>
//                     <h2>{item.productName || "Product Name"}</h2>
//                     <p>Price per Item: ₹{item.price ? item.price.toFixed(2) : "0.00"}</p>
//                     <p>Total for this Item: ₹{(item.price * (item.quantity || 0)).toFixed(2)}</p>
//
//
//                     <div key={item.productId || index}>
//                         <button onClick={() => updateCart(item.productId, "decrease")}>-</button>
//                         <span> Quantity: {item.quantity || 0} </span>
//                         <button onClick={() => updateCart(item.productId, "increase")}>+</button>
//                     </div>
//                     <button onClick={() => handleRemoveFromCart(item.productId)}>Remove from Cart</button>
//                 </div>
//             ))}
//         </div>
//     );
// };
//
// export default Cart;


// import { useState, useEffect } from "react";
// import axios from "axios";
//
// const Cart = () => {
//     const [cart, setCart] = useState([]);
//     const [user, setUser] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//     const [cartLoading, setCartLoading] = useState(false);
//
//     useEffect(() => {
//         const fetchUser = async () => {
//             const token = localStorage.getItem("token");
//             if (!token) {
//                 setLoading(false);
//                 return;
//             }
//
//             try {
//                 const response = await axios.get("http://localhost:8081/api/users/user", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//
//                 console.log("👤 User Data:", response.data);
//
//                 if (response.data.id) {
//                     setUser(response.data);
//                     fetchCart(response.data.id, token);
//                 } else {
//                     console.error("❌ User ID is missing from response");
//                 }
//             } catch (error) {
//                 console.error("Error fetching user data:", error);
//                 setError("Failed to fetch user data");
//             } finally {
//                 setLoading(false);
//             }
//         };
//
//         fetchUser();
//     }, []);
//
//     const fetchCart = async (userId, token) => {
//         if (!userId) return console.error("⚠️ User ID is missing");
//
//         try {
//             setTimeout(async () => {
//                 const response = await axios.get(`http://localhost:8081/api/cart/${userId}`, {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//
//                 console.log("🛒 Raw Cart API Response:", response.data);
//
//                 if (!response.data.cartItems) {
//                     console.error("⚠️ Cart data missing `cartItems`.");
//                     setCart([]);
//                 } else {
//                     setCart(
//                         response.data.cartItems.map(item => ({
//                             ...item,
//                             productId: item.product?._id || item.productId, // Ensure productId is correctly set
//                             price: item.product?.price || item.price || 0 // Ensure price is never undefined
//                         }))
//                     );
//                 }
//             }, 500);
//         } catch (error) {
//             console.error("❌ Error fetching cart data:", error.response?.data || error.message);
//             setError("Failed to fetch cart data");
//         }
//     };
//
//     const updateCart = async (productId, action) => {
//         const token = localStorage.getItem("token"); // Assuming you're storing a token
//         console.log("Auth Token:", token); // Debugging
//
//         try {
//             const response = await axios.post(
//                 "http://localhost:8081/api/cart/updateQuantity",
//                 { productId, action },
//                 {
//                     headers: {
//                         Authorization: `Bearer ${token}`, // Include the token
//                         "Content-Type": "application/json",
//                     },
//                 }
//             );
//             console.log("Cart updated successfully:", response.data);
//         } catch (error) {
//             console.error("❌ Error updating cart:", error.response?.data || error.message);
//         }
//     };
//
//
//
//
//     const handleRemoveFromCart = async (productId) => {
//         if (!productId) return;
//         const token = localStorage.getItem("token");
//         if (!token) return;
//
//         setCartLoading(true);
//         try {
//             const response = await axios.delete(
//                 `http://localhost:8081/api/cart/remove/${productId}`,
//                 { headers: { Authorization: `Bearer ${token}` } }
//             );
//             setCart(response.data.cartItems || []);
//         } catch (err) {
//             setError("Failed to remove item");
//         } finally {
//             setCartLoading(false);
//         }
//     };
//
//     return (
//         <div>
//             <h1>Shopping Cart</h1>
//             {loading && <p>Loading...</p>}
//             {error && <p style={{ color: "red" }}>{error}</p>}
//             {cartLoading && <p>Updating cart...</p>}
//             {!loading && !error && cart.length === 0 && <p>Your cart is empty.</p>}
//
//             {cart.map((item, index) => {
//                 console.log("🔍 Checking Cart Item:", item);
//                 console.log("🆔 Product ID:", item.productId);
//
//                 return (
//                     <div key={item.productId || index}>
//                         <img src={item.imageUrl || 'placeholder.jpg'} alt={item.productName || 'Product Image'}
//                              style={{width: "100px", height: "100px"}}/>
//                         <h2>{item.productName || "Product Name"}</h2>
//                         <p>Price per Item: ₹{item.price ? item.price.toFixed(2) : "0.00"}</p>
//                         <p>Total for this Item: ₹{(item.price * (item.quantity || 0)).toFixed(2)}</p>
//
//                         <div>
//                             <button onClick={() => updateCart(item.productId, "decrease")}>-</button>
//                             <span> Quantity: {item.quantity || 0} </span>
//                             <button onClick={() => updateCart(item.productId, "increase")}>+</button>
//                         </div>
//                         <button onClick={() => handleRemoveFromCart(item.productId)}>Remove from Cart</button>
//                     </div>
//                 );
//             })}
//         </div>
//     );
// };
//
// export default Cart;

//
// import React, { useEffect, useState } from "react";
// import axios from "axios";
//
// const Cart = () => {
//     const [cart, setCart] = useState(null);
//     const [loading, setLoading] = useState(true);
//
//     useEffect(() => {
//         fetchCart();
//     }, []);
//
//     const fetchCart = async () => {
//         try {
//             const response = await axios.get("/api/cart");  // Adjust API URL if needed
//             setCart(response.data);
//             setLoading(false);
//         } catch (error) {
//             console.error("Error fetching cart:", error);
//             setLoading(false);
//         }
//     };
//
//     const updateQuantity = async (productId, action) => {
//         try {
//             const response = await axios.post("/api/cart/updateQuantity", { productId, action });
//             setCart(response.data); // ✅ Update the cart with the latest response
//         } catch (error) {
//             console.error("Error updating quantity:", error.response?.data?.message || error.message);
//         }
//     };
//
//     if (loading) return <p>Loading cart...</p>;
//     if (!cart || cart.items.length === 0) return <p>Your cart is empty.</p>;
//
//     return (
//         <div>
//             <h2>Your Cart</h2>
//             <ul>
//                 {cart.items.map((item) => (
//                     <li key={item.product.id}>
//                         <p>{item.product.name} - ₹{item.product.price}</p>
//                         <p>Quantity: {item.quantity}</p>
//                         <button onClick={() => updateQuantity(item.product.id, "increase")}>+</button>
//                         <button onClick={() => updateQuantity(item.product.id, "decrease")} disabled={item.quantity === 1}>-</button>
//                     </li>
//                 ))}
//             </ul>
//             <h3>Total Price: ₹{cart.totalPrice}</h3>
//         </div>
//     );
// };
//
// export default Cart;
//
//
// import { useState, useEffect } from "react";
// import axios from "axios";
//
// const Cart = () => {
//     const [cart, setCart] = useState([]);
//     const [user, setUser] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//     const [cartLoading, setCartLoading] = useState(false);
//
//     useEffect(() => {
//         const fetchUser = async () => {
//             const token = localStorage.getItem("token");
//             if (!token) {
//                 setLoading(false);
//                 return;
//             }
//
//             try {
//                 const response = await axios.get("http://localhost:8081/api/users/user", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//
//                 console.log("👤 User Data:", response.data);
//
//                 if (response.data.id) {
//                     setUser(response.data);
//                     fetchCart(response.data.id, token);
//                 } else {
//                     console.error("❌ User ID is missing from response");
//                 }
//             } catch (error) {
//                 console.error("Error fetching user data:", error);
//                 setError("Failed to fetch user data");
//             } finally {
//                 setLoading(false);
//             }
//         };
//
//         fetchUser();
//     }, []);
//
//     const fetchCart = async (userId, token) => {
//         if (!userId) return console.error("⚠️ User ID is missing");
//
//         try {
//             setTimeout(async () => {
//                 const response = await axios.get(`http://localhost:8081/api/cart/${userId}`, {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//
//                 console.log("🛒 Raw Cart API Response:", response.data);
//
//                 if (!response.data.cartItems) {
//                     console.error("⚠️ Cart data missing `cartItems`.");
//                     setCart([]);
//                 } else {
//                     setCart(
//                         response.data.cartItems.map(item => ({
//                             ...item,
//                             productId: item.product?._id || item.productId, // ✅ Ensure productId is correctly set
//                             price: item.product?.price || item.price || 0,  // ✅ Ensure price is never undefined
//                         }))
//                     );
//                 }
//             }, 500);
//         } catch (error) {
//             console.error("❌ Error fetching cart data:", error.response?.data || error.message);
//             setError("Failed to fetch cart data");
//         }
//     };
//
//     const updateCart = async (productId, action) => {
//         if (!productId) return;
//         const token = localStorage.getItem("token");
//         if (!token) return;
//
//         setCartLoading(true);
//
//         // ✅ Optimistically update state
//         setCart((prevCart) =>
//             prevCart.map((item) =>
//                 item.productId === productId
//                     ? {
//                         ...item,
//                         quantity: action === "increase" ? item.quantity + 1 : item.quantity - 1,
//                         price: item.price, // ✅ Keep price from being lost
//                     }
//                     : item
//             )
//         );
//
//         try {
//             const response = await axios.post(
//                 "http://localhost:8081/api/cart/updateQuantity",
//                 { productId, action },
//                 { headers: { Authorization: `Bearer ${token}` } }
//             );
//
//             console.log("🛒 API Response:", response.data);
//
//             if (response.data.cartItems) {
//                 setCart(response.data.cartItems.map((item) => ({
//                     ...item,
//                     price: item.price || 0, // ✅ Prevent price from resetting to 0
//                 })));
//             }
//         } catch (err) {
//             console.error("Error updating cart:", err);
//             setError("Error updating cart");
//         } finally {
//             setCartLoading(false);
//         }
//     };
//
//
//     const handleRemoveFromCart = async (productId) => {
//         if (!productId) return;
//         const token = localStorage.getItem("token");
//         if (!token) return;
//
//         setCartLoading(true);
//         try {
//             const response = await axios.delete(
//                 `http://localhost:8081/api/cart/remove/${productId}`,
//                 { headers: { Authorization: `Bearer ${token}` } }
//             );
//             setCart(response.data.cartItems || []);
//         } catch (err) {
//             setError("Failed to remove item");
//         } finally {
//             setCartLoading(false);
//         }
//     };
//
//     return (
//         <div>
//             <h1>Shopping Cart</h1>
//             {loading && <p>Loading...</p>}
//             {error && <p style={{ color: "red" }}>{error}</p>}
//             {cartLoading && <p>Updating cart...</p>}
//             {!loading && !error && cart.length === 0 && <p>Your cart is empty.</p>}
//
//             {cart.map((item, index) => {
//                 console.log("🔍 Checking Cart Item:", item);
//                 console.log("🆔 Product ID:", item.productId);
//
//                 return (
//                     <div key={item.productId || index}>
//                         <img src={item.imageUrl || 'placeholder.jpg'} alt={item.productName || 'Product Image'}
//                              style={{width: "100px", height: "100px"}}/>
//                         <h2>{item.productName || "Product Name"}</h2>
//                         <p>Price per Item: ₹{item.price ? item.price.toFixed(2) : "0.00"}</p>
//                         <p>Total for this Item: ₹{(item.price * (item.quantity || 0)).toFixed(2)}</p>
//
//                         <div>
//                             <button onClick={() => updateCart(item.productId, "decrease")}>-</button>
//                             <span> Quantity: {item.quantity || 0} </span>
//                             <button onClick={() => updateCart(item.productId, "increase")}>+</button>
//                         </div>
//                         <button onClick={() => handleRemoveFromCart(item.productId)}>Remove from Cart</button>
//                     </div>
//                 );
//             })}
//         </div>
//     );
// };
//
// export default Cart;
//
//



import { useState, useEffect } from "react";
import "../styles/Home.css";
import "../styles/Login.css";
import axios from "axios";


const Cart = () => {
    const [cart, setCart] = useState([]);
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [cartLoading, setCartLoading] = useState(false);

    useEffect(() => {
        const fetchUser = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                setLoading(false);
                return;
            }

            try {
                const response = await axios.get("http://localhost:8081/api/users/user", {
                    headers: { Authorization: `Bearer ${token}` },
                });

                console.log("👤 User Data:", response.data);

                if (response.data.id) {
                    setUser(response.data); // Update user state
                    fetchCart(response.data.id, token);
                } else {
                    console.error("❌ User ID is missing from response");
                }
            } catch (error) {
                console.error("Error fetching user data:", error);
                setError("Failed to fetch user data");
            } finally {
                setLoading(false);
            }
        };

        fetchUser();
    }, []);

    // const fetchCart = async (userId, token) => {
    //     try {
    //         const response = await axios.get(`http://localhost:8081/api/cart/${userId}`, {
    //             headers: { Authorization: `Bearer ${token}` },
    //         });
    //
    //         console.log("🛒 Cart API Response:", response.data);
    //
    //         if (!response.data.items || response.data.items.length === 0) {
    //             console.error("⚠️ No items found in cart.");
    //             setCart([]);
    //         } else {
    //             response.data.items.forEach(item => {
    //                 console.log(`📦 Product: ${item.productName}, 📸 Image: ${item.imageUrl}`);
    //             });
    //             setCart(response.data.items);
    //         }
    //     } catch (error) {
    //         console.error("❌ Error fetching cart data:", error.response?.data || error.message);
    //         setCart([]);
    //     }
    // };



    const fetchCart = async (userId, token) => {
        try {
            const response = await axios.get(`http://localhost:8081/api/cart/${userId}`, {
                headers: { Authorization: `Bearer ${token}` },
            });

            console.log("🛒 Cart API Response:", response.data);

            if (!response.data.items || response.data.items.length === 0) {
                console.error("⚠️ No items found in cart.");
                setCart([]);
            } else {
                response.data.items.forEach(item => {
                    console.log(`📦 Product: ${item.product?.name}, 🆔 Product ID: ${item.product?.id}`);
                });
                setCart(response.data.items);
            }
        } catch (error) {
            console.error("❌ Error fetching cart data:", error.response?.data || error.message);
            setCart([]);
        }
    };



    // const updateCart = async (productId, action) => {
    //     const token = localStorage.getItem("token");
    //     if (!token || !user?.id) {
    //         console.error("No token or userId found.");
    //         return;
    //     }
    //
    //     try {
    //         console.log("🔍 Checking userId:", user.id);
    //
    //         const response = await axios.put(
    //             "http://localhost:8081/api/cart/updateQuantity", // ✅ Ensure this matches backend
    //             { userId: user.id, productId, action },
    //             { headers: { Authorization: `Bearer ${token}` } }
    //         );
    //
    //         console.log("✅ Cart updated successfully:", response.data);
    //         setCart(response.data.items || []);
    //     } catch (err) {
    //         console.error("❌ Error updating cart:", err.response?.data || err.message);
    //     }
    // };


    const updateCart = async (productId, action) => {
        const token = localStorage.getItem("token");
        if (!token || !user?.id) {
            console.error("No token or userId found.");
            return;
        }

        try {
            console.log("🔍 Updating cart - User ID:", user.id, "Product ID:", productId, "Action:", action);

            const response = await axios.put(
                "http://localhost:8081/api/cart/updateQuantity",
                { userId: user.id, productId, action },
                { headers: { Authorization: `Bearer ${token}` } }
            );

            console.log("✅ Cart updated successfully:", response.data);

            // ✅ Fetch the latest cart after update
            fetchCart(user.id, token);
        } catch (err) {
            console.error("❌ Error updating cart:", err.response?.data || err.message);
        }
    };





    // const handleRemoveFromCart = async (productId) => {
    //     try {
    //         console.log(`🗑 Removing product - ID: ${productId}`);
    //
    //         const response = await axios.delete(`http://localhost:8081/api/cart/remove/${productId}`, {
    //             headers: {
    //                 Authorization: `Bearer ${localStorage.getItem("token")}`,
    //             },
    //         });
    //
    //         if (response.status === 200) {
    //             console.log("✅ Product removed:", response.data);
    //
    //             // Safely filter the cart items to remove the product
    //             setCart((prevCart) =>
    //                 prevCart.filter(item => item.product && item.product._id !== productId)
    //             );
    //         } else {
    //             console.warn("⚠️ Unexpected response:", response.data);
    //         }
    //     } catch (error) {
    //         console.error("❌ Error removing product:", error.response?.data || error.message);
    //     }
    // };


    const handleRemoveFromCart = async (productId) => {
        try {
            console.log(`🗑 Removing product - ID: ${productId}`);

            // Optimistic UI update: remove the product from the cart immediately
            setCart(prevCart => prevCart.filter(item => item.product && item.product._id !== productId));

            // Call the backend to remove the item from the cart
            const response = await axios.delete(`http://localhost:8081/api/cart/remove/${productId}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });

            // Check if removal was successful
            if (response.status === 200) {
                console.log("✅ Product removed:", response.data);
                // Optionally, refetch the cart to ensure data is up-to-date
                const token = localStorage.getItem("token");
                if (user && user.id && token) {
                    fetchCart(user.id, token);
                }
            } else {
                console.warn("⚠️ Unexpected response:", response.data);
            }
        } catch (error) {
            console.error("❌ Error removing product:", error.response?.data || error.message);
            // Rollback the optimistic update in case of failure
            setCart(prevCart => prevCart); // In case of error, no change in the cart state
        }
    };







    return (
        <div>
            <h1 className="heading">Shopping Cart</h1>
            {loading && <p>Loading...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}
            {cartLoading && <p>Updating cart...</p>}
            {!loading && !error && cart.length === 0 && <p>Your cart is empty.</p>}
            <div className="category-section">
            {cart.map((item, index) => (
                <div className="category" key={item.productId || index}>
                    <img src={item.imageUrl || 'placeholder.jpg'} alt={item.productName || 'Product Image'}
                         style={{width: "100px", height: "100px"}}/>
                    <h2 className= "heading">{item.productName || "Product Name"}</h2>
                    <p className="para">Price per Item: ₹{item.price ? item.price.toFixed(2) : "0.00"}</p>
                    <p className="para">Total for this Item: ₹{(item.price * (item.quantity || 0)).toFixed(2)}</p>

                    <div>
                        <button className="sign-in-button"
                            onClick={() => updateCart(item.productId, "decrease")}
                            disabled={item.quantity <= 1}
                        >
                            -
                        </button>
                        <span> Quantity: {item.quantity || 0} </span>
                        <button className="sign-in-button"
                            onClick={() => updateCart(item.productId, "increase")}
                        >
                            +
                        </button>
                    </div>
                    <button className="sign-in-button" onClick={() => handleRemoveFromCart(item.productId || item.product?.id)}>
                        Remove from Cart
                    </button>


                </div>
            ))}</div>
        </div>
    );

};

export default Cart;
