package com.qingyun.db.service.impl;

import com.qingyun.db.bean.Student;
import com.qingyun.db.mapper.StudentMapper;
import com.qingyun.db.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 張青云
 * @since 2022-04-08
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
