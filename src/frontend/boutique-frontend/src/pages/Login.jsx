// import React, { useState } from "react";
// import { useNavigate, useLocation } from "react-router-dom";
// import "../styles/Login.css";
// import loginImage from "../images/login.jpg";
// import {Link} from "@mui/material";
//
// const Login = () => {
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//     const navigate = useNavigate();
//     const location = useLocation();
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//
//         try {
//             const response = await fetch("https://alagar003.github.io/Botique_VTS/auth/login", {
//                 method: "POST",
//                 headers: {
//                     "Content-Type": "application/json",
//                 },
//                 body: JSON.stringify({ email, password }),
//             });
//
//             const data = await response.json();
//             console.log("🔍 Login Response:", data); // Debugging
//
//             if (response.ok) {
//                 if (data.token && data.user) {  // ✅ Ensure user object exists
//                     localStorage.setItem("token", data.token);
//                     localStorage.setItem("user", JSON.stringify(data.user)); // ✅ Store full user data
//
//                     console.log("✅ Token & User saved:", data.token, data.user);
//                     alert("Login successful!");
//                     navigate("/");  // Redirect to home
//                 } else {
//                     alert("Login successful but missing token or user data.");
//                 }
//             } else {
//                 alert(data.message || "Login failed. Please check your credentials.");
//                 setEmail('');
//                 setPassword('');
//             }
//         } catch (error) {
//             console.error("❌ Error during login:", error.message);
//             alert("An error occurred. Please try again.");
//         }
//     };
//
//
//
//     return (
//         <div className="login-body">
//             <div className="login-container">
//                 <img className="image" src={loginImage} alt="Login" />
//                 <div className="login-form">
//                     <h2>LOG IN</h2>
//                     <form onSubmit={handleSubmit}>
//                         <label>Email</label>
//                         <input
//                             type="email"
//                             placeholder="Enter your email"
//                             value={email}
//                             onChange={(e) => setEmail(e.target.value)}
//                             required
//                         />
//                         <label>Password</label>
//                         <input
//                             type="password"
//                             placeholder="Enter your password"
//                             value={password}
//                             onChange={(e) => setPassword(e.target.value)}
//                             required
//                         />
//                         <Link className="forgot-password" onClick={() => navigate('/forgot_password')}>Forgot Password?</Link>
//                         <a className="create-account" onClick={() => navigate('/signup')}>Create Account</a>
//
//                         <button className="sign-in-button" type="submit">
//                             SIGN IN
//                         </button>
//                         <a className="create-account" onClick={() => navigate('/signup')}>Create Account</a>
//                     </form>
//                 </div>
//             </div>
//         </div>
//     );
// };
//
// export default Login;





import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Login.css";
import loginImage from "../images/login.jpg";
import { Link } from "react-router-dom"; // ✅ Correct import

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("https://alagar003.github.io/Botique_VTS/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password }),
            });

            const data = await response.json();
            console.log("🔍 Login Response:", data);

            if (response.ok) {
                if (data.token && data.user) {
                    localStorage.setItem("token", data.token);
                    localStorage.setItem("user", JSON.stringify(data.user));
                    console.log("✅ Token & User saved:", data.token, data.user);
                    alert("Login successful!");
                    navigate("/");  // ✅ Redirect without reload
                } else {
                    alert("Login successful but missing token or user data.");
                }
            } else {
                console.error("❌ Login failed:", response.status, data.message);
                alert(data.message || "Login failed. Please check your credentials.");
                setEmail('');
                setPassword('');
            }
        } catch (error) {
            console.error("❌ Error during login:", error.message);
            alert("An error occurred. Please try again.");
        }
    };

    return (
        <div className="login-body">
            <div className="login-container">
                <img className="image" src={loginImage} alt="Login" />
                <div className="login-form">
                    <h2>LOG IN</h2>
                    <form onSubmit={handleSubmit}>
                        <label>Email</label>
                        <input
                            type="email"
                            placeholder="Enter your email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                        <label>Password</label>
                        <input
                            type="password"
                            placeholder="Enter your password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                        <Link className="forgot-password" to="/forgot_password">
                            Forgot Password?
                        </Link>
                        <button className="sign-in-button" type="submit">
                            SIGN IN
                        </button>
                        <Link className="create-account" to="/signup">
                            Create Account
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Login;
