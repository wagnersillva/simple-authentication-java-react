import React from "react";
import {Form, Input} from "antd";

export default function InputPassword({ label, name, hasFeedback, disabled, dependencies, validates, isRequired, size, placeholder }){

    const rules = [
        ...( isRequired ? [{ required: true, message: `Campo ${name} obrigatório` }] : []),
        ...( validates ? validates : [])
    ]

    return (
        <Form.Item
            dependencies={dependencies}
            name={name}
            rules={rules}
            hasFeedback={hasFeedback}
        >
            <Input.Password disabled={disabled} size={size} placeholder={placeholder} />
        </Form.Item>
    )

}