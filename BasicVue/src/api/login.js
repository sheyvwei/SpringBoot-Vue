import request from '@/utils/request.js'

// 登录
export function login (loginForm) {
  return request({
    url: '/login/auth',
    method: 'post',
    data: loginForm
    // params: {
    //   username: loginForm.username,
    //   password: loginForm.password
    // }
  })
}
