import React from "react";
import {Navigate} from "react-router-dom";

export default function PrivateRoute({ children }){
    const user = localStorage.getItem("user");
    return !user ? <Navigate to="/auth/login" replace={true} /> :  children;
}