<template>
  <div class="app-container">
    <el-form label-width="120px">

      <el-form-item label="学生姓名">
        <el-input v-model="student.name"/>
      </el-form-item>

      <el-form-item label="性别">
        <el-select v-model="student.sex" clearable placeholder="请选择">
          <el-option :value="false" label="女"/>
          <el-option :value="true" label="男"/>
        </el-select>
      </el-form-item>

      <el-form-item label="班级">
        <el-input v-model="student.classId"/>
      </el-form-item>

      <el-form-item label="入学年份">
        <el-input v-model="student.firstYear"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="insertOrUpdateStudent">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import studentApi from '@/api/db/student'
export default {
    data() {
        return {
            student:{},
            saveBtnDisabled:false  // 按钮是否禁用
        }
    },
    created() {
        this.init()
    },
    watch: {  // 监听
        $route(to, from) {  // 路由变化方式，监听路由变化的固定写法，$route表示当前路由信息
            this.init()
        }
    },
    methods:{
        init() {
            // 判断路径中是否有id
            if (this.$route.params && this.$route.params.id) {
                const id = this.$route.params.id  // 从路径中获取id
                this.getStudentById(id)
            } else {
                this.student = {}
            }
        },
        //  添加或修改学生信息
        insertOrUpdateStudent() {
            if(this.student.id) {
                this.updateStudentById()
            } else {
                //  新增一个学生
                this.insertStudent()
            }
        },
        //  添加一个学生
        insertStudent() {
            studentApi.insertStudent(this.student)
                .then(response => {
                    //  提示信息
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
                    })
                    //  修改路由跳转到列表页面
                    this.$router.push({ path: '/student/allStudents' })
                }).catch(erroe => {
                    
                })
        },
        //  修改学生信息
        updateStudentById() {
            studentApi.updateStudentById(this.student.id, this.student)
                .then(response => {
                    //  提示信息
                    this.$message({
                        type: 'success',
                        message: '修改成功!'
                    })
                    //  修改路由跳转到列表页面
                    this.$router.push({ path: '/student/allStudents' })
                })
        },
        //  根据id查询学生信息
        getStudentById(id) {
            studentApi.getStudentById(id)
                .then(response => {
                    this.student = response.data.student
                }).catch(error => {
                    this.$message({
                        type: 'error',
                        message: '获取原始学生信息失败'
                    })
                })
        }
    }
}
</script>