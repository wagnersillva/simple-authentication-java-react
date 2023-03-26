import React, {useEffect, useState} from "react";
import {Col, Form, message, Row} from "antd";
import WrapperLogin from "../WrapperLogin";
import Content from "./Content";
import FooterForm from "./FooterForm";
import {authService} from "../../../services/authService";

export default function Login(){

    const [form] = Form.useForm();
    const [messageApi, contextHolder] = message.useMessage();
    const [accountValidated, setAccountValidated] = useState(false);
    const [accountBlock, setAccountBlock] = useState(false);
    const [usernameField, setUsernameField] = useState(null);

    useEffect(() => {
        if(!accountValidated){
            resetFields();
        }
    }, [accountValidated]);

    const showAlert = ( type, content) => messageApi.open({ type, content });

    const resetFields = () => form.resetFields();

    const toBack = () => {
        setAccountValidated(false);
        setAccountBlock(false);
    }

    const onFinish = (values) => {
        authService.login({...values, username: usernameField})
            .then( response => {
                const { message, username } = response;
                messageApi.success(message)
                    .then(() => {
                        localStorage.setItem("user", username);
                        window.location = "/dashboard";
                    });
            })
            .catch( error => {
                const { message, userBlocked } = error
                messageApi.error(message);
                setAccountBlock(userBlocked);
            })
    }

    const confirmAccount = () => {
        const username = form.getFieldValue("username");
        authService.verifyUsername({username})
            .then(response => {
                const { isValid, message } = response;
                let type = "error";
                let content = message;

                if(isValid){
                    setAccountValidated(true);
                    type = "success";
                    content = `Olá, ${username}, insira sua senha`
                    setUsernameField(username);
                }

                showAlert( type, content );
            })
            .catch( error => {
                const { message, userBlocked } = error
                messageApi.error(message);
                setAccountBlock(userBlocked);
            })

    }

    return (
        <WrapperLogin title="Fazer Login" >
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
                        <Content accountValidated={accountValidated} accountBlocked={accountBlock} />
                    </Col>
                </Row>
                <Row>
                    <FooterForm
                        accountBlocked={accountBlock}
                        accountValidated={accountValidated}
                        validAccount={confirmAccount}
                        onFinish={onFinish}
                        toBack={toBack}
                    />
                </Row>
            </Form>
        </WrapperLogin>
    )
}