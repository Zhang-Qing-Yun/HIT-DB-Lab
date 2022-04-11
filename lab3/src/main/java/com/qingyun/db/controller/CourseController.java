package com.qingyun.db.controller;


import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.base.R;
import com.qingyun.db.bean.Course;
import com.qingyun.db.bean.Page;
import com.qingyun.db.service.CourseService;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Value("${page.limit}")
    private int limit;

    @PostMapping("/getAllCourses/{current}")
    public R getAllCourses(@PathVariable Integer current, @RequestBody Course queryCondition) {
        Page page = new Page();
        page.setRows(courseService.getTotalRows(queryCondition));
        page.setCurrent(current);
        page.setLimit(limit);
        page.setPath("/course/getAllCourses/");

        List<Course> items = courseService.getAllCourses(page.getOffset(), limit, queryCondition);
        return R.ok().data("page", page).data("items", items);
    }

    @PostMapping("/insertCourse")
    public R insertCourse(@RequestBody Course course) {
        try {
            courseService.insertCourse(course);
            return R.ok();
        } catch (ParameterNotRightException e) {
            return R.error().message(e.getMessage());
        }
    }
}

