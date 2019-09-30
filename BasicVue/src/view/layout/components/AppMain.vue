<template>
  <section class="app-main">
    <!-- vue的动画效果，out-in--先淡出再淡入 -->
    <transition  name="fade-transform" mode="out-in">
      <router-view :key="key"/>
    </transition>
  </section>
</template>
<script>
export default {
  name: 'AppMain',
  computed: {
    // 用来强制刷新页面
    key () {
      return this.$route.path
    }
  }
}
</script>
<style scoped>
  .app-main{
    min-width: calc(100vh-50px);
    width: 100%;
    position: relative;
    overflow: hidden;
  }
  .fixed-header + .app-main {
    padding-top: 50px;
  }
</style>
<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
