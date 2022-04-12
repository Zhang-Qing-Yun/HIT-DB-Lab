<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
            <el-input v-model="queryCondition.id" placeholder="院系编号/ID"/>
        </el-form-item>

        <el-form-item>
            <el-input v-model="queryCondition.dname" placeholder="院系名"/>
        </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="getAllDepartments()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

        <el-table-column prop="id" label="院系编号" width="220" />

        <el-table-column prop="dname" label="院系名" width="220" />

    </el-table>

       <!-- 分页，底层会自动将点击的数字传到函数里 -->
    <el-pagination
      :current-page="current"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getAllDepartments"  
    />
  </div>
</template>

<script>
import department from '@/api/db/department'
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
        this.getAllDepartments()
    },

    methods:{  // 创建要使用的方法
        getAllDepartments(current = 1) {
            this.current = current
            department.getAllDepartments(this.current, this.queryCondition)
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
            this.getAllDepartments()
        }
    }

}
</script>