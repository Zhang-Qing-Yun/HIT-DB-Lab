package com.qingyun.db.controller;


import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.base.R;
import com.qingyun.db.bean.Class;
import com.qingyun.db.bean.Page;
import com.qingyun.db.service.ClassService;
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
@RequestMapping("/class")
@CrossOrigin
public class ClassController {
    @Autowired
    private ClassService classService;

    @Value("${page.limit}")
    private int limit;

    @PostMapping("/getAllClasses/{current}")
    public R getAllClasses(@PathVariable Integer current, @RequestBody(required = false) Class queryCondition) {
        Page page = new Page();
        page.setRows(classService.getTotalRows(queryCondition));
        page.setCurrent(current);
        page.setLimit(limit);
        page.setPath("/class/getAllClasses/");

        List<Class> items = classService.getAllClasses(page.getOffset(), limit, queryCondition);
        return R.ok().data("page", page).data("items", items);
    }

    @PostMapping("/insertClass")
    public R insertClass(@RequestBody Class c) {
        try {
            classService.insertClass(c);
            return R.ok();
        } catch (ParameterNotRightException e) {
            return R.error().message(e.getMessage());
        }
    }
}

