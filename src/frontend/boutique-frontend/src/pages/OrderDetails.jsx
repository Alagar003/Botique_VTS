import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

const OrderDetails = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { cartItems, user } = location.state || {};

    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [ordering, setOrdering] = useState(false);
    const [error, setError] = useState("");

    const handlePlaceOrder = async () => {
        if (!address.trim()) {
            setError("⚠️ Please enter a valid address.");
            return;
        }

        if (!/^\d{10,}$/.test(phoneNumber)) {
            setError("⚠️ Phone number must be at least 10 digits.");
            return;
        }

        setError(""); // Clear previous errors

        try {
            setOrdering(true);

            const response = await axios.post("http://localhost:8081/api/orders/create", {
                userId: user.id,
                address,
                phoneNumber,
                items: cartItems.map(item => ({
                    productId: item.productId,
                    productName: item.productName,
                    quantity: item.quantity,
                    price: item.price
                }))
            });

            console.log("✅ Order Created:", response.data);
            alert("🎉 Order placed successfully!");
            navigate("/order-list"); // Navigate to confirmation page
        } catch (error) {
            console.error("❌ Order Error:", error.response?.data || error.message);
            setError("⚠️ Failed to place order. Please try again.");
        } finally {
            setOrdering(false);
        }
    };

    return (
        <div style={{ padding: "20px", maxWidth: "400px", margin: "auto" }}>
            <h2>Enter Your Shipping Details</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <label>Address:</label>
            <input
                type="text"
                placeholder="Enter Address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                required
            />

            <label>Phone Number:</label>
            <input
                type="text"
                placeholder="Enter Phone Number"
                value={phoneNumber}
                onChange={(e) => setPhoneNumber(e.target.value)}
                required
            />

            <button onClick={handlePlaceOrder} disabled={ordering}>
                {ordering ? "Placing Order..." : "Place Order"}
            </button>
        </div>
    );
};

export default OrderDetails;
