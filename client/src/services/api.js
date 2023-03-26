import axios from "axios";
import {notification} from "antd";
import {SERVER_URL} from "../config";

const api = axios.create({
    baseURL: SERVER_URL,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json;charset=utf-8",
    },
});

axios.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    return Promise.reject(error);
});

api.interceptors.response.use(
    response => mapResponseToUsefulData(response),
    error => requestFailed(error)
);

const mapResponseToUsefulData = (response) => response.data;

const requestFailed = (error) => {
    let message = "";
    let userBlocked = false;

    if(error?.response?.message) {
        message = error?.response?.message
    } else if(error?.response?.error){
        message = error?.response?.error
    } else if(error?.response?.data?.message){
        message = error?.response?.data?.message
    } else {
        message = "Ocorreu um erro no servidor. Tente novamente mais tarde ou contate um administrador.";
    }

    if(error?.response?.data?.userBlocked){
        userBlocked = true;
    }

    return Promise.reject({ message, userBlocked });
}

export default api;