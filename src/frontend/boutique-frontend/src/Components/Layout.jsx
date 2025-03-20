// import React, { useState, useEffect } from "react";
// import { Outlet, useNavigate, Link } from "react-router-dom";
// import "../styles/Home.css";
// import logo from "../images/logo.jpg";
//
// const Layout = () => {
//     const [user, setUser] = useState(null);
//     const [showUserDetails, setShowUserDetails] = useState(false);
//     const [cart, setCart] = useState([]);
//     const [searchQuery, setSearchQuery] = useState("");
//     const navigate = useNavigate();
//
//     useEffect(() => {
//         const token = localStorage.getItem("token");
//         if (token) {
//             fetchUserDetails();
//             fetchCartDetails();
//         }
//     }, []);
//
//     const fetchUserDetails = async () => {
//         const token = localStorage.getItem("token");
//         if (!token) return;
//
//         try {
//             const response = await fetch("http://localhost:8081/api/users/user", {
//                 method: "GET",
//                 headers: {
//                     Authorization: `Bearer ${token}`,
//                     "Content-Type": "application/json",
//                 },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch user details");
//
//             const data = await response.json();
//             console.log("🔍 API Response:", data); // Check API response
//
//             setUser(data); // Set user state
//             console.log("✅ Updated User State:", data); // Check if user.id is correct
//         } catch (error) {
//             console.error(error.message);
//         }
//     };
//
//
//     const fetchCartDetails = async () => {
//         try {
//             const token = localStorage.getItem("token");
//             if (!token || !user?.id) return; // Ensure user.id exists
//
//             const response = await fetch(`http://localhost:8081/api/cart/${user.id}`, {
//                 headers: { Authorization: `Bearer ${token}` },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch cart");
//
//             const cartData = await response.json();
//             setCart(cartData.cartItems || []);
//         } catch (error) {
//             console.error("❌ Error fetching cart:", error);
//         }
//
//     };
//
//     return (
//         <div>
//             {/* Header */}
//             <header className="header">
//                 <div>
//                     <h1>Exclusive For Women!!!</h1>
//                 </div>
//                 <div className="h-logo">
//                     <img className="img-style" src={logo} alt="Lotus Boutique Logo" />
//                     <h1>LOTUS BOUTIQUE</h1>
//                 </div>
//                 <div className="container3">
//                     <h1>Exclusive for Silks!!!</h1>
//                 </div>
//             </header>
//
//             {/* Navbar */}
//             <nav className="navbar">
//                 <ul className="menu">
//                     <li><Link to="/">Home</Link></li>
//                     <li><Link to="/support">Support</Link></li>
//                     <li><Link to="/about">About Us</Link></li>
//
//                     <div className="search-bar">
//                         <input
//                             type="text"
//                             value={searchQuery}
//                             onChange={(e) => setSearchQuery(e.target.value)}
//                             placeholder="Search for products..."
//                         />
//                         <button onClick={() => navigate(`/search?query=${searchQuery}`)}>Search</button>
//                     </div>
//
//                     {/* User */}
//                     <li>
//                         {user ? (
//                             <span onClick={() => setShowUserDetails(!showUserDetails)}>👤 {user.name}</span>
//                         ) : (
//                             <Link to="/login">Login</Link>
//                         )}
//                     </li>
//
//                     {/* Cart */}
//                     <li>
//                         <Link to="/cart">🛒 Cart ({cart.length})</Link>
//                     </li>
//
//                     {/* Logout */}
//                     {user && (
//                         <li>
//                             <Link
//                                 to="/login"
//                                 onClick={() => {
//                                     localStorage.removeItem("token");
//                                     navigate("/login");
//                                     window.location.reload();
//                                 }}
//                             >
//                                 🚪 Sign Out
//                             </Link>
//                         </li>
//                     )}
//                 </ul>
//             </nav>
//
//             {/* Dynamic Content */}
//             <Outlet />
//         </div>
//     );
// };
//
// export default Layout;
//

