import request from '@/utils/request'

export default {
    //  分页查询课程列表
    getAllCourses(current, queryCondition) {
        return request({
            url: '/course/getAllCourses/' + current,
            method: 'post',
            data: queryCondition
        })
    },

    //  新增一个课程
    insertCourse(course) {
        return request({
            url: '/course/insertCourse/',
            method: 'post',
            data: course
        })
    }
}