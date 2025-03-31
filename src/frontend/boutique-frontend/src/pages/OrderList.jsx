import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

const OrderList = () => {
    const location = useLocation();
    const navigate = useNavigate();

    // Get user from location, localStorage, or fallback method
    const { user } = location.state || {};
    const userId = user?.id || localStorage.getItem("userId");

    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        console.log("User ID in OrderList:", userId); // Debugging

        if (!userId) {
            setError("⚠️ User ID is missing. Please log in.");
            setLoading(false);
            return;
        }

        const fetchOrders = async () => {
            try {
                const response = await axios.get(`https://alagar003.github.io/Botique_VTS/orders/user/${userId}`);
                console.log("Orders received:", response.data);
                setOrders(response.data);
            } catch (err) {
                console.error("Error fetching orders:", err);
                setError("⚠️ Failed to fetch orders. Please try again.");
            } finally {
                setLoading(false);
            }
        };

        fetchOrders();
    }, [userId]);

    if (loading) return <p>Loading orders...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <h2>Your Orders</h2>
            {orders.length === 0 ? (
                <p>No orders found.</p>
            ) : (
                <ul>
                    {orders.map(order => (
                        <li key={order.id}>
                            {order.productName} - {order.quantity}
                        </li>
                    ))}
                </ul>
            )}
            <button onClick={() => navigate("/")}>Go Back</button>
        </div>
    );
};

export default OrderList;