// import React, { useState, useEffect } from "react";
// import { Outlet, useNavigate, Link } from "react-router-dom";
// import "../styles/Home.css";
// import logo from "../images/logo.jpg";
//
// const Layout = () => {
//     const [user, setUser] = useState(null);
//     const [showUserDetails, setShowUserDetails] = useState(false);
//     const [cart, setCart] = useState([]);
//     const [searchQuery, setSearchQuery] = useState("");
//     const navigate = useNavigate();
//
//     useEffect(() => {
//         const token = localStorage.getItem("token");
//         if (token) {
//             fetchUserDetails();
//             fetchCartDetails();
//         }
//     }, []);
//
//     const fetchUserDetails = async () => {
//         const token = localStorage.getItem("token");
//         if (!token) return;
//
//         try {
//             const response = await fetch("http://localhost:8081/api/users/user", {
//                 method: "GET",
//                 headers: {
//                     Authorization: `Bearer ${token}`,
//                     "Content-Type": "application/json",
//                 },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch user details");
//
//             const data = await response.json();
//             console.log("🔍 API Response:", data); // Check API response
//
//             setUser(data); // Set user state
//             console.log("✅ Updated User State:", data); // Check if user.id is correct
//         } catch (error) {
//             console.error(error.message);
//         }
//     };
//
//     const fetchCartDetails = async () => {
//         try {
//             const token = localStorage.getItem("token");
//             if (!token || !user?.id) return; // Ensure user.id exists
//
//             const response = await fetch(`http://localhost:8081/api/cart/${user.id}`, {
//                 headers: { Authorization: `Bearer ${token}` },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch cart");
//
//             const cartData = await response.json();
//             setCart(cartData.cartItems || []);
//         } catch (error) {
//             console.error("❌ Error fetching cart:", error);
//         }
//     };
//
//     return (
//         <div>
//             {/* Header */}
//             <header className="header">
//                 <div>
//                     <h1>Exclusive For Women!!!</h1>
//                 </div>
//                 <div className="h-logo">
//                     <img className="img-style" src={logo} alt="Lotus Boutique Logo" />
//                     <h1>LOTUS BOUTIQUE</h1>
//                 </div>
//                 <div className="container3">
//                     <h1>Exclusive for Silks!!!</h1>
//                 </div>
//             </header>
//
//             {/* Navbar */}
//             <nav className="navbar">
//                 <ul className="menu">
//                     <li><Link to="/">Home</Link></li>
//                     <li><Link to="/support">Support</Link></li>
//                     <li><Link to="/about">About Us</Link></li>
//
//                     <div className="search-bar">
//                         <input
//                             type="text"
//                             value={searchQuery}
//                             onChange={(e) => setSearchQuery(e.target.value)}
//                             placeholder="Search for products..."
//                         />
//                         <button onClick={() => navigate(`/search?query=${searchQuery}`)}>Search</button>
//                     </div>
//
//                     {/* User */}
//                     <li>
//                         {user ? (
//                             <span onClick={() => setShowUserDetails(!showUserDetails)}>👤 {user.name}</span>
//                         ) : (
//                             <Link to="/login">Login</Link>
//                         )}
//                     </li>
//
//                     {/* Cart */}
//                     <li>
//                         <Link to="/cart">🛒 Cart ({cart.length})</Link>
//                     </li>
//
//                     {/* Logout */}
//                     {user && (
//                         <li>
//                             <Link
//                                 to="/login"
//                                 onClick={() => {
//                                     localStorage.removeItem("token");
//                                     navigate("/login");
//                                     window.location.reload();
//                                 }}
//                             >
//                                 🚪 Sign Out
//                             </Link>
//                         </li>
//                     )}
//                 </ul>
//             </nav>
//
//             {/* Dynamic Content */}
//             <Outlet />
//         </div>
//     );
// };
//
// export default Layout;
//


