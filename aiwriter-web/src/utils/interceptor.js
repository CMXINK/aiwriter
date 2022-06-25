import axios from 'axios'
//  axios 拦截器配置
axios.interceptors.request.use((config) => {
  config.headers['X-Requested-With'] = 'XMLHttpRequest'
  return config
})
axios.interceptors.response.use((response) => {
  /*const data = response.data
  switch (data.code) {
    case '0':
      if (isIOS()) {
        setTimeout(() => {
          document.body.scrollTop += 1
        }, 0)
      }
      return data
    case 'SHIRO_E5001':
      if (isWeChat() && IS_PRODUCTION) {
        axios.get(apis.common.wechat.authorizeUrl).then(({ result }) => {
          location.replace(global.decodeURIComponent(result))
        })
      } else {
        const search = encodeSearchParams({
          next: location.href,
        })
        location.replace(`/user/login?${search}`)
      }
      data.description = ''
      break
    default:
  }
*/
  // 状态码配置项
  const err = new Error(data.description)
  err.data = data
  err.response = response
  throw err
}, (err) => {
  if (err && err.response) {
    switch (err.response.status) {
      case 400:
        err.message = '请求错误'
        break
      case 401:
        err.message = '未授权，请登录'
        break
      case 403:
        err.message = '拒绝访问'
        break
      case 404:
        err.message = `请求地址出错: ${err.response.config.url}`
        break
      case 408:
        err.message = '请求超时'
        break
      case 500:
        err.message = '服务器内部错误'
        break
      case 501:
        err.message = '服务未实现'
        break
      case 502:
        err.message = '网关错误'
        break
      case 503:
        err.message = '服务不可用'
        break
      case 504:
        err.message = '网关超时'
        break
      case 505:
        err.message = 'HTTP版本不受支持'
        break
      default:
    }
  }
  return Promise.reject(err)
})
export default axios