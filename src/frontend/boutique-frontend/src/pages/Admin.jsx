// // // src/components/UserList.js
// //
// // import "../styles/Admin.css";
// // import React, { useEffect, useState } from 'react';
// // import axios from 'axios';
// //
// // const UserList = () => {
// //     const [users, setUsers] = useState([]);
// //     const [loading, setLoading] = useState(true);
// //     const [error, setError] = useState(null);
// //
// //     useEffect(() => {
// //         const fetchUsers = async () => {
// //             try {
// //                 const response = await axios.get('http://localhost:8081/api/admin/users', {
// //                     headers: {
// //                         'Authorization': `Bearer ${localStorage.getItem('token')}` // Assuming you store the JWT token in local storage
// //                     }
// //                 });
// //                 setUsers(response.data);
// //             } catch (err) {
// //                 // Check if the error response has a message property
// //                 const errorMessage = err.response && err.response.data && err.response.data.message
// //                     ? err.response.data.message
// //                     : 'Error fetching users';
// //                 setError(errorMessage);
// //             } finally {
// //                 setLoading(false);
// //             }
// //         };
// //
// //         fetchUsers();
// //     }, []);
// //
// //     if (loading) return <div>Loading...</div>;
// //     if (error) return <div>Error: {error}</div>;
// //
// //     return (
// //         <div>
// //             <h1>User List</h1>
// //             <table>
// //                 <thead>
// //                 <tr>
// //                     <th>ID</th>
// //                     <th>Email</th>
// //                     <th>Role</th>
// //                 </tr>
// //                 </thead>
// //                 <tbody>
// //                 {users.map(user => (
// //                     <tr key={user.id}>
// //                         <td>{user.id}</td>
// //                         <td>{user.email}</td>
// //                         <td>{user.role}</td>
// //                     </tr>
// //                 ))}
// //                 </tbody>
// //             </table>
// //         </div>
// //     );
// // };
// //
// // export default UserList;
//
// // src/components/UserOrderList.js
//
// import "../styles/Admin.css"; // Ensure you have the correct path to your CSS file
// import React, { useEffect, useState } from 'react';
// import axios from 'axios';
//
// const UserOrderList = () => {
//     const [users, setUsers] = useState([]);
//     const [orders, setOrders] = useState([]);
//     const [loadingUsers, setLoadingUsers] = useState(true);
//     const [loadingOrders, setLoadingOrders] = useState(true);
//     const [errorUsers, setErrorUsers] = useState(null);
//     const [errorOrders, setErrorOrders] = useState(null);
//     const [activeTab, setActiveTab] = useState('users'); // State to manage active tab
//
//     useEffect(() => {
//         const fetchUsers = async () => {
//             try {
//                 const response = await axios.get('http://localhost:8081/api/admin/users', {
//                     headers: {
//                         'Authorization': `Bearer ${localStorage.getItem('token')}`
//                     }
//                 });
//                 setUsers(response.data);
//             } catch (err) {
//                 const errorMessage = err.response && err.response.data && err.response.data.message
//                     ? err.response.data.message
//                     : 'Error fetching users';
//                 setErrorUsers(errorMessage);
//             } finally {
//                 setLoadingUsers(false);
//             }
//         };
//
//         const fetchOrders = async () => {
//             try {
//                 const response = await axios.get('http://localhost:8081/api/orders', {
//                     headers: {
//                         'Authorization': `Bearer ${localStorage.getItem('token')}`
//                     }
//                 });
//                 setOrders(response.data);
//             } catch (err) {
//                 const errorMessage = err.response && err.response.data && err.response.data.message
//                     ? err.response.data.message
//                     : 'Error fetching orders';
//                 setErrorOrders(errorMessage);
//             } finally {
//                 setLoadingOrders(false);
//             }
//         };
//
//         fetchUsers();
//         fetchOrders();
//     }, []);
//
//     const handleTabChange = (tab) => {
//         setActiveTab(tab);
//     };
//
//     return (
//         <div>
//             <h1>User and Order List</h1>
//             <div className="tabs">
//                 <button onClick={() => handleTabChange('users')} className={activeTab === 'users' ? 'active' : ''}>
//                     Users
//                 </button>
//                 <button onClick={() => handleTabChange('orders')} className={activeTab === 'orders' ? 'active' : ''}>
//                     Orders
//                 </button>
//             </div>
//
//             {activeTab === 'users' && (
//                 <div>
//                     {loadingUsers ? (
//                         <div>Loading users...</div>
//                     ) : errorUsers ? (
//                         <div>Error: {errorUsers}</div>
//                     ) : (
//                         <table>
//                             <thead>
//                             <tr>
//                                 <th>ID</th>
//                                 <th>Email</th>
//                                 <th>Role</th>
//                             </tr>
//                             </thead>
//                             <tbody>
//                             {users.map(user => (
//                                 <tr key={user.id}>
//                                     <td>{user.id}</td>
//                                     <td>{user.email}</td>
//                                     <td>{user.role}</td>
//                                 </tr>
//                             ))}
//                             </tbody>
//                         </table>
//                     )}
//                 </div>
//             )}
//
//             {activeTab === 'orders' && (
//                 <div>
//                     {loadingOrders ? (
//                         <div>Loading orders...</div>
//                     ) : errorOrders ? (
//                         <div>Error: {errorOrders}</div>
//                     ) : (
//                         <table>
//                             <thead>
//                             <tr>
//                                 <th>Order ID</th>
//                                 <th>Customer Name</th>
//                                 <th>Status</th>
//                                 <th>Total Amount</th>
//                             </tr>
//                             </thead>
//                             <tbody>
//                             {orders.map(order => (
//                                 <tr key={order.id}>
//                                     <td>{order.id}</td>
//                                     <td>{order.customerName}</td>
//                                     <td>{order.status}</td>
//                                     <td>{order.totalAmount}</td>
//                                 </tr>
//                             ))}
//                             </tbody>
//                         </table>
//                     )}
//                 </div>
//             )}
//         </div>
//     );
// };
//
// export default UserOrderList;


