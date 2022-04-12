import request from '@/utils/request'

export default {
    //  分页查询班级列表
    getAllClasses(current, queryCondition) {
        return request({
            url: '/class/getAllClasses/' + current,
            method: 'post',
            data: queryCondition
        })
    },

    //  新增一个班级
    insertClass(c) {
        return request({
            url: '/class/insertClass/',
            method: 'post',
            data: c
        })
    }
}