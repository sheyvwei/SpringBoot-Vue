<template>
  <div class="app-wrapper" :class="classObj">
    <div></div>
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <div :class="{'fixed-header': fixedHeader}">
        <navbar></navbar>
      </div>
      <app-main></app-main>
    </div>
  </div>

</template>
<script>
import Navbar from './components/Navbar'
import AppMain from './components/AppMain'
import Sidebar from './components/Sidebar'
export default {
  name: 'Layout',
  components: {
    Sidebar,
    Navbar,
    AppMain
  },
  computed: {
    // 获取store的open状态
    sidebar () {
      return this.$store.state.app.sidebar
    },
    // 默认为false, 没有fixed-header样式
    fixedHeader () {
      return this.$store.state.settings.fixedHeader
    },
    classObj () {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation // 默认为false
      }
    }
  }
}
</script>
<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
    &.mobile.openSidebar{
      position: fixed;
      top: 0;
    }
  }
  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }
  // 如果关闭左边栏的字，默认左边栏多出54px
  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
