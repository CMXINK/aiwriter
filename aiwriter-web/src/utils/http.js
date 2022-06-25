import axios from "./interceptor.js"

export default axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000,
})
