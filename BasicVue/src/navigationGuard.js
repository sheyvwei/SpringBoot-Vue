import router from './router' // 获取路由
import nprogress from 'nprogress' // 进度条
import 'nprogress/nprogress.css'
import {getToken} from '@/utils/auth'
import store from './store'
const whiteList = ['/login', '/404']// 白名单,不需要登录的路由

// 全局守卫
// beforeEach 定义一个全局前置守卫
// to:进入哪个路由， from从哪个路由离开， next: 函数，决定是否展示你要看到的路由页面
router.beforeEach((to, from, next) => {
  nprogress.start()
  // 已登录
  console.log(getToken())
  if (getToken()) {
    if (to.path === '/login') {
      // 已登录直接进入主页
      next('/')
      nprogress.done() // 如果当前页面是仪表板，则在每次挂接后不会触发，因此请手动处理
    } else if (!store.getters.role) {
      // 获取角色列表
      store.dispatch('GetInfo').then(() => {
        next({...to})
      })
    } else {
      next()
    }
  } else if (whiteList.indexOf(to.path) !== -1) {
    // indexOf 查找数据在数组相应位置的index， 没有返回-1
    next()// 可以进入白名单
  } else {
    // 跳转登录
    store.commit('RESET_USER')
    next('/login')
    nprogress.done()
  }
})
router.afterEach(() => {
  nprogress.done()
})
