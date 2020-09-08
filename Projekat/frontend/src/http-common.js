import axios from 'axios'

export const AXIOS = axios.create({
    baseURL: `/`,
    headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
})
