<template>
    <div class="app-container">
        <el-form label-width="120px">

        <el-form-item label="班主任编号">
            <el-input v-model="c.tid"/>
        </el-form-item>

        <el-form-item label="所属院系编号">
            <el-input v-model="c.departmentId"/>
        </el-form-item>

        <el-form-item>
            <el-button :disabled="saveBtnDisabled" type="primary" @click="insertClass">提交</el-button>
        </el-form-item>
        </el-form>
    </div>
</template>

<script>
import cls from '@/api/db/class'
export default {
    data() {
        return {
            c:{},
            saveBtnDisabled:false  // 按钮是否禁用
        }
    },
    created() {
        
    },
    methods:{
        //  添加一个班级
        insertClass() {
            cls.insertClass(this.c)
                .then(response => {
                    //  提示信息
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
                    })
                    //  修改路由跳转到列表页面
                    this.$router.push({ path: '/class/getAllClasses' })
                })
        }
    }
}
</script>