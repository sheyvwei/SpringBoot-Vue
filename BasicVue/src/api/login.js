import request from '@utils/request.js'

// 登录
export function login (userName, password) {
  return request({
    url: '/login/auth',
    method: 'post',
    params: {
      userName,
      password
    }
  })
}
