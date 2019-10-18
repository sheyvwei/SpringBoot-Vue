//  登录时用户信息
// import store from '@/store'
import {setToken, removeToken} from '@/utils/auth'
import {login, getInfo, logout} from '@/api/login'
import store from '@/store'
import router from '@/router'

const user = {
  state: {
    userName: '', // 名字
    role: '', // 角色
    menus: [], // 侧边栏的内容
    permissions: [] // 每个功能权限
  },
  mutations: {
    SET_USER: (state, userInfo) => {
      state.userName = userInfo.userName
      state.role = userInfo.roleName
      state.menus = userInfo.menuList
      state.permissions = userInfo.permissionList
    },
    RESET_USER: (state) => {
      state.userName = ''
      state.role = ''
      state.menus = []
      state.permissions = []
    }
  },
  actions: {
    login ({commit, state}, loginForm) {
      return new Promise((resolve, reject) => {
        login(loginForm).then(response => {
          var token = response.data.token
          setToken(token) // 保存token到cookies
          resolve()
        })
      })
    },
    // 获取用户信息
    getInfo ({commit, state}) {
      return new Promise((resolve, reject) => {
        getInfo().then(response => {
          let userInfo = response.data
          commit('SET_USER', userInfo) // 保存用户到vuex
          // 路由 = 公共路由 + 权限过滤的路由
          // console.log(1)
          store.dispatch('GenerateRoutes', userInfo).then(() => {
            router.addRoutes(store.getters.addRouters)
            // console.log(store.getters.addRouters)
          })
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    logout ({commit}) {
      return new Promise((resolve, reject) => {
        logout().then(() => {
          commit('RESET_USER')
          removeToken()
          resolve()
        })
      })
    },
    // 后端超时，前端自动重置
    fedLogout ({commit}) {
      return new Promise((resolve, reject) => {
        commit('RESET_USER')
        removeToken()
        resolve()
      })
    }
  }

}

export default user
