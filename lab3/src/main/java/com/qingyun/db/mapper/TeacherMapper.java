package com.qingyun.db.mapper;

import com.qingyun.db.bean.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
    void insertTeachers(List<Teacher> list);

    @Insert("insert into teacher(`name`,department_id) values (#{teacher.name}, #{teacher.departmentId})")
    void insertTeacher(Teacher teacher);
}
