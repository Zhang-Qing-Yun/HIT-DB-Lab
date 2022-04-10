import request from '@/utils/request'

export default {

    //  分页查询学生列表
    getAllStudents(current, queryCondition) {
        return request({
            url: '/student/getAllStudents/' + current,
            method: 'post',
            data: queryCondition
        })
    },

    //   删除一个学生
    deleteStudentById(id) {
        return request({
            url: '/student/deleteStudentById/' + id,
            method: 'delete'
        })
    },

    //   删除一个学生
    insertStudent(student) {
        return request({
            url: '/student/insertStudent/',
            method: 'post',
            data: student
        })
    },

    //  根据 学号/id 查询一个学生的信息
    getStudentById(id) {
        return request({
            url: '/student/getStudentById/' + id,
            method: 'get'
        })
    },

    //  根据id修改该学生的信息
    updateStudentById(id, student) {
        return request({
            url: '/student/updateStudentById/' + id,
            method: 'post',
            data: student
        })
    }
}