<template>
  <el-breadcrumb class="app-breadcrumb" separator=">">
    <!-- transition的name会导向样式 .breadcrumb-enter-active等6个过渡类名 -->
    <transition-group name="breadcrumb">
      <el-breadcrumb-item v-for="(item, index) in levelList" :key="item.path">
        <!-- noRedirect和路由最后一个不必有链接 -->
        <span v-if="item.redirect==='noRedirect'|| index===levelList.length-1" class="no-redirect">{{ item.meta.title}}</span>
        <!-- @click.handleLink事件修饰符，避免a标签跳转链接，只执行click事件 -->
        <a v-else @click.prevent="handleLink(item)">{{item.meta.title}}</a>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>
<script>
import pathToRegexp from 'path-to-regexp'
export default {
  name: 'Breadcrumb',
  data () {
    return {
      levelList: null
    }
  },
  watch: {
    // 路由变化就重新获取
    $route () {
      this.getBreadcrumb()
    }
  },
  created () {
    // 刚进入也要获取
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb () {
      // url匹配的路由记录会放在 $route.matched这个数据
      let matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      const first = matched[0]
      // if (!this.isDashboard(first)) {
      //   // concat 连接两个数组
      //   matched = [{path: '/dashboard', meta: {title: 'Dashboard'}}].concat(matched)
      // }
      if (!this.isDashboard(first)) {
        matched = [{path: '/dashboard', meta: { title: 'Dashboard' }}].concat(matched)
      }
      // console.log(matched)
      this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
      // this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
    },
    isDashboard (route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return name.trim().toLocaleLowerCase() === 'Dashboard'.toLocaleLowerCase()
    },
    handleLink (item) {
      const {redirect, path} = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(this.pathCompile(path))
    },
    pathCompile (path) {
      const { params } = this.$route
      // console.log(params)
      var toPath = pathToRegexp.compile(path)
      // console.log(path)
      // let keys = []
      // let reg = pathToRegexp('/user/:id', keys, {end: false})
      // console.log(reg) // /^\/user(?:\/(?=$))?(?=\/|$)/i
      // console.log(toPath)
      return toPath(params)
    }

  }
}
</script>
<style lang="scss">
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;
  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>
