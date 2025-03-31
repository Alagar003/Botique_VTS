import axios from 'axios';

const API = axios.create({
    baseURL: 'https://alagar003.github.io/Botique_VTS', // Adjust according to your backend
});

export default API;
