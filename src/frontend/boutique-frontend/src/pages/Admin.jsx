import React, { useEffect, useState } from "react";
import axios from "axios";
import "../styles/Admin.css"; // Ensure correct path
import { jwtDecode } from "jwt-decode";

const AdminPage = () => {
    const [users, setUsers] = useState([]);
    const [orders, setOrders] = useState([]);
    const [loadingUsers, setLoadingUsers] = useState(true);
    const [loadingOrders, setLoadingOrders] = useState(true);
    const [errorUsers, setErrorUsers] = useState(null);
    const [errorOrders, setErrorOrders] = useState(null);
    const [activeTab, setActiveTab] = useState("users");
    const [username, setUsername] = useState("");

    useEffect(() => {
        const fetchUsers = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                alert("⚠️ You must be logged in.");
                setLoadingUsers(false);
                return;
            }

            try {
                const decoded = jwtDecode(token);
                console.log("Decoded Token:", decoded); // Log token payload

                if (decoded.name) {
                    setUsername(decoded.name);  // Use 'name' since 'username' is missing
                } else if (decoded.email) {
                    setUsername(decoded.email); // Fallback to email
                } else {
                    setUsername("");  // Default value
                }


                const response = await axios.get("http://localhost:8081/api/admin/users", {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setUsers(response.data);
            } catch (err) {
                console.error("Error fetching users:", err);
                setErrorUsers(err.response?.data?.message || "Error fetching users");
            } finally {
                setLoadingUsers(false);
            }
        };

        fetchUsers();
    }, []);

    useEffect(() => {
        const fetchOrders = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                alert("⚠️ You must be logged in.");
                setLoadingOrders(false);
                return;
            }

            try {
                const response = await axios.get("http://localhost:8081/api/admin/orders", {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setOrders(response.data);
            } catch (err) {
                console.error("Error fetching orders:", err);
                setErrorOrders(err.response?.data?.message || "Error fetching orders");
            } finally {
                setLoadingOrders(false);
            }
        };

        fetchOrders();
    }, []);

    return (
        <div className="container">
            <h1>Admin Dashboard</h1>
            <h3>Welcome, {username || "Admin"}!</h3>

            <div className="tabs">
                <button onClick={() => setActiveTab("users")} className={activeTab === "users" ? "active" : ""}>
                    Users
                </button>
                <button onClick={() => setActiveTab("orders")} className={activeTab === "orders" ? "active" : ""}>
                    Orders
                </button>
            </div>

            {activeTab === "users" && (
                <div>
                    {loadingUsers ? (
                        <div>Loading users...</div>
                    ) : errorUsers ? (
                        <div>Error: {errorUsers}</div>
                    ) : (
                        <table>
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Email</th>
                                <th>Role</th>
                            </tr>
                            </thead>
                            <tbody>
                            {users.map((user) => (
                                <tr key={user.id}>
                                    <td>{user.id}</td>
                                    <td>{user.email}</td>
                                    <td>{user.role}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    )}
                </div>
            )}

            {activeTab === "orders" && (
                <div>
                    <h2>Order Details</h2>
                    {loadingOrders ? (
                        <div>Loading orders...</div>
                    ) : errorOrders ? (
                        <div>Error: {errorOrders}</div>
                    ) : orders.length === 0 ? (
                        <p>No orders found.</p>
                    ) : (
                        <table>
                            <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>User ID</th>
                                <th>Address</th>
                                <th>Phone Number</th>
                                <th>Status</th>
                                <th>Total Price</th>
                                <th>Order Items</th>
                            </tr>
                            </thead>
                            <tbody>
                            {orders.map((order) => (
                                <tr key={order.id}>
                                    <td>{order.id}</td>
                                    <td>{order.userId}</td>
                                    <td>{order.address}</td>
                                    <td>{order.phoneNumber}</td>
                                    <td>{order.status || "Pending"}</td>
                                    <td>${order.totalPrice ? order.totalPrice.toFixed(2) : "0.00"}</td>
                                    <td>
                                        <ul>
                                            {order.orderItems.map((item) => (
                                                <li key={item.productId}>
                                                    {item.productName} (Qty: {item.quantity}) - $
                                                    {item.price ? item.price.toFixed(2) : "0.00"}
                                                </li>
                                            ))}
                                        </ul>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    )}
                </div>
            )}
        </div>
    );
};

export default AdminPage;
