import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon'

Vue.component('svg-icon', SvgIcon)

// require.context(目录,是否搜子目录，正则)
const req = require.context('./svg', false, /\.svg$/) // 搜索./svg文件夹下，不包含子目录，.svg结尾文件
const requireAll = requireContext => requireContext.keys().map(requireContext)
requireAll(req)
