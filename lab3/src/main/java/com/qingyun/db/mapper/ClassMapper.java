package com.qingyun.db.mapper;

import com.qingyun.db.bean.Class;
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
public interface ClassMapper extends BaseMapper<Class> {
    @Insert("<script>" +
            "INSERT INTO class" +
            "        (tid,department_id)" +
            "        VALUES" +
            "        <foreach collection ='list' item='c' separator =','>" +
            "            (#{c.tid},#{c.departmentId})" +
            "        </foreach >" +
            "</script>")
    void insertClasses(@Param("list") List<Class> list);

    @Insert("insert into class(tid,department_id) values (#{c.tid},#{c.departmentId})")
    void insertClass(@Param("c") Class c);

    @Select("<script>" +
            "select * from class " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.tid != null'>" +
            "and `tid` = #{queryCondition.tid} " +
            "</when>" +
            "<when test='queryCondition.departmentId != null'>" +
            "and department_id = #{queryCondition.departmentId} " +
            "</when>" +
            "order by id desc " +
            "limit #{offset}, #{limit}" +
            "</script>")
    List<Class> getAllClasses(int offset, int limit, @Param("queryCondition") Class queryCondition);

    @Select("<script>" +
            "select count(*) from class " +
            "where true " +
            "<when test='queryCondition.id != null'>" +
            "and id = #{queryCondition.id} " +
            "</when>" +
            "<when test='queryCondition.tid != null'>" +
            "and `tid` = #{queryCondition.tid} " +
            "</when>" +
            "<when test='queryCondition.departmentId != null'>" +
            "and department_id = #{queryCondition.departmentId} " +
            "</when>" +
            "</script>")
    int getTotalRows(@Param("queryCondition") Class queryCondition);

    @Select("select * from class where id=#{id}")
    Class getClassById(Integer id);
}
