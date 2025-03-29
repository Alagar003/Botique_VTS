// // // import { useState, useEffect } from "react";
// // // import { useNavigate } from "react-router-dom";
// // // import axios from "axios";
// // //
// // // const Checkout = () => {
// // //     const [address, setAddress] = useState("");
// // //     const [cart, setCart] = useState([]);
// // //     const [user, setUser] = useState(null);
// // //     const navigate = useNavigate();
// // //
// // //     useEffect(() => {
// // //         const fetchUserAndCart = async () => {
// // //             const token = localStorage.getItem("token");
// // //             if (!token) return;
// // //
// // //             try {
// // //                 // Fetch user data
// // //                 const userResponse = await axios.get("http://localhost:8081/api/users/user", {
// // //                     headers: { Authorization: `Bearer ${token}` },
// // //                 });
// // //
// // //                 if (userResponse.data.id) {
// // //                     setUser(userResponse.data);
// // //
// // //                     // Fetch cart data
// // //                     const cartResponse = await axios.get(`http://localhost:8081/api/cart/${userResponse.data.id}`, {
// // //                         headers: { Authorization: `Bearer ${token}` },
// // //                     });
// // //
// // //                     console.log("Cart API Response:", cartResponse.data); // Debugging line
// // //
// // //                     setCart(cartResponse.data.items || []);
// // //                 }
// // //             } catch (error) {
// // //                 console.error("Error fetching user or cart:", error);
// // //             }
// // //         };
// // //
// // //         fetchUserAndCart();
// // //     }, []);
// // //
// // //     const handleOrderSubmit = async () => {
// // //         if (!address.trim()) {
// // //             alert("Please enter an address.");
// // //             return;
// // //         }
// // //
// // //         const token = localStorage.getItem("token");
// // //         if (!token || !user?.id) {
// // //             alert("You must be logged in to place an order.");
// // //             return;
// // //         }
// // //
// // //         console.log("📦 Raw Cart Data Before Processing:", cart);
// // //
// // //         // ✅ Debugging: Log each cart item
// // //         cart.forEach((item, index) => {
// // //             console.log(`🛒 Item ${index + 1}:`, item);
// // //         });
// // //
// // //         // ✅ Extract product IDs based on actual cart structure
// // //         const productIds = cart
// // //             .filter(item => item?.productId) // Ensure productId exists
// // //             .map(item => item.productId);
// // //
// // //         console.log("🔍 Extracted Product IDs:", productIds);
// // //
// // //         if (productIds.length === 0) {
// // //             alert("⚠️ No valid products in the cart.");
// // //             return;
// // //         }
// // //
// // //         try {
// // //             const response = await axios.post(
// // //                 "http://localhost:8081/api/orders/create",
// // //                 {
// // //                     userId: user.id,
// // //                     address: address,
// // //                     phoneNumber: user.phoneNumber || "",
// // //                     productIds: productIds,
// // //                 },
// // //                 {
// // //                     headers: { Authorization: `Bearer ${token}` },
// // //                 }
// // //             );
// // //
// // //             await axios.delete(`http://localhost:8081/api/cart/${user.id}`, {
// // //                 headers: { Authorization: `Bearer ${token}` },
// // //                 data: productIds.length ? productIds : []  // ✅ Send as an array
// // //             });
// // //
// // //
// // //             alert(`✅ Order placed successfully! Order ID: ${response.data.orderId}`);
// // //             navigate("/cart");
// // //         } catch (error) {
// // //             console.error("❌ Error placing order:", error.response?.data || error.message);
// // //             alert("⚠️ Failed to place order. Please try again.");
// // //         }
// // //     };
// // //
// // //
// // //     return (
// // //         <div>
// // //             <h1>Checkout</h1>
// // //             <input
// // //                 type="text"
// // //                 placeholder="Enter your address"
// // //                 value={address}
// // //                 onChange={(e) => setAddress(e.target.value)}
// // //             />
// // //             <button onClick={handleOrderSubmit}>Confirm Order</button>
// // //         </div>
// // //     );
// // // };
// // //
// // // export default Checkout;
// //
// //
// //
// // import { useState, useEffect } from "react";
// // import { useNavigate } from "react-router-dom";
// // import axios from "axios";
// //
// // const Checkout = () => {
// //     const [address, setAddress] = useState("");
// //     const [cart, setCart] = useState([]);
// //     const [user, setUser] = useState(null);
// //     const navigate = useNavigate();
// //
// //     useEffect(() => {
// //         const fetchUserAndCart = async () => {
// //             const token = localStorage.getItem("token");
// //             if (!token) {
// //                 console.warn("⚠️ No token found. User not authenticated.");
// //                 return;
// //             }
// //
// //             try {
// //                 // Fetch user data
// //                 const userResponse = await axios.get("http://localhost:8081/api/users/user", {
// //                     headers: { Authorization: `Bearer ${token}` },
// //                 });
// //
// //                 if (userResponse.data.id) {
// //                     setUser(userResponse.data);
// //
// //                     // Fetch cart data
// //                     const cartResponse = await axios.get(`http://localhost:8081/api/cart/${userResponse.data.id}`, {
// //                         headers: { Authorization: `Bearer ${token}` },
// //                     });
// //
// //                     console.log("Cart API Response:", cartResponse.data);
// //
// //                     // Ensure `items` is always an array before setting state
// //                     setCart(Array.isArray(cartResponse.data.items) ? cartResponse.data.items : []);
// //                 }
// //             } catch (error) {
// //                 console.error("❌ Error fetching user or cart:", error.response?.data || error.message);
// //                 alert("⚠️ Failed to fetch user/cart data. Please try again.");
// //             }
// //         };
// //
// //         fetchUserAndCart();
// //     }, []);
// //
// //     const handleOrderSubmit = async () => {
// //         if (!address.trim()) {
// //             alert("⚠️ Please enter a valid address.");
// //             return;
// //         }
// //
// //         const token = localStorage.getItem("token");
// //         if (!token || !user?.id) {
// //             alert("⚠️ You must be logged in to place an order.");
// //             return;
// //         }
// //
// //         console.log("📦 Raw Cart Data Before Processing:", cart);
// //
// //         // ✅ Debugging: Log each cart item
// //         cart.forEach((item, index) => {
// //             console.log(`🛒 Item ${index + 1}:`, item);
// //         });
// //
// //         // ✅ Extract product IDs based on actual cart structure
// //         const productIds = cart
// //             .filter(item => item?.productId) // Ensure productId exists
// //             .map(item => item.productId);
// //
// //         console.log("🔍 Extracted Product IDs:", productIds);
// //
// //         if (productIds.length === 0) {
// //             alert("⚠️ No valid products in the cart.");
// //             return;
// //         }
// //
// //         try {
// //             // Send order creation request
// //             const response = await axios.post(
// //                 "http://localhost:8081/api/orders/create",
// //                 {
// //                     userId: user.id,
// //                     address: address,
// //                     phoneNumber: user.phoneNumber || "",
// //                     productIds: productIds,
// //                 },
// //                 {
// //                     headers: { Authorization: `Bearer ${token}` },
// //                 }
// //             );
// //
// //             // Clear the cart after placing the order
// //             await axios.delete(`http://localhost:8081/api/cart/${user.id}`, {
// //                 headers: { Authorization: `Bearer ${token}` },
// //                 data: productIds,  // ✅ Send product IDs as an array
// //             });
// //
// //             alert(`✅ Order placed successfully! Order ID: ${response.data.orderId}`);
// //             navigate("/cart"); // Navigate after successful order
// //         } catch (error) {
// //             console.error("❌ Error placing order:", error.response?.data || error.message);
// //             alert("⚠️ Failed to place order. Please try again.");
// //         }
// //     };
// //
// //     return (
// //         <div>
// //             <h1>Checkout</h1>
// //             <input
// //                 type="text"
// //                 placeholder="Enter your address"
// //                 value={address}
// //                 onChange={(e) => setAddress(e.target.value)}
// //             />
// //             <button onClick={handleOrderSubmit}>Confirm Order</button>
// //         </div>
// //     );
// // };
// //
// // export default Checkout;
//
//
//
// // import { useState, useEffect } from "react";
// // import { useNavigate } from "react-router-dom";
// // import axios from "axios";
// //
// // const Checkout = () => {
// //     const [address, setAddress] = useState("");
// //     const [phoneNumber, setPhoneNumber] = useState("");
// //     const [cart, setCart] = useState([]);
// //     const [user, setUser] = useState(null);
// //     const navigate = useNavigate();
// //
// //     useEffect(() => {
// //         const fetchUserAndCart = async () => {
// //             const token = localStorage.getItem("token");
// //             if (!token) {
// //                 console.warn("⚠️ No token found. User not authenticated.");
// //                 return;
// //             }
// //
// //             try {
// //                 // Fetch user data
// //                 const userResponse = await axios.get("http://localhost:8081/api/users/user", {
// //                     headers: { Authorization: `Bearer ${token}` },
// //                 });
// //
// //                 if (userResponse.data.id) {
// //                     setUser(userResponse.data);
// //                     setPhoneNumber(userResponse.data.phoneNumber || "");
// //
// //                     // Fetch cart data
// //                     const cartResponse = await axios.get(`http://localhost:8081/api/cart/${userResponse.data.id}`, {
// //                         headers: { Authorization: `Bearer ${token}` },
// //                     });
// //
// //                     console.log("🛒 Cart API Response:", cartResponse.data);
// //
// //                     // Ensure items exist and are an array
// //                     const cartItems = cartResponse.data?.items || [];
// //                     if (!Array.isArray(cartItems)) {
// //                         console.error("❌ Cart API did not return an array. Received:", cartItems);
// //                         return;
// //                     }
// //
// //                     setCart(cartItems);
// //                 }
// //             } catch (error) {
// //                 console.error("❌ Error fetching user or cart:", error.response?.data || error.message);
// //                 alert("⚠️ Failed to fetch user/cart data. Please try again.");
// //             }
// //         };
// //
// //         fetchUserAndCart();
// //     }, []);
// //
// //     const handleOrderSubmit = async () => {
// //         if (!address.trim()) {
// //             alert("⚠️ Please enter a valid address.");
// //             return;
// //         }
// //
// //         const token = localStorage.getItem("token");
// //         if (!token || !user?.id) {
// //             alert("⚠️ You must be logged in to place an order.");
// //             return;
// //         }
// //
// //         console.log("📦 Raw Cart Data Before Processing:", cart);
// //
// //         const orderItems = cart.filter((item) => {
// //             if (item.price == null) {
// //                 console.error(`🚨 Error: Product ${item.productId} has a null price!`);
// //                 return false; // Remove this item from the order
// //             }
// //             return true;
// //         }).map((item) => ({
// //             productId: item.productId,
// //             quantity: item.quantity,
// //             price: item.price, // Ensure price is not null
// //         }));
// //
// //
// //
// //         const totalPrice = orderItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
// //
// //         console.log("🛒 Order Items:", orderItems);
// //         console.log("💰 Total Price:", totalPrice);
// //
// //         if (orderItems.length === 0) {
// //             alert("⚠️ No valid products in the cart.");
// //             return;
// //         }
// //
// //         try {
// //             // 🔵 Step 1: Place Order
// //             const response = await axios.post(
// //                 "http://localhost:8081/api/orders/create",
// //                 {
// //                     userId: user.id,
// //                     address: address,
// //                     phoneNumber: phoneNumber,
// //                     totalPrice: totalPrice, // ✅ Send total price
// //                     orderItems: orderItems, // ✅ Send detailed items
// //                 },
// //                 {
// //                     headers: { Authorization: `Bearer ${token}` },
// //                 }
// //             );
// //
// //             console.log("✅ Order placed successfully:", response.data);
// //
// //             // 🔵 Step 2: Remove only the ordered items from the cart
// //             await axios.post(
// //                 "http://localhost:8081/api/cart/remove",
// //                 {
// //                     userId: user.id,
// //                     productIds: orderItems.map((item) => item.productId),
// //                 },
// //                 {
// //                     headers: {
// //                         "Content-Type": "application/json",
// //                         Authorization: `Bearer ${token}`,
// //                     },
// //                 }
// //             );
// //
// //             console.log("🗑️ Selected cart items removed successfully.");
// //
// //             // 🔵 Step 3: Fetch updated cart after removal
// //             const updatedCartResponse = await axios.get(`http://localhost:8081/api/cart/${user.id}`, {
// //                 headers: { Authorization: `Bearer ${token}` },
// //             });
// //             setCart(updatedCartResponse.data?.items || []);
// //
// //             alert(`✅ Order placed successfully! Order ID: ${response.data.orderId}`);
// //
// //             setTimeout(() => {
// //                 navigate("/orders"); // Navigate to Orders page after success
// //             }, 500);
// //         } catch (error) {
// //             console.error("❌ Error placing order:", error.response?.data || error.message);
// //             alert("⚠️ Failed to place order. Please try again.");
// //         }
// //     };
// //
// //
// //     return (
// //         <div>
// //             <h1>Checkout</h1>
// //             <input
// //                 type="text"
// //                 placeholder="Enter your address"
// //                 value={address}
// //                 onChange={(e) => setAddress(e.target.value)}
// //             />
// //             <input
// //                 type="text"
// //                 placeholder="Enter your Phone Number"
// //                 value={phoneNumber}
// //                 onChange={(e) => setPhoneNumber(e.target.value)}
// //             />
// //             <button onClick={handleOrderSubmit}>Confirm Order</button>
// //         </div>
// //     );
// // };
// //
// // export default Checkout;
//
//
//
//
//
// import { useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import axios from "axios";
//
// const Checkout = () => {
//     const [address, setAddress] = useState("");
//     const [phoneNumber, setPhoneNumber] = useState("");
//     const [cart, setCart] = useState([]);
//     const [user, setUser] = useState(null);
//     const [orders, setOrders] = useState([]);
//     const navigate = useNavigate();
//
//     useEffect(() => {
//         const fetchUserAndOrders = async () => {
//             const token = localStorage.getItem("token");
//
//             if (!token) {
//                 console.warn("⚠️ No token found. User not authenticated.");
//                 alert("⚠️ Please log in to view your orders.");
//                 return;
//             }
//
//             try {
//                 // Fetch user details
//                 const userResponse = await axios.get("http://localhost:8081/api/users/user", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//
//                 console.log("✅ Fetched user data:", userResponse.data);
//                 setUser(userResponse.data);
//
//                 // Check if user ID exists
//                 const userId = userResponse.data?.id;
//                 if (!userId) {
//                     console.error("❌ User ID is missing.");
//                     alert("⚠️ Unable to retrieve user details. Please try again.");
//                     return;
//                 }
//
//                 // Fetch orders for the user
//                 const ordersResponse = await axios.get("http://localhost:8081/api/orders/user", {
//                     headers: { Authorization: `Bearer ${token}` },
//                     params: { userId },
//                 });
//
//                 console.log("📦 Fetched orders:", ordersResponse.data);
//                 setOrders(ordersResponse.data || []);
//             } catch (error) {
//                 console.error("❌ Error fetching user or orders:", error.response?.data || error.message);
//                 alert("⚠️ Failed to fetch orders. Please try again.");
//             }
//         };
//
//         fetchUserAndOrders();
//     }, []);
//
//
//     // const handleOrderSubmit = async () => {
//     //     if (!address.trim()) {
//     //         alert("⚠️ Please enter a valid address.");
//     //         return;
//     //     }
//     //
//     //     const token = localStorage.getItem("token");
//     //     if (!token || !user?.id) {
//     //         alert("⚠️ You must be logged in to place an order.");
//     //         return;
//     //     }
//     //
//     //     // Prepare the order items and validate product IDs
//     //     const orderItems = cart.map((item) => ({
//     //         product: {
//     //             id: item.productId,
//     //             name: item.productName,
//     //         },
//     //         quantity: item.quantity,
//     //         totalPrice: item.price * item.quantity,
//     //     }));
//     //
//     //     const productIds = orderItems.map((item) => item.product.id);
//     //     if (productIds.some((id) => !id)) {
//     //         alert("⚠️ Some items in the cart have invalid product IDs.");
//     //         return;
//     //     }
//     //
//     //     const totalPrice = orderItems.reduce((sum, item) => sum + item.totalPrice, 0);
//     //
//     //     if (orderItems.length === 0) {
//     //         alert("⚠️ No valid products in the cart.");
//     //         return;
//     //     }
//     //
//     //     try {
//     //         console.log("⏳ Placing order...");
//     //         console.log("Order Payload:", {
//     //             userId: user.id,
//     //             address,
//     //             phoneNumber,
//     //             totalPrice,
//     //             orderItems,
//     //         });
//     //
//     //         // Create the order
//     //         const orderResponse = await axios.post(
//     //             "http://localhost:8081/api/orders/create",
//     //             {
//     //                 userId: user.id,
//     //                 address,
//     //                 phoneNumber,
//     //                 totalPrice,
//     //                 orderItems,
//     //             },
//     //             {
//     //                 headers: { Authorization: `Bearer ${token}` },
//     //             }
//     //         );
//     //
//     //         alert(`✅ Order placed successfully! Order ID: ${orderResponse.data.orderId}`);
//     //         console.log("Payload for DELETE request:", { userId: user.id, productIds });
//     //
//     //         // Remove items from the cart
//     //         await axios.delete("http://localhost:8081/api/cart/remove", {
//     //             headers: { Authorization: `Bearer ${token}` },
//     //             data: {
//     //                 userId: user.id,
//     //                 productIds,
//     //             },
//     //         });
//     //
//     //         console.log("🛒 Removed items from cart.");
//     //
//     //         // Refresh the cart
//     //         const updatedCartResponse = await axios.get(`http://localhost:8081/api/cart/${user.id}`, {
//     //             headers: { Authorization: `Bearer ${token}` },
//     //         });
//     //         setCart(updatedCartResponse.data?.items || []);
//     //
//     //         console.log("✅ Updated cart:", updatedCartResponse.data?.items || []);
//     //
//     //         // Redirect to the orders page
//     //         setTimeout(() => {
//     //             navigate("/orders");
//     //         }, 500);
//     //     } catch (error) {
//     //         console.error("❌ Error placing order:", error.response?.data || error.message);
//     //         alert("⚠️ Failed to place order. Please try again.");
//     //     }
//     // };
//
//
//     const handleOrderSubmit = async () => {
//         if (!address.trim()) {
//             alert("⚠️ Please enter a valid address.");
//             return;
//         }
//
//         const token = localStorage.getItem("token");
//         if (!token || !user?.id) {
//             alert("⚠️ You must be logged in to place an order.");
//             return;
//         }
//
//         // Validate the cart before processing
//         if (!cart || cart.length === 0) {
//             console.error("🛒 Cart is empty or invalid.");
//             alert("⚠️ Your cart is empty. Please add products to proceed.");
//             return;
//         }
//
//         // Map order items
//         const orderItems = cart.map((item) => ({
//             product: {
//                 id: item.productId || null,
//                 name: item.productName || "Unknown Product",
//             },
//             quantity: item.quantity || 0,
//             totalPrice: item.price * item.quantity || 0,
//         }));
//
//         const productIds = orderItems.map((item) => item.product.id);
//         console.log("📦 Order Items:", orderItems);
//         console.log("🛒 Product IDs:", productIds);
//
//         if (productIds.some((id) => !id)) {
//             console.error("❌ Invalid product IDs detected:", productIds);
//             alert("⚠️ Some items in the cart have invalid product IDs.");
//             return;
//         }
//
//         const totalPrice = orderItems.reduce((sum, item) => sum + item.totalPrice, 0);
//         console.log("💵 Total Order Price:", totalPrice);
//
//         try {
//             console.log("⏳ Placing order...");
//             const orderResponse = await axios.post(
//                 "http://localhost:8081/api/orders/create",
//                 {
//                     userId: user.id,
//                     address,
//                     phoneNumber,
//                     totalPrice,
//                     orderItems,
//                 },
//                 {
//                     headers: { Authorization: `Bearer ${token}` },
//                 }
//             );
//
//             alert(`✅ Order placed successfully! Order ID: ${orderResponse.data.orderId}`);
//
//             // Remove items from the cart
//             await axios.delete("http://localhost:8081/api/cart/remove", {
//                 headers: { Authorization: `Bearer ${token}` },
//                 data: {
//                     userId: user.id,
//                     productIds,
//                 },
//             });
//
//             console.log("🛒 Removed items from cart.");
//
//             // Refresh the cart
//             const updatedCartResponse = await axios.get(`http://localhost:8081/api/cart/${user.id}`, {
//                 headers: { Authorization: `Bearer ${token}` },
//             });
//             setCart(updatedCartResponse.data?.items || []);
//
//             console.log("✅ Updated cart:", updatedCartResponse.data?.items || []);
//             setTimeout(() => {
//                 navigate("/orders");
//             }, 500);
//         } catch (error) {
//             console.error("❌ Error placing order:", error.response?.data || error.message);
//             alert("⚠️ Failed to place order. Please try again.");
//         }
//     };
//
//
//
//
//
//     return (
//         <div>
//             <h1>Checkout</h1>
//             <input
//                 type="text"
//                 placeholder="Enter your address"
//                 value={address}
//                 onChange={(e) => setAddress(e.target.value)}
//             />
//             <input
//                 type="text"
//                 placeholder="Enter your phone number"
//                 value={phoneNumber}
//                 onChange={(e) => setPhoneNumber(e.target.value)}
//             />
//             <button onClick={handleOrderSubmit}>Confirm Order</button>
//         </div>
//     );
// };
//
// export default Checkout;

