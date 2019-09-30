// 封装axios
import axios from 'axios'
import {Message, MessageBox} from 'element-ui'
import store from '../store'
import {getToken} from '@/utils/auth'
// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['X-Token'] = getToken() // 让每个请求携带自定义token，
    }
    config.headers.post['Content-Type'] = 'application/x-www-form-urlencode'
    return config
  },
  error => { // 异常处理
    console.log(error + ' (request.js by config)') // for debug
    return Promise.error(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    /**
     * code为20000是抛错，可以结合自己业务修改
     */
    if (res.code !== 20000) {
      Message({
        message: res.data,
        type: 'error',
        duration: 5 * 1000 // 显示时间
      })

      // 其中50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        MessageBox.confirm(
          '你已被登出，可以取消继续留在该页面，或者重新登录',
          '确定登出',
          {
            confirmButtonText: '重新登录',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
      }
      // 在promise的reject中需要传入的是一个Error对象. return Promise.reject('error')
      return Promise.reject(new Error('error'))
    } else {
      return response.data
    }
  },
  error => {
    console.log('err (request.js by response)' + error) // for debug
    Message({
      message: error.data,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
