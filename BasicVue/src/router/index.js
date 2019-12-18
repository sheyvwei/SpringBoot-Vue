import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

const Layout = r => require.ensure([], () => r(require('@/view/layout/Layout.vue')), 'Layout')
const Dashboard = r => require.ensure([], () => r(require('@/view/dashboard')), 'dashboard')
const Login = r => require.ensure([], () => r(require('@/view/login/')), 'login')
const form = r => require.ensure([], () => r(require('@/view/form/index')), 'form')
const Article = r => require.ensure([], () => r(require('@/view/article/index')), 'article')
const User = r => require.ensure([], () => r(require('@/view/user/index')), 'user')
const Role = r => require.ensure([], () => r(require('@/view/user/role')), 'role')
/**
 * 常量路由
 */
export const constantRouter = [
  {
    path: '/login',
    name: 'login',
    component: Login,
    hidden: true
  }, {
    path: '/hello',
    name: 'HelloWorld',
    component: HelloWorld,
    hidden: true,
    children: [{
      path: '/hello',
      name: 'helloworld',
      component: form
    }]
  }, {
    path: '/',
    redirect: '/dashboard',
    component: Layout,
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: Dashboard,
      meta: {icon: 'dashboard', title: 'Dashboard'}
    }]
  }, {
    path: '/form',
    redirect: '/form/index',
    component: Layout,
    meta: {icon: 'dashboard', title: 'form标题'},
    children: [{
      path: 'index',
      name: 'Form',
      component: form,
      meta: { icon: 'form', title: 'Form' }
    }, {
      path: 'detail',
      name: 'detail',
      component: HelloWorld,
      meta: {icon: 'tree', title: 'Table'}
    }]
  }
]
export const accessRouter = [
  {
    path: '/system',
    component: Layout,
    redirect: '/system/article',
    name: '功能模块',
    meta: {title: '功能模块', icon: 'tree'},
    children: [
      {
        path: 'article',
        name: '文章',
        component: Article,
        meta: {title: '文章', icon: 'example'},
        menu: 'article'
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '用户权限', icon: 'table'},
    children: [
      {
        path: '', name: '用户列表', component: User, meta: {title: '用户列表', icon: 'user'}, menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: Role,
        meta: {title: '权限管理', icon: 'password'},
        menu: 'role'
      }
    ]
  }
]
export default new Router({
  routes: constantRouter
})
