import { useState, useEffect } from "react";
import axios from "axios";

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
        <div style={{ padding: "20px" }}>
            <h2>Admin Product Management</h2>

            {/* Product Form (Add/Edit) */}
            <div style={{ marginBottom: "20px", border: "1px solid #ccc", padding: "10px" }}>
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
            <table border="1" width="100%">
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
                    products.map((product) => (
                        <tr key={product.id}>
                            <td><img src={product.image} alt={product.name} width="50" /></td>
                            <td>{product.name}</td>
                            <td>{product.description}</td>
                            <td>${product.price}</td>
                            <td>{product.quantity}</td>
                            <td>{product.category}</td>
                            <td>
                                <button onClick={() => handleEditProduct(product)}>Edit</button>
                                <button onClick={() => handleDeleteProduct(product.id)}>Delete</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr><td colSpan="7">No products available.</td></tr>
                )}
                </tbody>
            </table>
        </div>
    );
}
