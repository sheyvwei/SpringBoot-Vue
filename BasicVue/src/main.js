// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'// 配置element-ui样式
import '@/icons' // 导入自定义组件icon
import '@/styles/index.scss' // 导入styles/.css的样式
import '@/navigationGuard.js' // 路由守卫
import store from './store'
Vue.use(Element)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
