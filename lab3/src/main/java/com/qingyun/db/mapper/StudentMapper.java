package com.qingyun.db.mapper;

import com.qingyun.db.bean.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
