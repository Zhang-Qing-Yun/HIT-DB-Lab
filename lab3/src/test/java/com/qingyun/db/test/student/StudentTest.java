package com.qingyun.db.test.student;

import com.qingyun.db.bean.Student;
import com.qingyun.db.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description：
 * @author: 張青云
 * @create: 2022-04-09 23:26
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void getAllStudentsTest() {
        Student studentCondition = new Student();
        studentCondition.setId(1);
        studentService.getAllStudents(1, 20, studentCondition);
    }
}
