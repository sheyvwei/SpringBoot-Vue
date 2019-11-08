<template>
  <div>
    <div>
      <el-form>
        <el-form-item>
          <el-button type="primary" icons="plus" @click="showCreate">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- border是否带有纵向边框 -->
    <!-- fit列的宽度是否自撑开 -->
    <!-- highlight-current-row是否要高亮当前行 -->
    <el-table :data="list" element-loading-text="拼命加载中">
      <!-- <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="scope.$index"></span>
        </template>
      </el-table-column> -->
      <el-table-column type="index" width="80px" label="序号"></el-table-column>
      <el-table-column align="center" label="用户名" width="100px" prop="username"></el-table-column>
      <el-table-column align="center" label="角色" width="100px" prop="roleName">
        <template slot-scope="scope">
          <el-tag type=success  v-text="scope.row.roleName" v-if="scope.row.roleName==='05'"></el-tag>
          <el-tag type="primary" v-text="scope.row.roleName" v-else></el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime" width="170"></el-table-column>
      <el-table-column align="center" label="最近修改时间" prop="updateTime" width="170"></el-table-column>
      <el-table-column align="center" label="管理" width="220">
        <template slot-scope="scope">
          <el-button type="primary" @click="showUpdate(scope.$index)">修改</el-button>
          <el-button type="danger"  @click="remove(scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- <el-pagination
      layout="total, sizes, prev, pager, next, jumper"
    >
    </el-pagination> -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisiable">
      <el-form label-width="80px" label-position="left"
              style="width: 300px; margin-left: 50px">
        <el-form-item label="用户名" required>
          <el-input type="text" v-model="User.username"></el-input>
        </el-form-item>
        <el-form-item v-if="dialogStatus==='create'" label="密码" required>
          <el-input type="password"  v-model="User.password"></el-input>
        </el-form-item>
        <el-form-item v-else label="新密码" required>
          <el-input v-model="User.password"></el-input>
        </el-form-item>
        <el-form-item label="角色" required>
          <el-select placeholder="请选择" v-model="User.roleId">
            <el-option v-for="item in roles" :key="item.id" :label="item.roleName" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogFormVisiable=false">取 消</el-button>
        <el-button type="success" v-if="dialogStatus==='create'" @click="create">创建</el-button>
        <el-button type="primary" v-else>修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {getUserList, getAllRoles, createUser, deleteUser} from '@/api/user.js'
export default {
  name: 'User',
  data () {
    return {
      textMap: {
        create: '新增用户',
        update: '编辑'
      },
      dialogStatus: 'create', // 默认新增
      dialogFormVisiable: false, // 弹出框
      list: [], // 用户表格数据
      roles: [],
      User: {
        username: '',
        password: '',
        roleId: '',
        userId: ''
      }
    }
  },
  created () {
    this.getList() // 查询用户
    this.getRoles()
  },
  methods: {
    getList () {
      getUserList().then(response => {
        // console.log(response)
        this.list = response.data
      })
    },
    showCreate () {
      this.User.username = ''
      this.User.password = ''
      this.User.roleId = ''
      this.dialogStatus = 'create'
      this.dialogFormVisiable = true
    },
    showUpdate ($index) {
      let user = this.list[$index]
      console.log(user)
      this.User.username = user.username
      this.User.password = user.password
      this.User.roleId = user.roleId
      this.dialogStatus = 'update'
      this.dialogFormVisiable = true
    },
    getRoles () {
      getAllRoles().then(response => {
        this.roles = response.data
        // console.log(this.roles)
      })
    },
    create () {
      createUser(this.User).then(response => {
        this.getList()
        this.dialogFormVisiable = false
      })
    },
    remove ($index) {
      console.log('delete')
      let user = this.list[$index]
      console.log(user)
      deleteUser(user).then(response => {
        this.getList()
      })
    }
  }
}
</script>
