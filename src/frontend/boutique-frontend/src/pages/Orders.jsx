import React, { useState, useEffect } from "react";

const Orders = () => {
    const [orders, setOrders] = useState([]);
    const [user, setUser] = useState(null); // State for user details
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch user details when the component mounts
    useEffect(() => {
        const fetchUser = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                alert("⚠️ You must be logged in.");
                setLoading(false);
                return;
            }

            try {
                const userResponse = await fetch("https://alagar003.github.io/Botique_VTS/users/user", {
                    headers: { Authorization: `Bearer ${token}` },
                });

                if (userResponse.ok) {
                    const userData = await userResponse.json();
                    console.log("✅ Fetched user data:", userData);
                    setUser(userData); // Save user data in state
                } else {
                    const errorData = await userResponse.json();
                    console.error("❌ Error fetching user details:", errorData.message);
                    setError("Failed to fetch user details.");
                }
            } catch (error) {
                console.error("❌ Network error fetching user details:", error);
                setError("Network error occurred.");
            } finally {
                setLoading(false);
            }
        };

        fetchUser();
    }, []);

    // Fetch orders when user data becomes available
    useEffect(() => {
        const fetchOrders = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                alert("⚠️ You must be logged in.");
                setLoading(false);
                return;
            }

            if (!user?.id) {
                console.error("❌ User ID is not available.");
                setError("User ID is required to fetch orders.");
                setLoading(false);
                return;
            }

            try {
                const response = await fetch(`https://alagar003.github.io/Botique_VTS/orders/user?userId=${user.id}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });

                if (response.ok) {
                    const data = await response.json();
                    console.log("✅ Orders fetched successfully:", data);
                    setOrders(data);
                }
            } catch (error) {
                console.error("❌ Network error fetching orders:", error);
                setError("Network error occurred while fetching orders.");
            } finally {
                setLoading(false);
            }
        };

        if (user?.id) {
            fetchOrders(); // Trigger fetchOrders only when user is available
        }
    }, [user?.id]); // Depend on user.id to trigger fetching orders

    if (loading) return <p>Loading your orders...</p>;
    if (error) return <p style={{ color: "red" }}>{error}</p>;

    return (
        <div>
            <h2>Your Orders</h2>
            {orders.length === 0 ? (
                <p>No orders found.</p>
            ) : (
                <ul style={{ listStyleType: "none", padding: 0 }}>
                    {orders.map((order) => (
                        <li key={order.id} style={styles.orderCard}>
                            <p><b>Order ID:</b> {order.id}</p>
                            <p><b>Total Price:</b> ${order.totalPrice.toFixed(2)}</p> {/* Ensures currency format */}
                            <p><b>Status:</b> {order.status || "Pending"}</p> {/* Default status fallback */}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

// Styles for cleaner UI
const styles = {
    orderCard: {
        border: "1px solid #ddd",
        padding: "15px",
        borderRadius: "8px",
        marginBottom: "10px",
        backgroundColor: "#fefefe",
        boxShadow: "0px 1px 3px rgba(0, 0, 0, 0.1)",
    },
};

export default Orders;
