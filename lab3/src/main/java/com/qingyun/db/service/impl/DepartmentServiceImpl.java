package com.qingyun.db.service.impl;

import com.qingyun.db.bean.Department;
import com.qingyun.db.mapper.DepartmentMapper;
import com.qingyun.db.service.DepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments(int offset, int limit, Department queryCondition) {
        return departmentMapper.getAllDepartments(offset, limit, queryCondition);
    }

    @Override
    public int getTotalRows(Department queryCondition) {
        return departmentMapper.getTotalRows(queryCondition);
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }
}
