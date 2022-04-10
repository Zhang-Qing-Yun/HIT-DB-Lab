package com.qingyun.db.test.util;

import com.qingyun.db.bean.*;
import com.qingyun.db.bean.Class;
import com.qingyun.db.mapper.*;
import com.qingyun.db.util.DataGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：
 * @author: 張青云
 * @create: 2022-04-10 14:36
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataGeneratorTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void generateDepartmentTest() {
        List<Department> departments = DataGenerator.generateDepartments();
        departmentMapper.insertDepartments(departments);
    }

    @Test
    public void generateTeachersTest() {
        List<Teacher> teachers = DataGenerator.generateTeachers();
        teacherMapper.insertTeachers(teachers);
    }

    @Test
    public void generateClassesTest() {
        List<Class> classes = DataGenerator.generateClasses();
        classMapper.insertClasses(classes);
    }

    @Test
    public void generateCourses() {
        List<Course> courses = DataGenerator.generateCourses();
        courseMapper.insertCourses(courses);
    }

    @Test
    public void generateStudents() {
        List<Student> students = DataGenerator.generateStudents();
        int total = students.size();
        List<List<Student>> lists = new ArrayList<>();
        for (int i = 0; i < total / 5000; i++) {
            lists.add(new ArrayList<Student>());
        }
        for (int i = 0; i < total; i++) {
            lists.get(i / 5000).add(students.get(i));
        }
        for (int i = 0; i < lists.size(); i++) {
            studentMapper.insertStudents(lists.get(i));
        }
    }
}