// src/components/UserOrderList.js

// src/components/AdminPage.js
//
// import React, { useEffect, useState } from "react";
// import axios from "axios";
// import "../styles/Admin.css"; // Ensure you have the correct path to your CSS file
//
// const AdminPage = () => {
//     const [users, setUsers] = useState([]);
//     const [orders, setOrders] = useState([]);
//     const [loadingUsers, setLoadingUsers] = useState(true);
//     const [loadingOrders, setLoadingOrders] = useState(true);
//     const [errorUsers, setErrorUsers] = useState(null);
//     const [errorOrders, setErrorOrders] = useState(null);
//     const [activeTab, setActiveTab] = useState("users"); // State to manage active tab
//
//     // Fetch users
//     useEffect(() => {
//         const fetchUsers = async () => {
//             const token = localStorage.getItem("token");
//             if (!token) {
//                 alert("⚠️ You must be logged in.");
//                 setLoadingUsers(false);
//                 return;
//             }
//
//             try {
//                 const response = await axios.get("http://localhost:8081/api/admin/users", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//                 setUsers(response.data);
//             } catch (err) {
//                 const errorMessage = err.response?.data?.message || "Error fetching users";
//                 setErrorUsers(errorMessage);
//             } finally {
//                 setLoadingUsers(false);
//             }
//         };
//
//         fetchUsers();
//     }, []);
//
//     // Fetch orders
//     useEffect(() => {
//         const fetchOrders = async () => {
//             const token = localStorage.getItem("token");
//             if (!token) {
//                 alert("⚠️ You must be logged in.");
//                 setLoadingOrders(false);
//                 return;
//             }
//
//             try {
//                 const response = await axios.get("http://localhost:8081/api/orders", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//                 console.log("Fetched orders:", response.data); // Log the response data
//                 setOrders(response.data);
//             } catch (err) {
//                 const errorMessage = err.response?.data?.message || "Error fetching orders";
//                 setErrorOrders(errorMessage);
//             } finally {
//                 setLoadingOrders(false);
//             }
//         };
//
//         fetchOrders();
//     }, []);
//
//     const handleTabChange = (tab) => {
//         setActiveTab(tab);
//     };
//
//     return (
//         <div className="container">
//             <h1>Admin Dashboard</h1>
//             <div className="tabs">
//                 <button onClick={() => handleTabChange("users")} className={activeTab === "users" ? "active" : ""}>
//                     Users
//                 </button>
//                 <button onClick={() => handleTabChange("orders")} className={activeTab === "orders" ? "active" : ""}>
//                     Orders
//                 </button>
//             </div>
//
//             {activeTab === "users" && (
//                 <div>
//                     {loadingUsers ? (
//                         <div>Loading users...</div>
//                     ) : errorUsers ? (
//                         <div>Error: {errorUsers}</div>
//                     ) : (
//                         <table>
//                             <thead>
//                             <tr>
//                                 <th>ID</th>
//                                 <th>Email</th>
//                                 <th>Role</th>
//                             </tr>
//                             </thead>
//                             <tbody>
//                             {users.map((user) => (
//                                 <tr key={user.id}>
//                                     <td>{user.id}</td>
//                                     <td>{user.email}</td>
//                                     <td>{user.role}</td>
//                                 </tr>
//                             ))}
//                             </tbody>
//                         </table>
//                     )}
//                 </div>
//             )}
//
//             {activeTab === "orders" && (
//                 <div>
//                     <h2>Order Details</h2>
//                     {loadingOrders ? (
//                         <div>Loading orders...</div>
//                     ) : errorOrders ? (
//                         <div>Error: {errorOrders}</div>
//                     ) : orders.length === 0 ? (
//                         <p>No orders found.</p>
//                     ) : (
//                         <table>
//                             <thead>
//                             <tr>
//                                 <th>Order ID</th>
//                                 <th>Customer ID</th>
//                                 <th>Customer Name</th>
//                                 <th>Product ID</th>
//                                 <th>Status</th>
//                                 <th>Total Amount</th>
//                             </tr>
//                             </thead>
//                             <tbody>
//                             {orders.map((order) => (
//                                 <tr key={order.orderId}>
//                                     <td>{order.orderId}</td>
//                                     <td>{order.customerId}</td>
//                                     <td>{order.customerName}</td>
//                                     <td>{order.productId}</td>
//                                     <td>{order.status || "Pending"}</td>
//                                     <td>${order.totalAmount != null ? order.totalAmount.toFixed(2) : "0.00"}</td>
//                                 </tr>
//                             ))}
//                             </tbody>
//                         </table>
//                     )}
//                 </div>
//             )}
//         </div>
//     );
// };
//
// export default AdminPage;




