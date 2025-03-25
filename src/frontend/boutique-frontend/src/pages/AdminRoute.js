import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { getUserRole } from "../authService";

const AdminRoute = () => {
    const role = getUserRole();

    return role === "ADMIN" ? <Outlet /> : <Navigate to="/" />;
};

export default AdminRoutea;
