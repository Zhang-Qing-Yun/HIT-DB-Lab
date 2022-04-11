package com.qingyun.db.service;

import com.qingyun.db.bean.Department;
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
public interface DepartmentService extends IService<Department> {
    /**
     * 分页查询符合条件的院系
     */
    List<Department> getAllDepartments(int offset, int limit, Department queryCondition);

    /**
     * 符合条件的院系的条数
     */
    int getTotalRows(Department queryCondition);

    /**
     * 根据id查询院系信息
     */
    Department getDepartmentById(Integer id);

}