//
// import React, { useEffect, useState } from "react";
// import axios from "axios";
// import "../styles/Admin.css"; // Ensure you have the correct path to your CSS file
//
// const AdminPage = () => {
//     const [users, setUsers] = useState([]);
//     const [orders, setOrders] = useState([]);
//     const [loadingUsers, setLoadingUsers] = useState(true);
//     const [loadingOrders, setLoadingOrders] = useState(true);
//     const [errorUsers, setErrorUsers] = useState(null);
//     const [errorOrders, setErrorOrders] = useState(null);
//     const [activeTab, setActiveTab] = useState("users"); // State to manage active tab
//
//     // Fetch users
//     useEffect(() => {
//         const fetchUsers = async () => {
//             const token = localStorage.getItem("token");
//             if (!token) {
//                 alert("⚠️ You must be logged in.");
//                 setLoadingUsers(false);
//                 return;
//             }
//
//             try {
//                 const response = await axios.get("http://localhost:8081/api/admin/users", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//                 setUsers(response.data);
//             } catch (err) {
//                 const errorMessage = err.response?.data?.message || "Error fetching users";
//                 setErrorUsers(errorMessage);
//             } finally {
//                 setLoadingUsers(false);
//             }
//         };
//
//         fetchUsers();
//     }, []);
//
//     // Fetch orders
//     useEffect(() => {
//         const fetchOrders = async () => {
//             const token = localStorage.getItem("token");
//             if (!token) {
//                 alert("⚠️ You must be logged in.");
//                 setLoadingOrders(false);
//                 return;
//             }
//
//             try {
//                 const response = await axios.get("http://localhost:8081/api/orders", {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//                 console.log("Fetched orders:", response.data); // Log the response data
//                 setOrders(response.data);
//             } catch (err) {
//                 const errorMessage = err.response?.data?.message || "Error fetching orders";
//                 setErrorOrders(errorMessage);
//             } finally {
//                 setLoadingOrders(false);
//             }
//         };
//
//         fetchOrders();
//     }, []);
//
//     const handleTabChange = (tab) => {
//         setActiveTab(tab);
//     };
//
//     return (
//         <div className="container">
//             <h1>Admin Dashboard</h1>
//             <div className="tabs">
//                 <button onClick={() => handleTabChange("users")} className={activeTab === "users" ? "active" : ""}>
//                     Users
//                 </button>
//                 <button onClick={() => handleTabChange("orders")} className={activeTab === "orders" ? "active" : ""}>
//                     Orders
//                 </button>
//             </div>
//
//             {activeTab === "users" && (
//                 <div>
//                     {loadingUsers ? (
//                         <div>Loading users...</div>
//                     ) : errorUsers ? (
//                         <div>Error: {errorUsers}</div>
//                     ) : (
//                         <table>
//                             <thead>
//                             <tr>
//                                 <th>ID</th>
//                                 <th>Username</th> {/* Added Username column */}
//                                 <th>Email</th>
//                                 <th>Role</th>
//                             </tr>
//                             </thead>
//                             <tbody>
//                             {users.map((user) => (
//                                 <tr key={user.id}>
//                                     <td>{user.id}</td>
//                                     <td>{user.username || "N/A"}</td> {/* Displaying Username */}
//                                     <td>{user.email}</td>
//                                     <td>{user.role}</td>
//                                 </tr>
//                             ))}
//                             </tbody>
//                         </table>
//                     )}
//                 </div>
//             )}
//
//             {activeTab === "orders" && (
//                 <div>
//                     <h2>Order Details</h2>
//                     {loadingOrders ? (
//                         <div>Loading orders...</div>
//                     ) : errorOrders ? (
//                         <div>Error: {errorOrders}</div>
//                     ) : orders.length === 0 ? (
//                         <p>No orders found.</p>
//                     ) : (
//                         <table>
//                             <thead>
//                             <tr>
//                                 <th>Order ID</th>
//                                 <th>User ID</th>
//                                 <th>Address</th>
//                                 <th>Phone Number</th>
//                                 <th>Status</th>
//                                 <th>Total Price</th>
//                                 <th>Order Items</th>
//                             </tr>
//                             </thead>
//                             <tbody>
//                             {orders.map((order) => (
//                                 <tr key={order.id}>
//                                     <td>{order.id}</td>
//                                     <td>{order.userId}</td>
//                                     <td>{order.address}</td>
//                                     <td>{order.phoneNumber}</td>
//                                     <td>{order.status || "Pending"}</td>
//                                     <td>${order.totalPrice.toFixed(2)}</td>
//                                     <td>
//                                         <ul>
//                                             {order.orderItems.map((item) => (
//                                                 <li key={item.productId}>
//                                                     {item.productName} (Qty: {item.quantity}) - ${item.price.toFixed(2)}
//                                                 </li>
//                                             ))}
//                                         </ul>
//                                     </td>
//                                 </tr>
//                             ))}
//                             </tbody>
//                         </table>
//                     )}
//                 </div>
//             )}
//         </div>
//     );
// };
//
// export default AdminPage;





