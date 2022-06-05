import axios from 'axios'
import Vue from 'vue'

const http = axios.create({
  // 指定请求的根路径
  baseURL: 'http://127.0.0.1:5000'
})

export default http