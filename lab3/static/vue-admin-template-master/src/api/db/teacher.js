import request from '@/utils/request'

export default {
    //  分页查询教师列表
    getAllTeachers(current, queryCondition) {
        return request({
            url: '/teacher/getAllTeachers/' + current,
            method: 'post',
            data: queryCondition
        })
    },

    //   新增一个教师
    insertTeacher(teacher) {
        return request({
            url: '/teacher/insertTeacher/',
            method: 'post',
            data: teacher
        })
    }
}