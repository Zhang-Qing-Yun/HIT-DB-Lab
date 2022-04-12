<template>
    <div class="app-container">
        <el-form label-width="120px">
            <el-form-item label="课程名">
                <el-input v-model="course.cname"/>
            </el-form-item>

            <el-form-item label="授课教师编号">
                <el-input v-model="course.tid"/>
            </el-form-item>

            <el-form-item>
                <el-button :disabled="saveBtnDisabled" type="primary" @click="insertCourse">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import courseApi from '@/api/db/course'
export default {
    data() {
        return {
            course:{},
            saveBtnDisabled:false  // 按钮是否禁用
        }
    },
    created() {
        
    },
    methods:{
        //  添加一个课程
        insertCourse() {
            courseApi.insertCourse(this.course)
                .then(response => {
                    //  提示信息
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
                    })
                    //  修改路由跳转到列表页面
                    this.$router.push({ path: '/course/getAllCourses' })
                })
        }
    }
}
</script>