import React from "react";
import "./style.css";

export default function WrapperLogin({ children, title }){
    return (
        <div className="container">
            <div className="content-form-auth">
                <div>
                    {title}
                </div>
                {children}
            </div>
        </div>
    )
}