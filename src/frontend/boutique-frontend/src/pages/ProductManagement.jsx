// // import { useState, useEffect } from "react";
// // import axios from "axios";
// //
// // export default function ProductManagement() {
// //     const [products, setProducts] = useState([]);
// //     const [token, setToken] = useState(localStorage.getItem("token"));
// //     const [editingProduct, setEditingProduct] = useState(null);
// //     const [formData, setFormData] = useState({
// //         name: "",
// //         description: "",
// //         price: "",
// //         quantity: "",
// //         image: "",
// //         category: "",
// //     });
// //
// //     console.log("Stored Token:", localStorage.getItem("token"));
// //
// //
// //
// //     // const API_URL = "http://localhost:8081/api/products";
// //     //
// //     // useEffect(() => {
// //     //     fetchProducts();
// //     // }, []);
// //     //
// //     // const fetchProducts = async () => {
// //     //     try {
// //     //         const response = await axios.get(API_URL);
// //     //         setProducts(Array.isArray(response.data) ? response.data : response.data.content || []);
// //     //     } catch (error) {
// //     //         console.error("Error fetching products:", error);
// //     //     }
// //     // };
// //     //
// //     // // Handle form input changes
// //     // const handleChange = (e) => {
// //     //     setFormData({ ...formData, [e.target.name]: e.target.value });
// //     // };
// //     //
// //     // // Add or Update Product
// //     // const handleSaveProduct = async () => {
// //     //     if (editingProduct) {
// //     //         // Update existing product
// //     //         try {
// //     //             await axios.put(`${API_URL}/update/${editingProduct.id}`, formData);
// //     //             fetchProducts();
// //     //             resetForm();
// //     //         } catch (error) {
// //     //             console.error("Error updating product:", error);
// //     //         }
// //     //     } else {
// //     //         // Add new product
// //     //         try {
// //     //             await axios.post(`${API_URL}/add`, formData);
// //     //             fetchProducts();
// //     //             resetForm();
// //     //         } catch (error) {
// //     //             console.error("Error adding product:", error);
// //     //         }
// //     //     }
// //     // };
// //     //
// //     // // Delete Product
// //     // const handleDeleteProduct = async (id) => {
// //     //     try {
// //     //         await axios.delete(`${API_URL}/delete/${id}`);
// //     //         fetchProducts();
// //     //     } catch (error) {
// //     //         console.error("Error deleting product:", error);
// //     //     }
// //     // };
// //     //
// //     // // Edit Product
// //     // const handleEditProduct = (product) => {
// //     //     setEditingProduct(product);
// //     //     setFormData({ ...product });
// //     // };
// //
// //     const API_URL = "http://localhost:8081/api/admin";
// //     // const token = localStorage.getItem("token"); // Retrieve JWT token from storage
// //
// //     const authHeaders = {
// //         headers: {
// //             Authorization: `Bearer ${token}`,
// //             "Content-Type": "application/json",
// //         },
// //     };
// //
// //     useEffect(() => {
// //         const storedToken = localStorage.getItem("token");
// //         if (!storedToken) {
// //             console.error("❌ No token found in localStorage");
// //             return;
// //         }
// //         setToken(storedToken); // Store the token in state if needed
// //         fetchProducts(storedToken);
// //     }, []);
// //
// //
// //     const fetchProducts = async (storedToken) => {
// //         if (!storedToken) {
// //             console.error("❌ No token available, skipping API call.");
// //             return;
// //         }
// //         try {
// //             const response = await axios.get(API_URL, {
// //                 headers: {
// //                     Authorization: `Bearer ${storedToken}`,
// //                     "Content-Type": "application/json",
// //                 },
// //             });
// //             console.log("✅ API Response:", response.data);
// //             setProducts(Array.isArray(response.data) ? response.data : response.data.content || []);
// //         } catch (error) {
// //             console.error("❌ Error fetching products:", error.response?.data || error.message);
// //         }
// //     };
// //
// //
// //     // Handle form input changes
// //     const handleChange = (e) => {
// //         setFormData({ ...formData, [e.target.name]: e.target.value });
// //     };
// //
// //     // Add or Update Product
// //     const handleSaveProduct = async () => {
// //         if (editingProduct) {
// //             // Update existing product
// //             try {
// //                 await axios.put(`${API_URL}/update/${editingProduct.id}`, formData, authHeaders);
// //                 fetchProducts();
// //                 resetForm();
// //             } catch (error) {
// //                 console.error("Error updating product:", error);
// //             }
// //         } else {
// //             // Add new product
// //             try {
// //                 await axios.post(`${API_URL}/add`, formData, authHeaders);
// //                 fetchProducts();
// //                 resetForm();
// //             } catch (error) {
// //                 console.error("Error adding product:", error);
// //             }
// //         }
// //     };
// //
// //     // Delete Product
// //     const handleDeleteProduct = async (id) => {
// //         try {
// //             await axios.delete(`${API_URL}/delete/${id}`, authHeaders);
// //             fetchProducts();
// //         } catch (error) {
// //             console.error("Error deleting product:", error);
// //         }
// //     };
// //
// //     // Edit Product
// //     const handleEditProduct = (product) => {
// //         setEditingProduct(product);
// //         setFormData({ ...product });
// //     };
// //
// //     // Reset form
// //     const resetForm = () => {
// //         setFormData({
// //             name: "",
// //             description: "",
// //             price: "",
// //             quantity: "",
// //             image: "",
// //             category: "",
// //         });
// //         setEditingProduct(null);
// //     };
// //
// //     return (
// //         <div style={{ padding: "20px" }}>
// //             <h2>Admin Product Management</h2>
// //
// //             {/* Product Form (Add/Edit) */}
// //             <div style={{ marginBottom: "20px", border: "1px solid #ccc", padding: "10px" }}>
// //                 <h3>{editingProduct ? "Edit Product" : "Add New Product"}</h3>
// //                 <input type="text" name="name" placeholder="Name" value={formData.name} onChange={handleChange} />
// //                 <input type="text" name="description" placeholder="Description" value={formData.description} onChange={handleChange} />
// //                 <input type="number" name="price" placeholder="Price" value={formData.price} onChange={handleChange} />
// //                 <input type="number" name="quantity" placeholder="Quantity" value={formData.quantity} onChange={handleChange} />
// //                 <input type="text" name="category" placeholder="Category" value={formData.category} onChange={handleChange} />
// //                 <input type="text" name="image" placeholder="Image URL" value={formData.image} onChange={handleChange} />
// //
// //                 <button onClick={handleSaveProduct}>{editingProduct ? "Update Product" : "Add Product"}</button>
// //                 {editingProduct && <button onClick={resetForm}>Cancel</button>}
// //             </div>
// //
// //             {/* Product Table */}
// //             <table border="1" width="100%">
// //                 <thead>
// //                 <tr>
// //                     <th>Image</th>
// //                     <th>Name</th>
// //                     <th>Description</th>
// //                     <th>Price</th>
// //                     <th>Quantity</th>
// //                     <th>Category</th>
// //                     <th>Actions</th>
// //                 </tr>
// //                 </thead>
// //                 <tbody>
// //                 {products.length > 0 ? (
// //                     products.map((product) => (
// //                         <tr key={product.id}>
// //                             <td><img src={product.image} alt={product.name} width="50" /></td>
// //                             <td>{product.name}</td>
// //                             <td>{product.description}</td>
// //                             <td>${product.price}</td>
// //                             <td>{product.quantity}</td>
// //                             <td>{product.category}</td>
// //                             <td>
// //                                 <button onClick={() => handleEditProduct(product)}>Edit</button>
// //                                 <button onClick={() => handleDeleteProduct(product.id)}>Delete</button>
// //                             </td>
// //                         </tr>
// //                     ))
// //                 ) : (
// //                     <tr><td colSpan="7">No products available.</td></tr>
// //                 )}
// //                 </tbody>
// //             </table>
// //         </div>
// //     );
// // }
//
//
// // import { useState, useEffect } from "react";
// // import axios from "axios";
// //
// // export default function ProductManagement() {
// //     const [products, setProducts] = useState([]);
// //     const [editingProduct, setEditingProduct] = useState(null);
// //     const [formData, setFormData] = useState({
// //         name: "",
// //         description: "",
// //         price: "",
// //         quantity: "",
// //         image: "",
// //         category: "",
// //     });
// //
// //     const API_URL = "http://localhost:8081/api/admin";
// //
// //     useEffect(() => {
// //         fetchProducts();
// //     }, []);
// //
// //     const fetchProducts = async () => {
// //         const token = localStorage.getItem("token");
// //         console.log("Stored Token:", localStorage.getItem("token"));
// //
// //         if (!token) {
// //             console.error("❌ No token found in localStorage");
// //             return;
// //         }
// //         try {
// //             const response = await axios.get(API_URL, {
// //                 headers: {
// //                     Authorization: `Bearer ${token}`,
// //                     "Content-Type": "application/json",
// //                 },
// //             });
// //             setProducts(response.data);
// //         } catch (error) {
// //             console.error("❌ Error fetching products:", error.response?.data || error.message);
// //         }
// //     };
// //
// //     const handleChange = (e) => {
// //         setFormData({ ...formData, [e.target.name]: e.target.value });
// //     };
// //
// //     const handleSaveProduct = async () => {
// //         const token = localStorage.getItem("token");
// //         if (!token) return console.error("❌ No token found in localStorage");
// //
// //         try {
// //             if (editingProduct) {
// //                 await axios.put(`${API_URL}/update/${editingProduct.id}`, formData, {
// //                     headers: { Authorization: `Bearer ${token}` },
// //                 });
// //             } else {
// //                 await axios.post(`${API_URL}/add`, formData, {
// //                     headers: { Authorization: `Bearer ${token}` },
// //                 });
// //             }
// //             fetchProducts();
// //             resetForm();
// //         } catch (error) {
// //             console.error("❌ Error saving product:", error.response?.data || error.message);
// //         }
// //     };
// //
// //     const handleDeleteProduct = async (id) => {
// //         const token = localStorage.getItem("token");
// //         if (!token) return console.error("❌ No token found in localStorage");
// //
// //         try {
// //             await axios.delete(`${API_URL}/delete/${id}`, {
// //                 headers: { Authorization: `Bearer ${token}` },
// //             });
// //             fetchProducts();
// //         } catch (error) {
// //             console.error("❌ Error deleting product:", error.response?.data || error.message);
// //         }
// //     };
// //
// //     const handleEditProduct = (product) => {
// //         setEditingProduct(product);
// //         setFormData({ ...product });
// //     };
// //
// //     const resetForm = () => {
// //         setFormData({ name: "", description: "", price: "", quantity: "", image: "", category: "" });
// //         setEditingProduct(null);
// //     };
// //
// //     return (
// //         <div style={{ padding: "20px" }}>
// //             <h2>Admin Product Management</h2>
// //
// //             <div style={{ marginBottom: "20px", border: "1px solid #ccc", padding: "10px" }}>
// //                 <h3>{editingProduct ? "Edit Product" : "Add New Product"}</h3>
// //                 <input type="text" name="name" placeholder="Name" value={formData.name} onChange={handleChange} />
// //                 <input type="text" name="description" placeholder="Description" value={formData.description} onChange={handleChange} />
// //                 <input type="number" name="price" placeholder="Price" value={formData.price} onChange={handleChange} />
// //                 <input type="number" name="quantity" placeholder="Quantity" value={formData.quantity} onChange={handleChange} />
// //                 <input type="text" name="category" placeholder="Category" value={formData.category} onChange={handleChange} />
// //                 <input type="text" name="image" placeholder="Image URL" value={formData.image} onChange={handleChange} />
// //                 <button onClick={handleSaveProduct}>{editingProduct ? "Update Product" : "Add Product"}</button>
// //                 {editingProduct && <button onClick={resetForm}>Cancel</button>}
// //             </div>
// //
// //             <table border="1" width="100%">
// //                 <thead>
// //                 <tr>
// //                     <th>Image</th>
// //                     <th>Name</th>
// //                     <th>Description</th>
// //                     <th>Price</th>
// //                     <th>Quantity</th>
// //                     <th>Category</th>
// //                     <th>Actions</th>
// //                 </tr>
// //                 </thead>
// //                 <tbody>
// //                 {products.length > 0 ? (
// //                     products.map((product) => (
// //                         <tr key={product.id}>
// //                             <td><img src={product.image} alt={product.name} width="50" /></td>
// //                             <td>{product.name}</td>
// //                             <td>{product.description}</td>
// //                             <td>${product.price}</td>
// //                             <td>{product.quantity}</td>
// //                             <td>{product.category}</td>
// //                             <td>
// //                                 <button onClick={() => handleEditProduct(product)}>Edit</button>
// //                                 <button onClick={() => handleDeleteProduct(product.id)}>Delete</button>
// //                             </td>
// //                         </tr>
// //                     ))
// //                 ) : (
// //                     <tr><td colSpan="7">No products available.</td></tr>
// //                 )}
// //                 </tbody>
// //             </table>
// //         </div>
// //     );
// // }
//
//
// import { useState, useEffect } from "react";
// import axios from "axios";
//
// export default function ProductManagement() {
//     const [products, setProducts] = useState([]);
//     const [editingProduct, setEditingProduct] = useState(null);
//     const [formData, setFormData] = useState({
//         name: "",
//         description: "",
//         price: "",
//         quantity: "",
//         image: "",
//         category: "",
//     });
//     const [error, setError] = useState(null);
//
//     const API_URL = "http://localhost:8081/api/admin";
//
//     useEffect(() => {
//         fetchProducts();
//     }, []);
//
//     const fetchProducts = async () => {
//         const token = localStorage.getItem("token");
//         console.log("Stored Token:", token); // Check if token exists
//
//         if (!token) {
//             console.error("❌ No token found in localStorage");
//             return;
//         }
//
//         try {
//             const response = await axios.get(API_URL, {
//                 headers: {
//                     Authorization: `Bearer ${token}`,
//                     "Content-Type": "application/json",
//                 },
//             });
//             setProducts(response.data);
//         } catch (error) {
//             console.error("❌ Error fetching products:", error.response?.data || error.message);
//
//             // Check if the token is invalid
//             if (error.response?.status === 403) {
//                 console.error("❌ Unauthorized: Token may be invalid or expired.");
//                 alert("Session expired. Please log in again.");
//                 localStorage.removeItem("token"); // Remove invalid token
//                 window.location.href = "/login";  // Redirect to login page
//             }
//         }
//     };
//
//
//     const handleChange = (e) => {
//         setFormData({ ...formData, [e.target.name]: e.target.value });
//     };
//
//     const handleSaveProduct = async () => {
//         const token = localStorage.getItem("token");
//         if (!token) return console.error("❌ No token found in localStorage");
//
//         try {
//             if (editingProduct) {
//                 await axios.put(`${API_URL}/update/${editingProduct.id}`, formData, {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//             } else {
//                 await axios.post(`${API_URL}/add`, formData, {
//                     headers: { Authorization: `Bearer ${token}` },
//                 });
//             }
//             fetchProducts();
//             resetForm();
//         } catch (error) {
//             console.error("❌ Error saving product:", error.response?.data || error.message);
//             setError("Failed to save product. Please check your input.");
//         }
//     };
//
//     const handleDeleteProduct = async (id) => {
//         const token = localStorage.getItem("token");
//         if (!token) return console.error("❌ No token found in localStorage");
//
//         try {
//             await axios.delete(`${API_URL}/delete/${id}`, {
//                 headers: { Authorization: `Bearer ${token}` },
//             });
//             fetchProducts();
//         } catch (error) {
//             console.error("❌ Error deleting product:", error.response?.data || error.message);
//             setError("Failed to delete product. Please try again.");
//         }
//     };
//
//     const handleEditProduct = (product) => {
//         setEditingProduct(product);
//         setFormData({ ...product });
//     };
//
//     const resetForm = () => {
//         setFormData({ name: "", description: "", price: "", quantity: "", image: "", category: "" });
//         setEditingProduct(null);
//     };
//
//     return (
//         <div style={{ padding: "20px" }}>
//             <h2>Admin Product Management</h2>
//
//             {error && <p style={{ color: "red", fontWeight: "bold" }}>{error}</p>}
//
//             <div style={{ marginBottom: "20px", border: "1px solid #ccc", padding: "10px" }}>
//                 <h3>{editingProduct ? "Edit Product" : "Add New Product"}</h3>
//                 <input type="text" name="name" placeholder="Name" value={formData.name} onChange={handleChange} />
//                 <input type="text" name="description" placeholder="Description" value={formData.description} onChange={handleChange} />
//                 <input type="number" name="price" placeholder="Price" value={formData.price} onChange={handleChange} />
//                 <input type="number" name="quantity" placeholder="Quantity" value={formData.quantity} onChange={handleChange} />
//                 <input type="text" name="category" placeholder="Category" value={formData.category} onChange={handleChange} />
//                 <input type="text" name="image" placeholder="Image URL" value={formData.image} onChange={handleChange} />
//
//                 <button onClick={handleSaveProduct} style={{ margin: "5px", padding: "8px 12px", cursor: "pointer" }}>
//                     {editingProduct ? "Update Product" : "Add Product"}
//                 </button>
//                 {editingProduct && (
//                     <button onClick={resetForm} style={{ padding: "8px 12px", cursor: "pointer" }}>
//                         Cancel
//                     </button>
//                 )}
//             </div>
//
//             <table border="1" width="100%">
//                 <thead>
//                 <tr>
//                     <th>Image</th>
//                     <th>Name</th>
//                     <th>Description</th>
//                     <th>Price</th>
//                     <th>Quantity</th>
//                     <th>Category</th>
//                     <th>Actions</th>
//                 </tr>
//                 </thead>
//                 <tbody>
//                 {products.length > 0 ? (
//                     products.map((product) => (
//                         <tr key={product.id}>
//                             <td><img src={product.image} alt={product.name} width="50" /></td>
//                             <td>{product.name}</td>
//                             <td>{product.description}</td>
//                             <td>${product.price}</td>
//                             <td>{product.quantity}</td>
//                             <td>{product.category}</td>
//                             <td>
//                                 <button onClick={() => handleEditProduct(product)} style={{ marginRight: "5px" }}>
//                                     Edit
//                                 </button>
//                                 <button onClick={() => handleDeleteProduct(product.id)} style={{ color: "red" }}>
//                                     Delete
//                                 </button>
//                             </td>
//                         </tr>
//                     ))
//                 ) : (
//                     <tr>
//                         <td colSpan="7">No products available.</td>
//                     </tr>
//                 )}
//                 </tbody>
//             </table>
//         </div>
//     );
// }


