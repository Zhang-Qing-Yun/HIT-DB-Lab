package com.qingyun.db.mapper;

import com.qingyun.db.bean.Teacher;
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
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Insert("<script>" +
            "INSERT INTO teacher" +
            "        (`name`,department_id)" +
            "        VALUES" +
            "        <foreach collection ='list' item='t' separator =','>" +
            "            (#{t.name}, #{t.departmentId})" +
            "        </foreach >" +
            "</script>")
    void insertTeachers(@Param("list") List<Teacher> list);

    @Insert("insert into teacher(`name`,department_id) values (#{teacher.name}, #{teacher.departmentId})")
    void insertTeacher(@Param("teacher") Teacher teacher);

    @Select("select * from teacher where id=#{id}")
    Teacher getTeacherById(Integer id);

    @Select("<script>" +
            "select * from teacher " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.name != null'>" +
            "and `name` = #{queryCondition.name} " +
            "</when>" +
            "<when test='queryCondition.departmentId != null'>" +
            "and department_id = #{queryCondition.departmentId} " +
            "</when>" +
            "order by id desc " +
            "limit #{offset}, #{limit}" +
            "</script>")
    List<Teacher> getAllTeachers(int offset, int limit, @Param("queryCondition") Teacher queryCondition);

    @Select("<script>" +
            "select count(*) from teacher " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.name != null'>" +
            "and `name` = #{queryCondition.name} " +
            "</when>" +
            "<when test='queryCondition.departmentId != null'>" +
            "and department_id = #{queryCondition.departmentId} " +
            "</when>" +
            "</script>")
    int getTotalRows(@Param("queryCondition") Teacher queryCondition);
}
