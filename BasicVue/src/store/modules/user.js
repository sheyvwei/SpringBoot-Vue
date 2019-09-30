//  登录时用户信息
// import store from '@/store'

const user = {
  state: {
    userName: '',
    role: '', // 角色
    menus: [], // 侧边栏的内容
    permissions: [] // 每个功能权限
  },
  mutation: {
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
    Login ({commit, state}, loginForm) {
      return new Promise((resolve, reject) => {

      })
    }
  }

}

export default user
