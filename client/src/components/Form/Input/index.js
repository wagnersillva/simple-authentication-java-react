import React from "react";
import {Form, Input} from "antd";

export default function InputAnt({ name, hasFeedback, validates, isRequired, size, placeholder }){

    const rules = [
        ...( isRequired ? [{ required: true, message: `Campo ${name} obrigatório` }] : []),
        ...( validates ? validates : [])
    ]

    return (
        <Form.Item
            name={name}
            rules={rules}
            hasFeedback={hasFeedback}
        >
            <Input size={size} placeholder={placeholder} />
        </Form.Item>
    )

}