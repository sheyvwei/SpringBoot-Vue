// 用来modules下的store
const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,

  role: state => state.user.role, // 用户有哪些角色
  addRouters: state => state.permission.addRouters, // 权限路由
  allRouters: state => state.permission.routers // 所有路由
}

export default getters
