<template>
    <div class="app-container">
        <el-form label-width="120px">

        <el-form-item label="姓名">
            <el-input v-model="teacher.name"/>
        </el-form-item>

        <el-form-item label="院系编号">
            <el-input v-model="teacher.departmentId"/>
        </el-form-item>

        <el-form-item>
            <el-button :disabled="saveBtnDisabled" type="primary" @click="insertTeacher">提交</el-button>
        </el-form-item>
        </el-form>
    </div>
</template>

<script>
import teacherApi from '@/api/db/teacher'
export default {
    data() {
        return {
            teacher:{},
            saveBtnDisabled:false  // 按钮是否禁用
        }
    },
    created() {
        
    },
    methods:{
        //  添加一个学生
        insertTeacher() {
            teacherApi.insertTeacher(this.teacher)
                .then(response => {
                    //  提示信息
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
                    })
                    //  修改路由跳转到列表页面
                    this.$router.push({ path: '/teacher/getAllTeachers' })
                })
        }
    }
}
</script>