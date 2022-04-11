package com.qingyun.db.service.impl;

import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.bean.Teacher;
import com.qingyun.db.mapper.TeacherMapper;
import com.qingyun.db.service.DepartmentService;
import com.qingyun.db.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public List<Teacher> getAllTeachers(int offset, int limit, Teacher queryCondition) {
        return teacherMapper.getAllTeachers(offset, limit, queryCondition);
    }

    @Override
    public int getTotalRows(Teacher queryCondition) {
        return teacherMapper.getTotalRows(queryCondition);
    }

    @Override
    public void insertTeacher(Teacher teacher) throws ParameterNotRightException {
        //  校验院系id是否存在
        if (teacher.getDepartmentId()==null || departmentService.getDepartmentById(teacher.getDepartmentId())==null) {
            throw new ParameterNotRightException("院系不存在");
        }
        teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        return teacherMapper.getTeacherById(id);
    }
}
