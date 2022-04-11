package com.qingyun.db.test.teacher;

import com.qingyun.db.bean.Teacher;
import com.qingyun.db.mapper.TeacherMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description：
 * @author: 張青云
 * @create: 2022-04-11 20:06
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TeacherTest {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void getTeachersTest() {
        Teacher queryCondition = new Teacher();
        System.out.println(teacherMapper.getAllTeachers(0, 10, queryCondition).toString());
        System.out.println(teacherMapper.getTotalRows(queryCondition));
    }
}
