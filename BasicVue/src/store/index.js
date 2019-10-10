import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app' // 记录侧标栏切换状态
import user from './modules/user'
import settings from './modules/settings'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    settings
  },
  getters
})

export default store