// import React from 'react';
// import '../styles/Home.css';
// import banner from '../images/infashion-1690967784.png';
// import women from '../images/Indian-Traditional-Clothing-min.jpg';
// import men from '../images/GiiwWVAe_0c44b8c6cd354c10af411a0692f8c599.jpg';
// import girl from '../images/cfe064dada1df3c62194fba860f86b2d.jpg';
// import boy from '../images/istockphoto-524161848-612x612.jpg';
// import { useNavigate } from "react-router-dom";
//
// const Home = () => {
//     const navigate = useNavigate();
//
//     const handleNavigation = (category) => {
//         navigate(`/products/${category}`);
//     };
//
//     return (
//         <div>
//             {/* Banner Section */}
//             <div className="banner">
//                 <div>
//                     <h1>Fashion is What You Buy,<br />
//                         Style is What<br /> You Do with It.</h1>
//                 </div>
//                 <div>
//                     <img className="banner-img" src={banner} alt="Family in traditional attire" />
//                 </div>
//             </div>
//
//             {/* Shop by Category Section */}
//             <h1 className= "heading">Shop by Category</h1>
//             <div className="category-section">
//                 <div className="category" onClick={() => handleNavigation('Women')}>
//                     <img src={women} alt="Women Collections" />
//                     <h3>Women Collections</h3>
//                 </div>
//                 <div className="category" onClick={() => handleNavigation('Men')}>
//                     <img src={men} alt="Men Collections" />
//                     <h3>Men Collections</h3>
//                 </div>
//                 <div className="category" onClick={() => handleNavigation('Girls')}>
//                     <img src={girl} alt="Girls Collections" />
//                     <h3>Girls Collections</h3>
//                 </div>
//                 <div className="category" onClick={() => handleNavigation('Boys')}>
//                     <img src={boy} alt="Boys Collections" />
//                     <h3>Boys Collections</h3>
//                 </div>
//             </div>
//         </div>
//     );
// };
//
// export default Home;


