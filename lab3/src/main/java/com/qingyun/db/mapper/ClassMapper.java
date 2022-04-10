package com.qingyun.db.mapper;

import com.qingyun.db.bean.Class;
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
public interface ClassMapper extends BaseMapper<Class> {
    @Insert("<script>" +
            "INSERT INTO class" +
            "        (tid,department_id)" +
            "        VALUES" +
            "        <foreach collection ='list' item='c' separator =','>" +
            "            (#{c.tid},#{c.departmentId})" +
            "        </foreach >" +
            "</script>")
    void insertClasses(List<Class> list);

    @Insert("insert into class(tid,department_id) values (#{c.tid},#{c.departmentId})")
    void insertClass(Class c);
}
