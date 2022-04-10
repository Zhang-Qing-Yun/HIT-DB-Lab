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
//    @Select("<script>" +
//            "select * from student " +
//            "where true " +
//            "<if test='#{id} != null'>" +
//            "and id = #{id} " +
//            "</if>" +
//            "<if test='#{name} != null'>" +
//            "and name = #{name} " +
//            "</if>" +
//            "<if test='#{classId} != null'>" +
//            "and class_id = #{classId} " +
//            "</if>" +
//            "<if test='#{firstYear} != null'>" +
//            "and first_year = #{firstYear} " +
//            "</if>" +
//            "limit #{offset}, #{limit}" +
//            "</script>")
    @Select("select * from student where true and id = #{id} and name = #{name} " +
            "and class_id = #{classId} and first_year = #{firstYear} limit #{offset}, #{limit}")
    List<Student> getAllStudents(int offset, int limit, Integer id, String name, Integer classId, Integer firstYear);

    @Select("select count(*) from student")
    int getTotalRows();

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
