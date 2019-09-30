import Cookies from 'js-cookie'
// cookies保存一个特殊的token
const TokenKey = 'Admin-Token'

export function getToken () {
  return Cookies.get(TokenKey)
}

export function setToken (token) {
  return Cookies.setToken(TokenKey, token)
}

export function removeToken () {
  return Cookies.remove(TokenKey)
}
