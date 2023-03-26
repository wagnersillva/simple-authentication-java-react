import api from "./api";

export const authService = {
    register: (values) => api.post("auth/register", values),
    verifyUsername: (values) => api.post("auth/verify-username", values),
    login: (values) => api.post("auth/login", values),
}