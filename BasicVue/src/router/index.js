import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

const Layout = r => require.ensure([], () => r(require('@/view/layout/Layout.vue')), 'Layout')
const Dashboard = r => require.ensure([], () => r(require('@/view/dashboard')), 'dashboard')
const Login = r => require.ensure([], () => r(require('@/view/login/')), 'login')
const form = r => require.ensure([], () => r(require('@/view/form/index')), 'form')
export default new Router({
  routes: [
    {
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
      path: '/login',
      name: 'login',
      component: Login,
      hidden: true
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
})
