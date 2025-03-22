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