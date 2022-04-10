package com.qingyun.db.mapper;

import com.qingyun.db.bean.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 張青云
 * @since 2022-04-10
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    @Insert("<script>" +
            "INSERT INTO department" +
            "        (`dname`)" +
            "        VALUES" +
            "        <foreach collection ='list' item='d' separator =','>" +
            "            (#{d.dname})" +
            "        </foreach >" +
            "</script>")
    void insertDepartments(@Param("list") List<Department> list);

    @Insert("insert into department(`dname`) values (#{department.dname})")
    void insertDepartment(@Param("department") Department department);
}
