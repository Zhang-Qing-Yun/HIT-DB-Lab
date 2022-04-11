package com.qingyun.db.util;

import com.qingyun.db.bean.*;
import com.qingyun.db.bean.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @description： 数据生成器
 * @author: 張青云
 * @create: 2022-04-08 21:30
 **/
public final class DataGenerator {
    //  最小入学年份
    private static final int MIN_FIRST_YEAR = 2000;

    //  最大入学年份
    private static final int MAX_FIRST_YEAR = 2020;

    //  每个入学年份要生成的学生的数量
    private static final int YEAR_NUM = 5000;

    //  院系的数量
    private static final int DEP_NUM = 13;

    //  生成教师的数量
    private static final int TEA_NUM = 350;

    //  每个入学年份生成的班级数量
    private static final int YEAR_CLASS = 120;

    //  生成的课程的数量
    private static final int COURSE_NUM = 60;

    /**
     * 生成一些院系
     */
    public static List<Department> generateDepartments() {
        String[] arr = {"计算机学院", "数学科学学院", "生命科学学院", "经济学院", "法学院",
                "外国语学院", "建筑学院", "航天航空学院", "材料学院", "环境学院", "电信学院", "物理学院", "仪器学院"};
        List<Department> res = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            Department department = new Department();
            department.setDname(arr[i]);
            res.add(department);
        }
        return res;
    }

    /**
     * 生成教师
     */
    public static List<Teacher> generateTeachers() {
        List<Teacher> res = new ArrayList<>(TEA_NUM);
        List<String> familyNames = NameGenerator.getFamilyNames();
        List<String> lastNamesOfMan = NameGenerator.getLastNames(true);
        List<String> lastNamesOfWoman = NameGenerator.getLastNames(false);
        for (int i = 0; i < TEA_NUM; i++) {
            Teacher teacher = new Teacher();
            int nextInt = new Random().nextInt(2);
            if (nextInt == 0) {
                teacher.setName(NameGenerator.getName(familyNames, lastNamesOfWoman));
            } else {
                teacher.setName(NameGenerator.getName(familyNames, lastNamesOfMan));
            }
            teacher.setDepartmentId(new Random().nextInt(DEP_NUM)+1);
            res.add(teacher);
        }
        return res;
    }

    /**
     * 生成班级
     */
    public static List<Class> generateClasses() {
        List<Class> classes = new ArrayList<>();
        for(int i = 0; i <= MAX_FIRST_YEAR-MIN_FIRST_YEAR; i++) {
            for(int j = 0; j < YEAR_CLASS; j++) {
                Class cls = new Class();
                cls.setTid(new Random().nextInt(TEA_NUM)+1);
                cls.setDepartmentId(new Random().nextInt(DEP_NUM)+1);
                classes.add(cls);
            }
        }
        return classes;
    }

    /**
     *  生成课程
     */
    public static List<Course> generateCourses() {
        List<Course> courses = new ArrayList<>();
        List<String> courseNames = getCourseNames();
        for (int i = 0; i < COURSE_NUM; i++) {
            Course course = new Course();
            course.setTid(new Random().nextInt(TEA_NUM)+1);
            course.setCname(courseNames.get(i));
            courses.add(course);
        }
        return courses;
    }

    /**
     * 生成 10w 个学生对象
     */
    public static List<Student> generateStudents() {
        List<Student> students = new ArrayList<>();
        List<String> familyNames = NameGenerator.getFamilyNames();
        List<String> lastNamesOfMan = NameGenerator.getLastNames(true);
        List<String> lastNamesOfWoman = NameGenerator.getLastNames(false);
        for (int i = MIN_FIRST_YEAR; i <= MAX_FIRST_YEAR; i++) {
            int classBase = (i - MIN_FIRST_YEAR) * YEAR_CLASS;
            for (int j = 0; j < YEAR_NUM; j++) {
                Student student = new Student();
                student.setSex(new Random().nextBoolean());
                student.setClassId(classBase + new Random().nextInt(YEAR_CLASS)+1);
                student.setFirstYear(i);
                if (student.getSex()) {
                    student.setName(NameGenerator.getName(familyNames, lastNamesOfMan));
                } else {
                    student.setName(NameGenerator.getName(familyNames, lastNamesOfWoman));
                }
                students.add(student);
            }
        }
        return students;
    }

    /**
     * 从文件中获取课程名
     */
    private static List<String> getCourseNames() {
        List<String> courseNames = new ArrayList<>();
        InputStream in = DataGenerator.class.getClassLoader().getResourceAsStream("课程.txt");
        if (in == null) {
            System.out.println("文件不存在！");
            return courseNames;
        }
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll((char) 12288 + "", " ").trim();
                String[] arr = line.split("\\s+");
                courseNames.add(arr[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return courseNames;
    }
}
