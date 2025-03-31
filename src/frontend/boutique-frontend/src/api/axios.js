import axios from 'axios';

const API = axios.create({
    baseURL: 'https://alagar003.github.io', // Adjust according to your backend
});

export default API;
