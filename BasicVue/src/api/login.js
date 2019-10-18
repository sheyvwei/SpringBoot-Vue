import request from '@/utils/request.js'

// 登录
export function login (loginForm) {
  return request({
    url: '/login/auth',
    method: 'post',
    // data: loginForm
    params: {
      userName: loginForm.username,
      password: loginForm.password
    }
  })
}

// 获取用户信息
export function getInfo () {
  return request({
    url: '/login/getInfo',
    method: 'get'
  })
}

export function logout () {
  return request({
    url: '/login/logout',
    method: 'get'
  })
}
