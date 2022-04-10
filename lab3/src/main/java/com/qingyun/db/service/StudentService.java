package com.qingyun.db.service;

import com.qingyun.db.bean.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 張青云
 * @since 2022-04-08
 */
public interface StudentService extends IService<Student> {
    /**
     * 分页查询所有的学生，也可以添加条件
     */
    List<Student> getAllStudents(int offset, int limit, Student queryCondition);

    /**
     * 学生的总数
     */
    int getTotalRows(Student queryCondition);

    /**
     * 添加一个学生
     */
    void insertStudent(Student student);

    /**
     * 根据学生id删除该学生
     */
    void deleteStudentById(Integer id);

    /**
     * 根据学生id查询学生信息
     */
    Student getStudentById(Integer id);

    /**
     * 根据id修改学生信息
     */
    void updateStudentById(Integer id, Student student);
}
