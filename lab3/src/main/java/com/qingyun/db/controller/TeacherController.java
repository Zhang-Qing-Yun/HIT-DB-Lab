package com.qingyun.db.controller;


import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.base.R;
import com.qingyun.db.bean.Page;
import com.qingyun.db.bean.Teacher;
import com.qingyun.db.service.TeacherService;
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
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Value("${page.limit}")
    private int limit;

    @PostMapping("/getAllTeachers/{current}")
    public R getAllTeachers(@PathVariable int current, @RequestBody(required = false) Teacher queryCondition) {
        Page page = new Page();
        page.setRows(teacherService.getTotalRows(queryCondition));
        page.setCurrent(current);
        page.setLimit(limit);
        page.setPath("/teacher/getAllTeachers/");

        List<Teacher> items = teacherService.getAllTeachers(page.getOffset(), page.getLimit(), queryCondition);
        return R.ok().data("page", page).data("items", items);
    }

    @PostMapping("/insertTeacher")
    public R insertTeacher(@RequestBody Teacher teacher) {
        try {
            teacherService.insertTeacher(teacher);
            return R.ok();
        } catch (ParameterNotRightException e) {
            return R.error().message(e.getMessage());
        }
    }

    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable int id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return R.ok().data("teacher", teacher);
    }
}

