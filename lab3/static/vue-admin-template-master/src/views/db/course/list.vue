<template>
    <div class="app-container">
        <!--查询表单-->
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="queryCondition.id" placeholder="课程编号/ID"/>
            </el-form-item>

            <el-form-item>
                <el-input v-model="queryCondition.cname" placeholder="课程名"/>
            </el-form-item>

            <el-form-item>
                <el-input v-model="queryCondition.tid" placeholder="授课教师编号"/>
            </el-form-item>

            <el-button type="primary" icon="el-icon-search" @click="getAllCourses()">查询</el-button>
            <el-button type="default" @click="resetData()">清空</el-button>
        </el-form>

        <!-- 表格 -->
        <el-table
        :data="list"
        border
        fit
        highlight-current-row>

            <el-table-column prop="id" label="课程编号" width="220" />

            <el-table-column prop="cname" label="课程名" width="220" />

            <el-table-column prop="tid" label="授课教师编号" width="200"/>

        </el-table>

        <!-- 分页，底层会自动将点击的数字传到函数里 -->
        <el-pagination
        :current-page="current"
        :page-size="limit"
        :total="total"
        style="padding: 30px 0; text-align: center;"
        layout="total, prev, pager, next, jumper"
        @current-change="getAllCourses"  
        />
    </div>
</template>

<script>
import course from '@/api/db/course'
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
        this.getAllCourses()
    },

    methods:{  // 创建要使用的方法
        getAllCourses(current = 1) {
            this.current = current
            course.getAllCourses(this.current, this.queryCondition)
            .then(response => {  // 请求成功，response是请求返回值
                this.list = response.data.items
                this.total = response.data.page.rows
                this.limit = response.data.page.limit
            })
        },
        //  清空查询结果
        resetData() {
            this.queryCondition = {}
            this.getAllCourses()
        }
    }

}
</script>