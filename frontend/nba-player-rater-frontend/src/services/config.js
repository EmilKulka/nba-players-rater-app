import axios from 'axios';

const api = axios.create({
    baseURL: import.meta.env.VITE_REACT_APP_API_BASE_URL,
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    }
});
console.log(import.meta.env.VITE_REACT_APP_API_BASE_URL)

api.interceptors.request.use(
    (config) => {
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

api.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default api;