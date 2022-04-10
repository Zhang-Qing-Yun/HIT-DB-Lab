package com.qingyun.db.service.impl;

import com.qingyun.db.bean.Student;
import com.qingyun.db.mapper.StudentMapper;
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

    @Override
    public List<Student> getAllStudents(int offset, int limit, Student queryCondition) {
        return studentMapper.getAllStudents(offset, limit, queryCondition.getId(), queryCondition.getName(),
                queryCondition.getClassId(), queryCondition.getFirstYear());
    }

    @Override
    public int getTotalRows() {
        return studentMapper.getTotalRows();
    }
}
