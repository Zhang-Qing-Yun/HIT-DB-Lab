import request from '@/utils/request'

export default {
    //  分页查询院系列表
    getAllDepartments(current, queryCondition) {
        return request({
            url: '/department/getAllDepartments/' + current,
            method: 'post',
            data: queryCondition
        })
    }
}