import React, { useEffect, useState } from "react";
import axios from "axios";
import "../styles/Admin.css"; // Ensure you have the correct path to your CSS file

const AdminPage = () => {
    const [users, setUsers] = useState([]);
    const [orders, setOrders] = useState([]);
    const [loadingUsers, setLoadingUsers] = useState(true);
    const [loadingOrders, setLoadingOrders] = useState(true);
    const [errorUsers, setErrorUsers] = useState(null);
    const [errorOrders, setErrorOrders] = useState(null);
    const [activeTab, setActiveTab] = useState("users"); // State to manage active tab

    // Fetch users
    useEffect(() => {
        const fetchUsers = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                alert("⚠️ You must be logged in.");
                setLoadingUsers(false);
                return;
            }

            try {
                const response = await axios.get("http://localhost:8081/api/admin/users", {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setUsers(response.data);
            } catch (err) {
                const errorMessage = err.response?.data?.message || "Error fetching users";
                setErrorUsers(errorMessage);
            } finally {
                setLoadingUsers(false);
            }
        };

        fetchUsers();
    }, []);

    // Fetch orders
    useEffect(() => {
        const fetchOrders = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                alert("⚠️ You must be logged in.");
                setLoadingOrders(false);
                return;
            }

            try {
                // Updated endpoint to match your backend
                const response = await axios.get("http://localhost:8081/api/admin/orders", {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log("Fetched orders:", response.data); // Log the response data
                setOrders(response.data);
            } catch (err) {
                const errorMessage = err.response?.data?.message || "Error fetching orders";
                setErrorOrders(errorMessage);
            } finally {
                setLoadingOrders(false);
            }
        };

        fetchOrders();
    }, []);

    const handleTabChange = (tab) => {
        setActiveTab(tab);
    };

    return (
        <div className="container">
            <h1>Admin Dashboard</h1>
            <div className="tabs">
                <button onClick={() => handleTabChange("users")} className={activeTab === "users" ? "active" : ""}>
                    Users
                </button>
                <button onClick={() => handleTabChange("orders")} className={activeTab === "orders" ? "active" : ""}>
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
                                <th>Username</th> {/* Added Username column */}
                                <th>Email</th>
                                <th>Role</th>
                            </tr>
                            </thead>
                            <tbody>
                            {users.map((user) => (
                                <tr key={user.id}>
                                    <td>{user.id}</td>
                                    <td>{user.username || "N/A"}</td> {/* Displaying Username */}
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
                                    <td>${order.totalPrice ? order.totalPrice.toFixed(2) : "0.00"}</td> {/* Add null check */}
                                    <td>
                                        <ul>
                                            {order.orderItems.map((item) => (
                                                <li key={item.productId}>
                                                    {item.productName} (Qty: {item.quantity}) - ${item.price ? item.price.toFixed(2) : "0.00"} {/* Add null check */}
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