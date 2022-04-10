package com.qingyun.db.mapper;

import com.qingyun.db.bean.Course;
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
}
