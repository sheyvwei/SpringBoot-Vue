// 用来modules下的store
const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,

  role: state => state.user.role // 用户有哪些角色
}

export default getters
