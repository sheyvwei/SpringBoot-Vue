<template>
  <div>
    <el-scrollbar wrap-class="scrollbar-wrapper">
    <!-- collapse 是否水平折叠收起菜单（仅在 mode 为 vertical 时可用 -->
    <!-- text-color菜单文字颜色 -->
    <!-- unique-opened是否只保持一个子菜单的展开 -->
    <!-- active-text-color当前激活菜单的文字颜色 -->
    <!-- collapse-transition是否开启折叠动画 -->
    <el-menu
      :collapse="isCollapse"
      :background-color="variables.menuBg"
      :text-color="variables.menuText"
      :unique-opened="false"
      :active-text-color="variables.menuActiveText"
      :collapse-transition="false"
      mode="vertical"
      >
      <!-- 暂时获取router/index的路由获取 -->
      <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" ></sidebar-item>
    </el-menu>
  </el-scrollbar>
  </div>
</template>
<script>
import {mapGetters} from 'vuex'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
export default {
  name: 'Sidebar',
  components: {
    SidebarItem
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'allRouters'
    ]),
    routes () {
      // 获取index的路由
      // console.log(this.$router.options.routes)
      // return this.allRouters
      return this.$store.getters.allRouters
    },
    isCollapse () {
      // 获取store的sidebar状态
      return !this.sidebar.opened
    },
    variables () {
      return variables
    }
  }
}
</script>
