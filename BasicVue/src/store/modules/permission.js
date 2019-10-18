/**
 * 根据用户权限，进行路由的叠加 = 常量路由 + 权限路由
 */
import {constantRouter, accessRouter} from '@/router/index'

function hasPermission (menuList, router) {
  if (router.munu) {
    return menuList.indexOf(router.menu) > -1
  } else {
    return true
  }
}
// 根据权限列表筛选路由
function filterAccessRouter (accessRouter, menuList) {
  const accessRouters = accessRouter.filter(router => {
    if (hasPermission(menuList, router)) {
      if (router.children && router.children.length) {
        // 如果这个路由的下一级路由有的话，递归
        router.children = filterAccessRouter(router.children, menuList)
        return (router.children && router.children.length)
      }
      return true
    }
    return false
  })
  return accessRouters
}
const permission = {
  state: {
    routers: constantRouter, // 初始化为consantRouter，后面进行合并
    addRouters: [] // 权限路由
  },
  mutations: {
    SET_ROUTER: (state, router) => {
      state.addRouters = router
      // 将固定路由和新增路由合并
      state.routers = constantRouter.concat(router)
    }
  },
  actions: {
    // 权限计算
    GenerateRoutes ({commit}, userInfo) {
      return new Promise((resolve, reject) => {
        const roleName = userInfo.roleName
        const menuList = userInfo.menuList
        let permissionRouter
        if (roleName === '60') {
          permissionRouter = accessRouter
        } else {
          permissionRouter = filterAccessRouter(accessRouter, menuList)
        }
        commit('SET_ROUTER', permissionRouter)
        resolve()
      })
    }
  }
}

export default permission
