import React, {useEffect} from "react";
import WrapperLogin from "../WrapperLogin";
import {Button, Col, Form, message, Row} from "antd";
import Input from "../../../components/Form/Input";
import InputPassword from "../../../components/Form/InputPassword";
import {authService} from "../../../services/authService";

export default function Register(){

    const [form] = Form.useForm();
    const [messageApi, contextHolder] = message.useMessage();

    useEffect(() => {
        resetFields();
    }, [])

    const resetFields = () => form.resetFields();

    const toLogin = () => {
        window.location = "/auth/login";
    }

    const onFinish = (values) => {
        authService.register(values)
            .then( response => {
                const { username, message } = response;
                messageApi.success(`Parabéns ${username}. ${message} `);
                resetFields();
            })
            .catch( error => {
                const { message } = error;
                messageApi.error(message);
            })
    }

    return (
        <WrapperLogin title="Fazer Cadastro">
            {contextHolder}
            <Form
                name="basic"
                layout="vertical"
                style={{ maxWidth: 600 }}
                form={form}
                onFinish={onFinish}
            >
                <Row justify={"center"} style={{ marginBottom: 60, marginTop: 90 }}>
                    <Col span={20}>
                        <Input size={"large"} placeholder={"Username"} name={"username"} isRequired />
                    </Col>
                    <Col span={20}>
                        <Input size={"large"} placeholder={"Name"} name={"name"} isRequired />
                    </Col>
                    <Col span={20}>
                        <InputPassword placeholder={"Password"} size={"large"} name={"password"} isRequired />
                    </Col>
                    <Col span={20}>
                        <InputPassword
                            placeholder={"Confirm Password"}
                            size={"large"}
                            name={"confirmPassword"}
                            dependencies={['password']}
                            validates={[
                                ({ getFieldValue }) => ({
                                    validator(_, value) {
                                        if (!value || getFieldValue('password') === value) {
                                            return Promise.resolve();
                                        }
                                        return Promise.reject(new Error('As senhas não conferem'));
                                    },
                                })
                            ]}
                            hasFeedback
                            isRequired
                        />
                    </Col>
                </Row>
                <Row>
                    <Col span={12}>
                        <Button type={"text"} onClick={toLogin}>
                            Já tem conta? <strong> Faça login </strong>
                        </Button>
                    </Col>
                    <Col span={12} key="btn-on-finish">
                        <Button
                            htmlType={"submit"}
                            type={"primary"}
                        >
                            Registrar-se
                        </Button>
                    </Col>
                </Row>
            </Form>
        </WrapperLogin>
    )
}