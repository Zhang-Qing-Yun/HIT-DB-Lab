package com.qingyun.db.controller;


import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.base.R;
import com.qingyun.db.bean.Page;
import com.qingyun.db.bean.Student;
import com.qingyun.db.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Value("${page.limit}")
    private int limit;

    /**
     * 分页查询所有的学生，可以添加查询条件
     */
    @PostMapping("/getAllStudents/{current}")
    public R getAllStudents(@PathVariable int current, @RequestBody(required = false) Student queryCondition) {
        //  封装分页对象
        Page page = new Page();
        page.setRows(studentService.getTotalRows(queryCondition));
        page.setCurrent(current);
        page.setLimit(limit);
        page.setPath("/student/getAllStudents/");
        List<Student> items = studentService.getAllStudents(page.getOffset(), limit, queryCondition);
        return R.ok()
                .data("page", page)
                .data("items", items);
    }

    @PostMapping("/insertStudent")
    public R insertStudent(@RequestBody Student student) {
        try {
            studentService.insertStudent(student);
            return R.ok();
        } catch (ParameterNotRightException e) {
            return R.error().message(e.getMessage());
        }
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public R deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return R.ok();
    }

    @GetMapping("/getStudentById/{id}")
    public R getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return R.ok().data("student", student);
    }

    @PostMapping("updateStudentById/{id}")
    public R updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        try {
            studentService.updateStudentById(id, student);
            return R.ok();
        } catch (ParameterNotRightException e) {
            return R.error().message(e.getMessage());
        }
    }
}

