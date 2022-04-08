package com.qingyun.db.controller;


import com.qingyun.db.base.R;
import com.qingyun.db.bean.Student;
import com.qingyun.db.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 張青云
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudents")
    public R getAllStudents() {
        List<Student> students = studentService.list(null);
        return R.ok().data("students", students);
    }
}

