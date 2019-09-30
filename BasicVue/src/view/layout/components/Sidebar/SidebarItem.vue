<template>
  <div v-if="!item.hidden">
    <!-- el-menu 下的第一个template用来显示分组叫什么 -->
    <!-- 如果只有一个children，把children放在根节点,                    children有数据   alwaysShow -->
    <template v-if="hasOneShowingChild(item.children,item) && (!onlyOneChild.children||onlyOneChild.noShowingChildren)&&!item.alwaysShow">
      <router-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path)" >
          <el-menu-item :index="resolvePath(onlyOneChild.path)">
            <menu-item :icon="onlyOneChild.meta.icon" :title="onlyOneChild.meta.title" ></menu-item>
          </el-menu-item>
      </router-link>
    </template>
    <!-- 还需再次分类 分组里面的内容
    popper-append-to-body是否将弹出菜单插入至body 元素。在菜单的定位出现问题时，可尝试修改该属性-->
    <el-submenu v-else :index="resolvePath(item.path)"  ref="subMenu"  popper-append-to-body>
      <!-- 因为childre有多个，所以直接获取父组的标题和icon -->
      <template slot="title">
        <!-- <span v-if="item.meta">{{item.meta.icon}}</span> -->
        <menu-item v-if="item.meta" :icon="item.meta && item.meta.icon" :title="item.meta.title"></menu-item>
      </template>
      <!-- // 子标题 -->
      <sidebar-item
          v-for="child in item.children"
          :key="child.path"
          :is-nest="true"
          :item="child"
          :base-path="resolvePath(child.path)"
          class="nest-menu" />

    </el-submenu>
  </div>
</template>
<script>
import MenuItem from './MenuItem'
import path from 'path'
import {isExternal} from '@/utils/validate'
export default {
  name: 'SidebarItem',
  data () {
    return {
      detail: this.item,
      // icon:''
      onlyOneChild: null // 记录子路由信息
    }
  },
  props: {
    item: {
      type: Object,
      required: true
    },
    basePath: {
      type: String,
      default: ''
    }
  },
  components: {
    MenuItem
  },
  methods: {
    // 判断children中的路由个数
    hasOneShowingChild (children = [], parent) {
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          this.onlyOneChild = item
          return true
        }
      })
      // 经过filter后，如果只有一个children
      if (showingChildren.length === 1) {
        return true
      }
      if (showingChildren.length === 0) {
        //  ?? ...parent
        this.onlyOneChild = { ...parent, path: '', noShowingChildren: true }
        return true
      }
      return false
    },
    resolvePath (routePath) {
      // 解析是外链还是内部链接
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      // 作用：把一个路径或路径片段的序列解析为一个绝对路径
      return path.resolve(this.basePath, routePath)
    }
  }
}
</script>
