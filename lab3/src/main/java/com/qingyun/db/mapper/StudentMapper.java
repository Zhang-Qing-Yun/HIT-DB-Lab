package com.qingyun.db.mapper;

import com.qingyun.db.bean.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 張青云
 * @since 2022-04-08
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("<script>" +
            "select * from student " +
            "where true " +
            "<when test='id != null'>" +
            "and id = #{id} " +
            "</when>" +
            "<when test='name != null'>" +
            "and `name` = #{name} " +
            "</when>" +
            "<when test='classId != null'>" +
            "and class_id = #{classId} " +
            "</when>" +
            "<when test='firstYear != null'>" +
            "and first_year = #{firstYear} " +
            "</when>" +
            "limit #{offset}, #{limit}" +
            "</script>")
    List<Student> getAllStudents(int offset, int limit, Integer id, String name, Integer classId, Integer firstYear);

    @Select("<script>" +
            "select count(*) from student " +
            "where true " +
            "<when test='id != null'>" +
            "and id = #{id} " +
            "</when>" +
            "<when test='name != null'>" +
            "and `name` = #{name} " +
            "</when>" +
            "<when test='classId != null'>" +
            "and class_id = #{classId} " +
            "</when>" +
            "<when test='firstYear != null'>" +
            "and first_year = #{firstYear} " +
            "</when>" +
            "</script>")
    int getTotalRows(Integer id, String name, Integer classId, Integer firstYear);

    @Insert("<script>" +
            "INSERT INTO student" +
            "        (`name`,sex,class_id,first_year)" +
            "        VALUES" +
            "        <foreach collection ='list' item='stu' separator =','>" +
            "            (#{stu.name}, #{stu.sex}, #{stu.classId}, #{stu.firstYear})" +
            "        </foreach >" +
            "</script>")
    void insertStudents(@Param("list") List<Student> list);

    @Insert("insert into student(`name`,sex,class_id,first_year) " +
            "values (#{stu.name}, #{stu.sex}, #{stu.classId}, #{stu.firstYear})")
    void insertStudent(Student stu);

    @Delete("delete from student where id=#{id}")
    void deleteStudentById(Integer id);
}
