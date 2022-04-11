package com.qingyun.db.service.impl;

import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.bean.Course;
import com.qingyun.db.mapper.CourseMapper;
import com.qingyun.db.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingyun.db.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 張青云
 * @since 2022-04-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherService teacherService;

    @Override
    public Course getCourseById(Integer id) {
        return courseMapper.getCourseById(id);
    }

    @Override
    public void insertCourse(Course course) throws ParameterNotRightException {
        //  校验是否存在该教师
        if (course.getTid() == null || teacherService.getTeacherById(course.getId()) == null) {
            throw new ParameterNotRightException("班主任信息不正确");
        }
        courseMapper.insertCourse(course);
    }

    @Override
    public int getTotalRows(Course queryCondition) {
        return courseMapper.getTotalRows(queryCondition);
    }

    @Override
    public List<Course> getAllCourses(int offset, int limit, Course queryCondition) {
        return courseMapper.getAllCourses(offset, limit, queryCondition);
    }
}
