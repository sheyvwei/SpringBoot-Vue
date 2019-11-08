import request from '@/utils/request.js'

export function getUserList () {
  return request({
    url: '/user/getList',
    method: 'get'
  })
}

// 获取所有角色
export function getAllRoles () {
  return request({
    url: '/user/getAllRoles',
    method: 'get'
  })
}

// 创建用户
export function createUser (User) {
  console.log(User)
  return request({
    url: '/user/create',
    method: 'post',
    params: {
      userName: User.username,
      password: User.password,
      roleId: User.roleId
    }
  })
}

// 删除
export function deleteUser (User) {
  return request({
    url: 'user/delete',
    method: 'post',
    params: {
      ids: User.id
    }
  })
}
