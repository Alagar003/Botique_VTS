import React, { useEffect, useState } from "react";
import "../styles/profile.css"; // Import the CSS file for styling

const Dashboard = () => {
    const [user, setUser ] = useState(null); // State to hold user data
    const [loading, setLoading] = useState(true); // State to manage loading state
    const [error, setError] = useState(null); // State to manage error messages

    useEffect(() => {
        const fetchUser  = async () => {
            const token = localStorage.getItem("token"); // Retrieve stored token
            if (!token) {
                console.log("No token found!");
                setLoading(false); // Set loading to false if no token
                return;
            }

            try {
                const response = await fetch("https://alagar003.github.io/Botique_VTS/users/user", {
                    method: "GET",
                    headers: {
                        Authorization: `Bearer ${token}`, // Send token in headers
                        "Content-Type": "application/json",
                    },
                });

                if (!response.ok) {
                    throw new Error("Failed to fetch user details");
                }

                const data = await response.json();
                console.log("User  details fetched:", data); // Log the user details
                setUser (data); // Update state with user data
            } catch (error) {
                console.error("Error fetching user details:", error);
                setError(error.message); // Set error message
            } finally {
                setLoading(false); // Set loading to false after fetch attempt
            }
        };

        fetchUser ();
    }, []);

    // Loading state
    if (loading) {
        return <div className="spinner">Loading user data...</div>; // Loading spinner
    }

    // Error state
    if (error) {
        return <p className="error-message">Error: {error}</p>; // Display error message
    }

    return (
        <div className="dashboard-container">
            <h2>Welcome, {user ? user.name : "Guest"}!</h2>
            {user && (
                <div className="user-info">
                    <p>Email: {user.email}</p>
                    <p>Role: {user.role}</p>
                </div>
            )}
        </div>
    );
};

export default Dashboard;