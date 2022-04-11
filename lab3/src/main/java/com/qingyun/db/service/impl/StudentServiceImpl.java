package com.qingyun.db.service.impl;

import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.bean.Class;
import com.qingyun.db.bean.Student;
import com.qingyun.db.mapper.StudentMapper;
import com.qingyun.db.service.ClassService;
import com.qingyun.db.service.StudentService;
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
 * @since 2022-04-08
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassService classService;

    @Override
    public List<Student> getAllStudents(int offset, int limit, Student queryCondition) {
        return studentMapper.getAllStudents(offset, limit, queryCondition.getId(), queryCondition.getName(),
                queryCondition.getClassId(), queryCondition.getFirstYear());
    }

    @Override
    public int getTotalRows(Student queryCondition) {
        return studentMapper.getTotalRows(queryCondition.getId(), queryCondition.getName(),
                queryCondition.getClassId(), queryCondition.getFirstYear());
    }

    @Override
    public void insertStudent(Student student) throws ParameterNotRightException {
        //  检查班级是否存在
        if (student.getClassId() != null && classService.getClassById(student.getClassId()) != null) {
            throw new ParameterNotRightException("该班级不存在");
        }
        studentMapper.insertStudent(student);
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentMapper.deleteStudentById(id);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void updateStudentById(Integer id, Student student) throws ParameterNotRightException {
        //  检查班级是否存在
        if (student.getClassId() == null || classService.getClassById(student.getClassId()) == null) {
            throw new ParameterNotRightException("该班级不存在");
        }
        studentMapper.updateStudentById(id, student);
    }
}
