package com.qingyun.db.service;

import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.bean.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 張青云
 * @since 2022-04-10
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 分页查询所有符合条件的教师，queryCondition所有属性都为null则是无条件查询
     */
    List<Teacher> getAllTeachers(int offset, int limit, Teacher queryCondition);

    /**
     * 获取符合条件的教师的数量
     */
    int getTotalRows(Teacher queryCondition);

    /**
     * 增加一个老师信息
     */
    void insertTeacher(Teacher teacher) throws ParameterNotRightException;

    /**
     * 根据id查询教师信息
     */
    Teacher getTeacherById(Integer id);
}
