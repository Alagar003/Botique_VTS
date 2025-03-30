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
