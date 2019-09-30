/**
 * 判断是否是外部链接，如果是，则增加跳转另一个标签页
 * @param {*} path
 */
export function isExternal (path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}