//
// import React, { useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import axios from "axios";
//
// const Checkout = () => {
//     const [address, setAddress] = useState("");
//     const [phoneNumber, setPhoneNumber] = useState("");
//     const [cart, setCart] = useState([]);
//     const [user, setUser] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const navigate = useNavigate();
//
//     // Fetch user and cart details
//     useEffect(() => {
//         const fetchUserAndCart = async () => {
//             const token = localStorage.getItem("token");
//
//             if (!token) {
//                 console.warn("⚠️ No token found. User not authenticated.");
//                 alert("⚠️ Please log in to proceed.");
//                 setLoading(false);
//                 return;
//             }
//
//             try {
//                 // Fetch user details
//                 const userResponse = await axios.get("http://localhost:8081/api/users/user", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//                 console.log("✅ Fetched user data:", userResponse.data);
//                 setUser(userResponse.data);
//
//                 // Fetch cart details
//                 const cartResponse = await axios.get(`http://localhost:8081/api/cart/${userResponse.data?.id}`, {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//                 console.log("✅ Fetched cart data:", cartResponse.data?.items || []);
//                 setCart(cartResponse.data?.items || []);
//             } catch (error) {
//                 console.error("❌ Error fetching user or cart:", error.response?.data || error.message);
//                 alert("⚠️ Failed to fetch user or cart data. Please try again.");
//                 setCart([]); // Default to empty cart
//             } finally {
//                 setLoading(false); // Stop loading spinner
//             }
//         };
//
//         fetchUserAndCart();
//     }, []);
//
//     // Handle order submission
//     const handleOrderSubmit = async () => {
//         if (!address.trim()) {
//             alert("⚠️ Please enter a valid address.");
//             return;
//         }
//
//         const token = localStorage.getItem("token");
//         if (!token || !user?.id) {
//             alert("⚠️ You must be logged in to place an order.");
//             return;
//         }
//
//         // Validate cart data
//         if (!cart || cart.length === 0) {
//             console.error("🛒 Cart is empty or invalid.");
//             alert("⚠️ Your cart is empty. Please add products to proceed.");
//             return;
//         }
//
//         // Map order items
//         const orderItems = cart.map((item) => ({
//             product: {
//                 id: item.productId || null,
//                 name: item.productName || "Unknown Product",
//             },
//             quantity: item.quantity || 0,
//             totalPrice: item.price * item.quantity || 0,
//         }));
//
//         const productIds = orderItems.map((item) => item.product.id);
//         console.log("📦 Order Items:", orderItems);
//         console.log("🛒 Product IDs:", productIds);
//
//         if (productIds.some((id) => !id)) {
//             console.error("❌ Invalid product IDs detected:", productIds);
//             alert("⚠️ Some items in the cart have invalid product IDs.");
//             return;
//         }
//
//         const totalPrice = orderItems.reduce((sum, item) => sum + item.totalPrice, 0);
//         console.log("💵 Total Order Price:", totalPrice);
//
//         try {
//             console.log("⏳ Placing order...");
//             const orderResponse = await axios.post(
//                 "http://localhost:8081/api/orders/create",
//                 {
//                     user: { id: user.id },
//                     address,
//                     phoneNumber,
//                     totalPrice,
//                     orderItems,
//                 },
//                 {
//                     headers: { Authorization: `Bearer ${token}` },
//                 }
//             );
//
//             alert(`✅ Order placed successfully! Order ID: ${orderResponse.data.orderId}`);
//             console.log("📦 Order Response:", orderResponse.data);
//
//             // Remove items from the cart
//             console.log("🛒 Removing items from cart...");
//             await axios.delete("http://localhost:8081/api/cart/remove", {
//                 headers: { Authorization: `Bearer ${token}` },
//                 data: {
//                     userId: user.id,
//                     productIds,
//                 },
//             });
//             console.log("🛒 Cart items successfully removed.");
//
//             // Refresh the cart
//             const updatedCartResponse = await axios.get(`http://localhost:8081/api/cart/${user.id}`, {
//                 headers: { Authorization: `Bearer ${token}` },
//             });
//             setCart(updatedCartResponse.data?.items || []);
//             console.log("✅ Updated cart:", updatedCartResponse.data?.items || []);
//
//             // Redirect to orders page
//             setTimeout(() => {
//                 navigate("/orders");
//             }, 500);
//         } catch (error) {
//             console.error("❌ Error placing order:", error.response?.data || error.message);
//             alert("⚠️ Failed to place order. Please try again.");
//         }
//     };
//
//     if (loading) {
//         return <p>Loading user and cart data...</p>;
//     }
//
//     return (
//         <div>
//             <h1>Checkout</h1>
//             <input
//                 type="text"
//                 placeholder="Enter your address"
//                 value={address}
//                 onChange={(e) => setAddress(e.target.value)}
//             />
//             <input
//                 type="text"
//                 placeholder="Enter your phone number"
//                 value={phoneNumber}
//                 onChange={(e) => setPhoneNumber(e.target.value)}
//             />
//             <button onClick={handleOrderSubmit}>Confirm Order</button>
//         </div>
//     );
// };
//
// export default Checkout;

