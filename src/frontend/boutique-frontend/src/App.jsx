import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { CartProvider } from "./pages/CartContext"; // ✅ Correct import
import Layout from "./Components/Layout";
import Home from "./pages/Home";
import Login from "./pages/Login";
import CreateAccount from "./pages/CreateAccount";
import ConfirmEmail from "./pages/ConfirmEmail";
import ForgetPassword from "./pages/ForgetPassword";
import ProductList from "./pages/ProductList";
import NotFound from "./pages/NotFound";
import Cart from "./pages/Cart";
import Dashboard from "./pages/Profile";
import AboutUs from "./pages/About";
import OrderPage from "./pages/OrderDetails";
import OrderList from "./pages/OrderList"
import ProductManagement from "./pages/ProductManagement";
import Checkout from "./pages/Checkout";
import Orders from "./pages/Orders";
import UserList from "./pages/Admin";
import Admin from "./pages/Admin";
const App = () => {
    return (
        <CartProvider> {/* ✅ Wrap everything in CartProvider */}
            <Router>
                <Routes>
                    <Route path="/" element={<Layout />}>
                        {/*<Route path="/" element={<BottomLayout />} />*/}
                        <Route index element={<Home />} />
                        <Route path="login" element={<Login />} />
                        <Route path="signup" element={<CreateAccount />} />
                        <Route path="confirm_email" element={<ConfirmEmail />} />
                        <Route path="forgot_password" element={<ForgetPassword />} />
                        <Route path="products/:category" element={<ProductList />} />
                        <Route path="/search" element={<ProductList />} />
                        <Route path="cart" element={<Cart />} />
                        <Route path="*" element={<NotFound />} />
                        <Route path="/dashboard" element={<Dashboard />} />
                        <Route path="/about" element={<AboutUs />} />
                        <Route path="/cart" element={<Cart />} />
                        <Route path="/order-details" element={<OrderPage />} />
                        <Route path="/order-list" element={<OrderList />} />
                        <Route path="/checkout" element={<Checkout />} />
                        <Route path="/orders" element={<Orders />} />
                        <Route path="/admin" element={<UserList />} />

                        <Route path="/product" element={<ProductManagement />} >
                            <Route index element={<ProductManagement />} />
                        </Route>

                    </Route>
                </Routes>
            </Router>
        </CartProvider>
    );
};

export default App;
