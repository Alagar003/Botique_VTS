import React, { useState } from 'react';
import '../styles/Login.css';
import accImage from '../images/saree-pictures-2d8qt1hau3xlfjdp-removebg-preview.png';
import { useNavigate } from 'react-router-dom';

const CreateAccount = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('CUSTOMER');  // Default role
    const [organizationPassword, setOrganizationPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleCreateAccount = async (e) => {
        e.preventDefault();

        const requestData = { name, email, password, role };
        if (role === "ADMIN") {
            requestData.organizationPassword = organizationPassword; // Add only for admin
        }

        try {
            const response = await fetch('https://alagar003.github.io/Botique_VTS/auth/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(requestData),
            });

            if (response.ok) {
                alert("Account created successfully!");
                navigate("/confirm_email");
            } else {
                const errorData = await response.json();
                setErrorMessage(errorData.message || 'Registration failed.');
            }
        } catch (error) {
            setErrorMessage('A network error occurred. Please try again later.');
        }
    };

    return (
        <div className="login-body">
            <div className="login-container">
                <img className="image" src={accImage} alt="Create Account" />
                <div className="login-form">
                    <h2>CREATE ACCOUNT</h2>
                    <form onSubmit={handleCreateAccount}>
                        <label>Name</label>
                        <input
                            type="text"
                            placeholder="Enter your name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
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

                        <label>Role</label>
                        <select value={role} onChange={(e) => setRole(e.target.value)} required>
                            <option value="CUSTOMER">Customer</option>
                            <option value="ADMIN">Admin</option>
                        </select>

                        {/* Show Organization Password Field Only for Admin */}
                        {role === "ADMIN" && (
                            <>
                                <label>Organization Password</label>
                                <input
                                    type="password"
                                    placeholder="Enter organization password"
                                    value={organizationPassword}
                                    onChange={(e) => setOrganizationPassword(e.target.value)}
                                    required
                                />
                            </>
                        )}

                        {errorMessage && <p className="error-message">{errorMessage}</p>}
                        <button className="sign-in-button" type="submit">CREATE</button>
                        <a
                            href="#"
                            className="create-account"
                            onClick={(e) => {
                                e.preventDefault();
                                navigate('/login');
                            }}
                        >
                            Cancel
                        </a>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default CreateAccount;