// import React, { useState, useEffect } from "react";
// import { Outlet, useNavigate, Link } from "react-router-dom";
// import "../styles/Home.css";
// import logo from "../images/logo.jpg";
//
// const Layout = () => {
//     const [user, setUser] = useState(null);
//     const [showDropdown, setShowDropdown] = useState(false);
//     const [searchQuery, setSearchQuery] = useState("");
//     const navigate = useNavigate();
//
//     useEffect(() => {
//         // Fetch user details if logged in
//         const fetchUserDetails = async () => {
//             const token = localStorage.getItem("token");
//             if (token) {
//                 try {
//                     const response = await fetch("http://localhost:8081/api/users/user", {
//                         headers: {
//                             Authorization: `Bearer ${token}`,
//                         },
//                     });
//                     if (response.ok) {
//                         const data = await response.json();
//                         setUser(data); // Set user details
//                     } else {
//                         console.error("Failed to fetch user details");
//                     }
//                 } catch (error) {
//                     console.error("Error fetching user details:", error);
//                 }
//             }
//         };
//
//         fetchUserDetails();
//     }, []);
//
//     const handleLogout = () => {
//         localStorage.removeItem("token"); // Clear token
//         alert("Logged out successfully");
//         navigate("/login"); // Redirect to login page
//         window.location.reload(); // Refresh to reflect logged-out state
//     };
//
//     const handleProtectedNavigation = (link) => {
//         const token = localStorage.getItem("token");
//         if (!token) {
//             alert("Please log in to access this page.");
//             navigate("/login");
//         } else {
//             navigate(link); // Navigate if user is logged in
//         }
//     };
//
//     const handleSearch = () => {
//         if (searchQuery.trim()) {
//             navigate(`/search?query=${searchQuery}`);
//             setSearchQuery("");
//         } else {
//             alert("Please enter a search term.");
//         }
//
//     };
//
//
//     return (
//         <div>
//             {/* Header */}
//             <header className="header">
//                 <div>
//                     <h1>Exclusive For Women!!!</h1>
//                 </div>
//                 <div className="h-logo">
//                     <div>
//                         <img className="img-style" src={logo} alt="Lotus Boutique Logo" />
//                     </div>
//                     <div>
//                         <h1>LOTUS<br /> BOUTIQUE</h1>
//                     </div>
//                 </div>
//                 <div className="container3">
//                     <h1>Exclusive for Silks!!!</h1>
//                 </div>
//             </header>
//
//             {/* Navbar */}
//             <nav className="navbar">
//                 <div className="navbar-container">
//                     <ul className="menu">
//                         <div className="icons">
//                             <li><Link to="/">Home</Link></li>
//                             <li onClick={() => handleProtectedNavigation("/products/women")}>Women</li>
//                             <li onClick={() => handleProtectedNavigation("/products/men")}>Men</li>
//                             <li onClick={() => handleProtectedNavigation("/products/boys")}>Boys</li>
//                             <li onClick={() => handleProtectedNavigation("/products/girls")}>Girls</li>
//                             <li><Link to="/support">Support</Link></li>
//                             <li><Link to="/about">About Us</Link></li>
//                             <li><Link to="/lookbooks">Look Books</Link></li>
//                             <div className="search-bar">
//                                 <input
//                                     type="text"
//                                     value={searchQuery}
//                                     onChange={(e) => setSearchQuery(e.target.value)}
//                                     placeholder="Search for products..."
//                                     className="search-input"
//                                 />
//                                 <button onClick={handleSearch} className="search-button">Search</button>
//                             </div>
//
//                             {/* User Icon */}
//                             <div className="user-section">
//                                 <div
//                                     className="icon user"
//                                     onClick={() => setShowDropdown(!showDropdown)}
//                                 >
//                                     👤
//                                 </div>
//                                 {showDropdown && user && (
//                                     <div className="dropdown-menu">
//                                         <p><b>Name:</b> {user.name}</p>
//                                         <p><b>Email:</b> {user.email}</p>
//                                         <button onClick={() => navigate("/profile")}>
//                                             Manage Account
//                                         </button>
//                                     </div>
//                                 )}
//                             </div>
//
//                             {/* Cart Link */}
//                             <li>
//                                 <Link to="/cart" className="icon cart">
//                                     🛒 Cart
//                                 </Link>
//                             </li>
//
//                             {/* Sign-Out Link */}
//                             <li>
//                                 <Link to="/login" onClick={handleLogout} className="icon logout">
//                                     🚪 Sign Out
//                                 </Link>
//                             </li></div>
//                     </ul>
//
//
//                 </div>
//             </nav>
//
//             {/* Dynamic Content */}
//             <Outlet/>
//         </div>
//     );
// };
//
// export default Layout;

