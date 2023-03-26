import React from "react";
import {Button, Col, Row} from "antd";


export default function Home(){
    return (
        <Row  justify={"center"} className={"content-home"}>
            <Col span={14}>
                Seja Bem-vindo!
            </Col>
            <Col span={14} style={{ marginTop: 25 }}>
                <Button href={"/auth/login"}>
                    Fazer login
                </Button>
            </Col>
        </Row>
    )
}