import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Checkout = () => {
    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [cart, setCart] = useState([]);
    const [user, setUser] = useState(null);
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    // Fetch user and cart details
    useEffect(() => {
        const fetchUserAndCart = async () => {
            const token = localStorage.getItem("token");

            if (!token) {
                console.warn("⚠️ No token found. User not authenticated.");
                alert("⚠️ Please log in to proceed.");
                setLoading(false);
                return;
            }

            try {
                // Fetch user details
                const userResponse = await axios.get("http://localhost:8081/api/users/user", {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log("✅ Fetched user data:", userResponse.data);
                setUser(userResponse.data); // Update user state

                // Fetch cart details
                const cartResponse = await axios.get(`http://localhost:8081/api/cart/${userResponse.data?.id}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log("✅ Fetched cart data:", cartResponse.data?.items || []);
                setCart(cartResponse.data?.items || []);

                // Fetch orders for user
                const ordersResponse = await axios.get(`http://localhost:8081/api/orders/user`, {
                    headers: { Authorization: `Bearer ${token}` },
                    params: { userId: userResponse.data?.id },
                });
                console.log("📦 Fetched orders:", ordersResponse.data);
                setOrders(ordersResponse.data || []);
            } catch (error) {
                console.error("❌ Error fetching user, cart, or orders:", error.response?.data || error.message);
                alert("⚠️ Failed to fetch user, cart, or orders.");
                setCart([]);
            } finally {
                setLoading(false);
            }
        };

        fetchUserAndCart();
    }, []);

    // Handle order submission
    const handleOrderSubmit = async () => {
        if (!address.trim()) {
            alert("⚠️ Please enter a valid address.");
            return;
        }

        const token = localStorage.getItem("token");
        if (!token || !user?.id) {
            alert("⚠️ You must be logged in to place an order.");
            return;
        }

        // Validate cart data
        if (!cart || cart.length === 0) {
            console.error("🛒 Cart is empty or invalid.");
            alert("⚠️ Your cart is empty. Please add products to proceed.");
            return;
        }

        // Map order items
        const orderItems = cart.map((item) => ({
            product: {
                id: item.productId || null,
                name: item.productName || "Unknown Product",
            },
            quantity: item.quantity || 0,
            totalPrice: item.price * item.quantity || 0,
        }));

        const productIds = orderItems.map((item) => item.product.id);
        console.log("📦 Order Items:", orderItems);
        console.log("🛒 Product IDs:", productIds);

        if (productIds.some((id) => !id)) {
            console.error("❌ Invalid product IDs detected:", productIds);
            alert("⚠️ Some items in the cart have invalid product IDs.");
            return;
        }

        const totalPrice = orderItems.reduce((sum, item) => sum + item.totalPrice, 0);
        console.log("💵 Total Order Price:", totalPrice);

        try {
            console.log("⏳ Placing order...");
            const orderResponse = await axios.post(
                "http://localhost:8081/api/orders/create",
                {
                    user: { id: user.id },
                    address,
                    phoneNumber,
                    totalPrice,
                    orderItems,
                },
                {
                    headers: { Authorization: `Bearer ${token}` },
                }
            );

            alert(`✅ Order placed successfully! Order ID: ${orderResponse.data.orderId}`);
            console.log("📦 Order Response:", orderResponse.data);

            // Remove items from the cart
            console.log("🛒 Removing items from cart...");
            await axios.delete("http://localhost:8081/api/cart/remove", {
                headers: { Authorization: `Bearer ${token}` },
                data: {
                    userId: user.id,
                    productIds,
                },
            });
            console.log("🛒 Cart items successfully removed.");

            // Refresh the cart
            const updatedCartResponse = await axios.get(`http://localhost:8081/api/cart/${user.id}`, {
                headers: { Authorization: `Bearer ${token}` },
            });
            setCart(updatedCartResponse.data?.items || []);
            console.log("✅ Updated cart:", updatedCartResponse.data?.items || []);

            // Redirect to orders page
            setTimeout(() => {
                navigate("/orders");
            }, 500);
        } catch (error) {
            console.error("❌ Error placing order:", error.response?.data || error.message);
        }
    };

    if (loading) {
        return <p>Loading user and cart data...</p>;
    }

    return (
        <div>
            <h1>Checkout</h1>
            <input
                type="text"
                placeholder="Enter your address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
            />
            <input
                type="text"
                placeholder="Enter your phone number"
                value={phoneNumber}
                onChange={(e) => setPhoneNumber(e.target.value)}
            />
            <button onClick={handleOrderSubmit}>Confirm Order</button>
        </div>
    );
};

export default Checkout;
