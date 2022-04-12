package com.qingyun.db.mapper;

import com.qingyun.db.bean.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("<script>" +
            "select * from department " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.dname != null'>" +
            "and `dname` = #{queryCondition.dname} " +
            "</when>" +
            "limit #{offset}, #{limit}" +
            "</script>")
    List<Department> getAllDepartments(int offset, int limit, @Param("queryCondition") Department queryCondition);

    @Select("<script>" +
            "select count(*) from department " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.dname != null'>" +
            "and `dname` = #{queryCondition.dname} " +
            "</when>" +
            "</script>")
    int getTotalRows(@Param("queryCondition") Department queryCondition);

    @Select("select * from department where id=#{id}")
    Department getDepartmentById(Integer id);
}
