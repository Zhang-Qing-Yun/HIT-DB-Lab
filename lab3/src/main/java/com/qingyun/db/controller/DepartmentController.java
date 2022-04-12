package com.qingyun.db.controller;


import com.qingyun.db.base.R;
import com.qingyun.db.bean.Department;
import com.qingyun.db.bean.Page;
import com.qingyun.db.service.DepartmentService;
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
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Value("${page.limit}")
    private int limit;

    @PostMapping("/getAllDepartments/{current}")
    public R getAllDepartments(@PathVariable int current, @RequestBody(required = false) Department queryCondition) {
        Page page = new Page();
        page.setRows(departmentService.getTotalRows(queryCondition));
        page.setCurrent(current);
        page.setLimit(limit);
        page.setPath("/department/getAllDepartments/");

        List<Department> items = departmentService.getAllDepartments(page.getOffset(), limit, queryCondition);
        return R.ok().data("items", items).data("page", page);
    }
}

