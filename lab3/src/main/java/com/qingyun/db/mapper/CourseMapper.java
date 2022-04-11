package com.qingyun.db.mapper;

import com.qingyun.db.bean.Course;
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
public interface CourseMapper extends BaseMapper<Course> {
    @Insert("<script>" +
            "INSERT INTO course" +
            "        (cname,tid)" +
            "        VALUES" +
            "        <foreach collection ='list' item='c' separator =','>" +
            "            (#{c.cname},#{c.tid})" +
            "        </foreach >" +
            "</script>")
    void insertCourses(@Param("list") List<Course> list);

    @Insert("insert into course(cname,tid) values (#{course.cname},#{course.tid})")
    void insertCourse(@Param("course") Course course);

    @Select("select * from course where id=#{id}")
    Course getCourseById(Integer id);

    @Select("<script>" +
            "select * from course " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.cname != null'>" +
            "and `cname` = #{queryCondition.cname} " +
            "</when>" +
            "<when test='queryCondition.tid != null'>" +
            "and tid = #{queryCondition.tid} " +
            "</when>" +
            "order by id desc " +
            "limit #{offset}, #{limit}" +
            "</script>")
    List<Course> getAllCourses(int offset, int limit, @Param("queryCondition") Course queryCondition);

    @Select("<script>" +
            "select count(*) from course " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.cname != null'>" +
            "and `cname` = #{queryCondition.cname} " +
            "</when>" +
            "<when test='queryCondition.tid != null'>" +
            "and tid = #{queryCondition.tid} " +
            "</when>" +
            "</script>")
    int getTotalRows(@Param("queryCondition") Course queryCondition);
}
