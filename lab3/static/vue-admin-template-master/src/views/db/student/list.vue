<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="queryCondition.id" placeholder="学号/ID"/>
      </el-form-item>

      <el-form-item>
        <el-input v-model="queryCondition.name" placeholder="姓名"/>
      </el-form-item>

      <el-form-item>
        <el-input v-model="queryCondition.classId" placeholder="班号"/>
      </el-form-item>

      <el-form-item>
        <el-input v-model="queryCondition.firstYear" placeholder="入学年份"/>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getAllStudents()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

      <el-table-column prop="id" label="学号" width="220" />

      <el-table-column prop="name" label="姓名" width="220" />

      <el-table-column label="性别" width="120">
        <!-- scope代表整个表格，scope.row代表表格的当前行 -->
        <template slot-scope="scope">
          {{ scope.row.sex===true?'男':'女' }}
        </template>
      </el-table-column>

      <el-table-column prop="classId" label="班号" width="200"/>

      <el-table-column prop="firstYear" label="入学年份" width="250"/>

      <el-table-column label="操作" width="350" align="center">
        <template slot-scope="scope">
          <router-link :to="'/student/update/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>

          <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteStudentById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

       <!-- 分页，底层会自动将点击的数字传到函数里 -->
    <el-pagination
      :current-page="current"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getAllStudents"  
    />
  </div>
</template>

<script>
import student from '@/api/db/student'
export default {
    data(){  // 定义变量和初始值
        return {
            list:null,  // 查询返回值
            current:1,  // 当前页
            limit:50,  // 每一页的数量
            total:0,  // 总数量
            queryCondition:{}  // 查询条件
        }
    },

    created() {  // 页面渲染之前执行
        this.getAllStudents()
    },

    methods:{  // 创建要使用的方法
        getAllStudents(current = 1) {
          this.current = current
          student.getAllStudents(this.current, this.queryCondition)
          .then(response => {  // 请求成功，response是请求返回值
              this.list = response.data.items
              this.total = response.data.page.rows
              this.limit = response.data.page.limit
          })
          .catch(error => {
              console.log(error)
          })  // 请求有异常
        },
        //  清空查询结果
        resetData() {
          this.queryCondition = {}
          this.getAllStudents()
        },
        //  删除学生
        deleteStudentById(id) {
          this.$confirm('此操作将永久删除该学生信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            student.deleteStudentById(id)
              .then(response => {
                //  删除
                this.getAllStudents()
                //  提示信息
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.getAllStudents()
              })
          }).catch((response) => { // 失败
            if (!(response === 'cancel')) {
              this.$message({
                type: 'error',
                message: '删除失败'
              })
            }
          })
        }
    }

}
</script>