import { useState, useEffect } from "react";
import axios from "axios";
import "../styles/ProductManagement.css";

export default function ProductManagement() {
    const [products, setProducts] = useState([]);
    const [editingProduct, setEditingProduct] = useState(null);
    const [formData, setFormData] = useState({
        name: "",
        description: "",
        price: "",
        quantity: "",
        image: "",
        category: "",
    });

    const API_URL = "http://localhost:8081/api/products";

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await axios.get(API_URL);
            setProducts(Array.isArray(response.data) ? response.data : response.data.content || []);
        } catch (error) {
            console.error("Error fetching products:", error);
        }
    };

    // Handle form input changes
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    // Add or Update Product
    const handleSaveProduct = async () => {
        if (editingProduct) {
            // Update existing product
            try {
                await axios.put(`${API_URL}/update/${editingProduct.id}`, formData);
                fetchProducts();
                resetForm();
            } catch (error) {
                console.error("Error updating product:", error);
            }
        } else {
            // Add new product
            try {
                await axios.post(`${API_URL}/add`, formData);
                fetchProducts();
                resetForm();
            } catch (error) {
                console.error("Error adding product:", error);
            }
        }
    };

    // Delete Product
    const handleDeleteProduct = async (id) => {
        try {
            await axios.delete(`${API_URL}/delete/${id}`);
            fetchProducts();
        } catch (error) {
            console.error("Error deleting product:", error);
        }
    };

    // Edit Product
    const handleEditProduct = (product) => {
        setEditingProduct(product);
        setFormData({ ...product });
    };

    // Reset form
    const resetForm = () => {
        setFormData({
            name: "",
            description: "",
            price: "",
            quantity: "",
            image: "",
            category: "",
        });
        setEditingProduct(null);
    };

    return (
        <div className="admin-container">
            <h2>Admin Product Management</h2>

            {/* Product Form */}
            <div className="product-form">
                <h3>{editingProduct ? "Edit Product" : "Add New Product"}</h3>
                <input type="text" name="name" placeholder="Name" value={formData.name} onChange={handleChange} />
                <input type="text" name="description" placeholder="Description" value={formData.description} onChange={handleChange} />
                <input type="number" name="price" placeholder="Price" value={formData.price} onChange={handleChange} />
                <input type="number" name="quantity" placeholder="Quantity" value={formData.quantity} onChange={handleChange} />
                <input type="text" name="category" placeholder="Category" value={formData.category} onChange={handleChange} />
                <input type="text" name="image" placeholder="Image URL" value={formData.image} onChange={handleChange} />

                <button onClick={handleSaveProduct}>{editingProduct ? "Update Product" : "Add Product"}</button>
                {editingProduct && <button onClick={resetForm}>Cancel</button>}
            </div>

            {/* Product Table */}
            <table className="product-table">
                <thead>
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {products.length > 0 ? (
                    products.map((product) => {
                        console.log("Product:", product); // Debugging
                        return (
                            <tr key={product.id}>
                                <td><img src={product.image} alt={product.name}/></td>
                                <td>{product.name}</td>
                                <td>{product.description}</td>
                                <td>${product.price}</td>
                                <td>{product.quantity}</td>
                                <td>{product.category || "No Category"}</td>
                                {/* Add a fallback */}
                                <td className="product-actions">
                                    <button className="edit" onClick={() => handleEditProduct(product)}>Edit</button>
                                    <button className="delete" onClick={() => handleDeleteProduct(product.id)}>Delete
                                    </button>
                                </td>
                            </tr>
                        );
                    })
                ) : (
                    <tr>
                        <td colSpan="7">No products available.</td>
                    </tr>
                )}
                </tbody>

            </table>
        </div>
    );
}
