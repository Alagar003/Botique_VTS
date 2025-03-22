import React, { useState, useEffect } from "react";
import { Outlet, useNavigate, Link } from "react-router-dom";
import "../styles/Home.css";
import logo from "../images/logo.jpg";

const Layout = () => {
    const [user, setUser] = useState(null);
    const [showDropdown, setShowDropdown] = useState(false);
    const [searchQuery, setSearchQuery] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch user details if logged in
        const fetchUserDetails = async () => {
            const token = localStorage.getItem("token");
            if (token) {
                try {
                    const response = await fetch("http://localhost:8081/api/users/user", {
                        headers: {
                            Authorization: `Bearer ${token}`,
                        },
                    });
                    if (response.ok) {
                        const data = await response.json();
                        setUser(data); // Set user details
                    } else {
                        console.error("Failed to fetch user details");
                    }
                } catch (error) {
                    console.error("Error fetching user details:", error);
                }
            }
        };

        fetchUserDetails();
    }, []);

    const handleLogout = () => {
        localStorage.removeItem("token"); // Clear token
        alert("Logged out successfully");
        navigate("/login"); // Redirect to login page
        window.location.reload(); // Refresh to reflect logged-out state
    };

    const handleProtectedNavigation = (link) => {
        const token = localStorage.getItem("token");
        if (!token) {
            alert("Please log in to access this page.");
            navigate("/login");
        } else {
            navigate(link); // Navigate if user is logged in
        }
    };

    const handleSearch = () => {
        if (searchQuery.trim()) {
            navigate(`/search?query=${searchQuery}`);
            setSearchQuery("");
        } else {
            alert("Please enter a search term.");
        }

    };


    return (
        <div>
            {/* Header */}
            <header className="header">
                <div>
                    <h1>Exclusive For Women!!!</h1>
                </div>
                <div className="h-logo">
                    <div>
                        <img className="img-style" src={logo} alt="Lotus Boutique Logo"/>
                    </div>
                    <div>
                        <h1>LOTUS<br/> BOUTIQUE</h1>
                    </div>
                </div>
                <div className="container3">
                    <h1>Exclusive for Silks!!!</h1>
                </div>
            </header>

            {/* Navbar */}
            <nav className="navbar">
                <div className="navbar-container">
                    <ul className="menu">
                        <div className="icons">
                            <li><Link to="/">Home</Link></li>
                            <li onClick={() => handleProtectedNavigation("/products/women")}>Women</li>
                            <li onClick={() => handleProtectedNavigation("/products/men")}>Men</li>
                            <li onClick={() => handleProtectedNavigation("/products/boys")}>Boys</li>
                            <li onClick={() => handleProtectedNavigation("/products/girls")}>Girls</li>
                            <li><Link to="/support">Support</Link></li>
                            <li><Link to="/about">About Us</Link></li>
                            <li><Link to="/lookbooks">Look Books</Link></li>
                            <div className="search-bar">
                                <input
                                    type="text"
                                    value={searchQuery}
                                    onChange={(e) => setSearchQuery(e.target.value)}
                                    placeholder="Search for products..."
                                    className="search-input"
                                />
                                <button onClick={handleSearch} className="search-button">Search</button>
                            </div>

                            {/* User Icon */}
                            <div className="user-section">
                                <div
                                    className="icon user"
                                    onClick={() => setShowDropdown(!showDropdown)}
                                >
                                    👤
                                </div>
                                {showDropdown && user && (
                                    <div className="dropdown-menu">
                                        <p><b>Email:</b> {user.email}</p>
                                    </div>
                                )}
                            </div>

                            {/* Cart Link */}
                            <li>
                                <Link to="/cart" className="icon cart">
                                    🛒 Cart
                                </Link>
                            </li>

                            {/* Sign-Out Link */}
                            <li>
                                <Link to="/login" onClick={handleLogout} className="icon logout">
                                    🚪 Sign Out
                                </Link>
                            </li>
                        </div>
                    </ul>


                </div>
            </nav>

            {/* Dynamic Content */}
            <Outlet/>


            <div className="down-nav">
                <div>
                    <ul>
                        <h3>Categories</h3>
                        <li className="liststyle" onClick={() => handleProtectedNavigation("/products/women")}>Women's Collections
                        </li>
                        <br/>
                        <li className="liststyle" onClick={() => handleProtectedNavigation("/products/men")}>Men's Collections</li>
                        <br/>
                        <li className="liststyle" onClick={() => handleProtectedNavigation("/products/boys")}>Boys's Collections</li><br/>
                        <li className="liststyle" onClick={() => handleProtectedNavigation("/products/girls")}>Girls Collections
                        </li>
                    </ul>
                </div>
                <div>
                    <ul>
                        <br/><br/>
                        <li className="liststyle" onClick={() => handleProtectedNavigation("/about")}>AboutUs</li>
                    </ul>
                </div>
                <div>
                    <ul>
                        <br/><br/>
                        <li className="liststyle" onClick={() => handleProtectedNavigation("")}>SignUp</li>

                    </ul>
                </div>
                <div>
                    <h3>Contact</h3>
                    <h3>Coimbatore :</h3>
                    <h3>TamilNadu, India</h3>
                    <h3> 📞 +91 96009 73736</h3>
                    <h3> 📞 +91 95008 61222</h3>
                    <h3> ✉ contact@lotusonline.com</h3>

                </div>


            </div>


        </div>
    );
};

export default Layout;
