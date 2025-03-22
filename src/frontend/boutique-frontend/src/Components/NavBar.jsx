//
// import React, { useState, useEffect } from "react";
// import { Outlet, useNavigate, Link } from "react-router-dom";
// import "../styles/Home.css";
// import logo from "../images/logo.jpg";
//
// const BottomLayout = () => {
//     const [user, setUser] = useState(null);
//     const [showDropdown, setShowDropdown] = useState(false);
//     const [searchQuery, setSearchQuery] = useState("");
//     const navigate = useNavigate();
//
//
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
//
//     return (
//         <div>
//             {/* Header */}
//             <div className="down-nav">
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
//             </div>
//
//             {/* Dynamic Content */}
//             <Outlet/>
//         </div>
//     );
// };
//
// export default BottomLayout;
