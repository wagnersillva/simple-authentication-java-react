import Home from "../pages/Home";
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import Dashboard from "../pages/Dashboard";

export const mappingRoutes = [
    { path: "/", element: <Home /> },
    { path: "/dashboard", element: <Dashboard />, isPrivate: true },
    { path: "/auth/login", element: <Login /> },
    { path: "/auth/register", element: <Register />, }
];

