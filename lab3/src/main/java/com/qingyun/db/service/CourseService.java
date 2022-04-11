package com.qingyun.db.service;

import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.bean.Course;
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
public interface CourseService extends IService<Course> {
    /**
     * 根据id去查询课程
     */
    Course getCourseById(Integer id);

    /**
     * 新增一个课程
     */
    void insertCourse(Course course) throws ParameterNotRightException;

    /**
     * 查询符合条件的课程的数量
     */
    int getTotalRows(Course queryCondition);

    /**
     * 分页查询符合条件的课程信息
     */
    List<Course> getAllCourses(int offset, int limit, Course queryCondition);
}