// import React, { useState, useEffect } from "react";
// import { Outlet, useNavigate, Link } from "react-router-dom";
// import "../styles/Home.css";
// import logo from "../images/logo.jpg";
//
// import hand
//
// const Layout = () => {
//     const [user, setUser] = useState(null);
//     const [showUserDetails, setShowUserDetails] = useState(false);
//     const [cart, setCart] = useState([]);
//     const [searchQuery, setSearchQuery] = useState("");
//     const navigate = useNavigate();
//
//     useEffect(() => {
//         const token = localStorage.getItem("token");
//         if (token) {
//             fetchUserDetails();
//             fetchCartDetails();
//         }
//     }, []);
//
//     const fetchUserDetails = async () => {
//         const token = localStorage.getItem("token");
//         if (!token) return;
//
//         try {
//             const response = await fetch("http://localhost:8081/api/users/user", {
//                 method: "GET",
//                 headers: {
//                     Authorization: `Bearer ${token}`,
//                     "Content-Type": "application/json",
//                 },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch user details");
//
//             const data = await response.json();
//             console.log("🔍 API Response:", data); // Check API response
//
//             setUser(data); // Set user state
//             console.log("✅ Updated User State:", data); // Check if user.id is correct
//         } catch (error) {
//             console.error(error.message);
//         }
//     };
//
//
//     const fetchCartDetails = async () => {
//         try {
//             const token = localStorage.getItem("token");
//             if (!token || !user?.id) return; // Ensure user.id exists
//
//             const response = await fetch(`http://localhost:8081/api/cart/${user.id}`, {
//                 headers: { Authorization: `Bearer ${token}` },
//             });
//
//             if (!response.ok) throw new Error("Failed to fetch cart");
//
//             const cartData = await response.json();
//             setCart(cartData.cartItems || []);
//         } catch (error) {
//             console.error("❌ Error fetching cart:", error);
//         }
//
//         const handleLogout = () => {
//     localStorage.removeItem("token"); // Clear token
//          alert("Logged out successfully");
//          navigate("/login"); // Redirect to login page
//          window.location.reload(); // Refresh to reflect logged-out state
//      };
//
//      const handleProtectedNavigation = (link) => {
//          const token = localStorage.getItem("token");
//          if (!token) {
//              alert("Please log in to access this page.");
//              navigate("/login");
//          } else {
//              navigate(link); // Navigate if user is logged in
//          }
//      };
//
//      const handleSearch = () => {
//          if (searchQuery.trim()) {
//              navigate(`/search?query=${searchQuery}`);
//              setSearchQuery("");
//          } else {
//              alert("Please enter a search term.");
//          }
//
//      };
//
//     };
//
//     return (
//         <div>
//             {/* Header */}
//             <header className="header">
//                 <div>
//                     <h1>Exclusive For Women!!!</h1>
//                 </div>
//                 <div className="h-logo">
//                     <img className="img-style" src={logo} alt="Lotus Boutique Logo" />
//                     <h1>LOTUS BOUTIQUE</h1>
//                 </div>
//                 <div className="container3">
//                     <h1>Exclusive for Silks!!!</h1>
//                 </div>
//             </header>
//
//             {/* Navbar */}
//             <nav className="navbar">
//                 <ul className="menu">
//                     <li><Link to="/">Home</Link></li>
//                     <li><Link to="/support">Support</Link></li>
//                     <li><Link to="/about">About Us</Link></li>
//                     <li><Link to="/">Home</Link></li>
//                      <li onClick={() => handleProtectedNavigation("/products/women")}>Women</li>
//                     <li onClick={() => handleProtectedNavigation("/products/men")}>Men</li>
//                     <li onClick={() => handleProtectedNavigation("/products/boys")}>Boys</li>
//                     <li onClick={() => handleProtectedNavigation("/products/girls")}>Girls</li>
//                     <li><Link to="/support">Support</Link></li>
//                     <li><Link to="/about">About Us</Link></li>
//                     <li><Link to="/lookbooks">Look Books</Link></li>
//
//                     <div className="search-bar">
//                         <input
//                             type="text"
//                             value={searchQuery}
//                             onChange={(e) => setSearchQuery(e.target.value)}
//                             placeholder="Search for products..."
//                         />
//                         <button onClick={() => navigate(`/search?query=${searchQuery}`)}>Search</button>
//                     </div>
//
//                     {/* User */}
//                     <li>
//                         {user ? (
//                             <span onClick={() => setShowUserDetails(!showUserDetails)}>👤 {user.name}</span>
//                         ) : (
//                             <Link to="/login">Login</Link>
//                         )}
//                     </li>
//
//                     {/* Cart */}
//                     <li>
//                         <Link to="/cart">🛒 Cart ({cart.length})</Link>
//                     </li>
//
//                     {/* Logout */}
//                     {user && (
//                         <li>
//                             <Link
//                                 to="/login"
//                                 onClick={() => {
//                                     localStorage.removeItem("token");
//                                     navigate("/login");
//                                     window.location.reload();
//                                 }}
//                             >
//                                 🚪 Sign Out
//                             </Link>
//                         </li>
//                     )}
//                 </ul>
//             </nav>
//
//             {/* Dynamic Content */}
//             <Outlet/>
//         </div>
//     );
// };
//
// export default Layout;



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
                        <img className="img-style" src={logo} alt="Lotus Boutique Logo" />
                    </div>
                    <div>
                        <h1>LOTUS<br /> BOUTIQUE</h1>
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
                            </li></div>
                    </ul>


                </div>
            </nav>

            {/* Dynamic Content */}
            <Outlet/>
        </div>
    );
};

export default Layout;
