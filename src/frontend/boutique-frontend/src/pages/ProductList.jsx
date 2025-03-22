import React, {useContext, useEffect, useState} from "react";
import axios from "axios";
import { useNavigate, useParams, useLocation } from "react-router-dom";
import {CartContext, useCart} from "./CartContext";

const ProductList = () => {
    const { category } = useParams();
    const location = useLocation();
    const navigate = useNavigate();
    // const { handleAddToCart } = useCart(); // Using handleAddToCart from CartContext
    const {handleAddToCart}  = useContext(CartContext);

    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [isAdding, setIsAdding] = useState(false);

    const query = new URLSearchParams(location.search).get("query");

    useEffect(() => {
        const fetchProducts = async () => {
            const token = localStorage.getItem("token");

            if (!token) {
                alert("No token found. Redirecting to login...");
                navigate("/login");
                return;
            }

            try {
                let response;
                if (query) {
                    response = await axios.get(
                        `http://localhost:8081/api/products/search?query=${query}`,
                        { headers: { Authorization: `Bearer ${token}` } }
                    );
                } else {
                    response = await axios.get(
                        `http://localhost:8081/api/products/category/${category}`,
                        { headers: { Authorization: `Bearer ${token}` } }
                    );
                }
                setProducts(response.data);
            } catch (err) {
                if (err.response?.status === 401) {
                    alert("Session expired. Please log in again.");
                    localStorage.removeItem("token");
                    navigate("/login");
                } else {
                    setError("Failed to load products. Please try again later.");
                }
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, [category, query, navigate]);

    if (loading) return <div>Loading products...</div>;
    if (error) return <div>{error}</div>;



    return (
        <div>
            <h1 className="heading">
                {query ? `Search Results for "${query}"` : `${category} Collection`}
            </h1>
            <div className="category-section">
                {products.map((product) => (
                    <div key={product.id}>
                        <div className="category">
                            <img src={product.image} alt={product.name}/>
                            <h2>{product.name}</h2>
                            <p>{product.description}</p>
                            <p>₹{product.price}</p>
                            <button
                                className="sign-in-button"
                                disabled={isAdding} // Disable button while processing
                                onClick={async () => {
                                    if (typeof handleAddToCart === "function") {
                                        setIsAdding(true); // Prevent multiple clicks
                                        await handleAddToCart(product.id, 1);
                                        setIsAdding(false);
                                    } else {
                                        console.warn("handleAddToCart is not a function");
                                    }
                                }}
                            >
                                {isAdding ? "Adding..." : "Add to Cart"}
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProductList;
