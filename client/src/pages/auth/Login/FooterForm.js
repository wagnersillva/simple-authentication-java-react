import React from "react";
import {Button, Col} from "antd";

export default function FooterForm({ accountBlocked, accountValidated, toBack, onFinish, validAccount }){

    const toRegister = () => {
        window.location = "/auth/register";
    }

    const toHome = () => {
        window.location = "/";
    }

    const renderFooterFormBlocked = () => {
        return (
            <Col span={24}>
                <Button type={"text"} onClick={toHome}>
                    Sair
                </Button>
            </Col>
        )
    }

    const renderFooterFormValidated = () => {
        return (
            <>
                <Col span={12}>
                    <Button type={"text"} onClick={toBack}>
                        Voltar
                    </Button>
                </Col>
                <Col span={12} key="btn-on-finish">
                    <Button
                        htmlType={"submit"}
                        type={"primary"}
                    >
                        Entrar
                    </Button>
                </Col>
            </>
        )
    }

    const renderFooterForm = () => {
        return (
            <>
                <Col span={12}>
                    <Button type={"text"} onClick={toRegister}>
                        Criar conta
                    </Button>
                </Col>
                <Col span={12}>
                    <Button
                        type={"primary"}
                        onClick={validAccount}
                        key="btn-on-next"
                    >
                        Próximo
                    </Button>
                </Col>
            </>
        )
    }

    if(accountBlocked) return renderFooterFormBlocked();
    if(accountValidated) return renderFooterFormValidated();

    return renderFooterForm();
}