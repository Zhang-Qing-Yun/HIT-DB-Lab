import request from '@/utils/request'

export default {

    //  分页查询学生列表
    getAllStudents(current, queryCondition) {
        return request({
            url: '/student/getAllStudents/' + current,
            method: 'post',
            data: queryCondition
        })
    }

}