import React from "react";
import Input from "../../../components/Form/Input";
import InputPassword from "../../../components/Form/InputPassword";

export default function Content({ accountBlocked, accountValidated }){

    if(accountValidated){
        return <InputPassword disabled={accountBlocked} size={"large"} name="password" isRequired />
    }

    return <Input size={"large"} name="username" placeholder="Insira o Username" isRequired />
}