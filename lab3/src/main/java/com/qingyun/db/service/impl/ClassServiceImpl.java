package com.qingyun.db.service.impl;

import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.bean.Class;
import com.qingyun.db.mapper.ClassMapper;
import com.qingyun.db.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingyun.db.service.DepartmentService;
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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public List<Class> getAllClasses(int offset, int limit, Class queryCondition) {
        return classMapper.getAllClasses(offset, limit, queryCondition);
    }

    @Override
    public int getTotalRows(Class queryCondition) {
        return classMapper.getTotalRows(queryCondition);
    }

    @Override
    public Class getClassById(Integer id) {
        return classMapper.getClassById(id);
    }

    @Override
    public void insertClass(Class c) throws ParameterNotRightException {
        //  校验是否存在该教师
        if (c.getTid() == null || teacherService.getTeacherById(c.getId()) == null) {
            throw new ParameterNotRightException("班主任信息不正确");
        }
        //  校验院系信息是否正确
        if (c.getDepartmentId() == null || departmentService.getDepartmentById(c.getDepartmentId()) == null) {
            throw new ParameterNotRightException("院系信息不正确");
        }
        classMapper.insertClass(c);
    }
}
