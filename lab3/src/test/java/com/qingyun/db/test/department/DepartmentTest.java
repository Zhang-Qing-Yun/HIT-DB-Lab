package com.qingyun.db.test.department;

import com.qingyun.db.bean.Department;
import com.qingyun.db.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description：
 * @author: 張青云
 * @create: 2022-04-12 17:02
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getTotalRows() {
        System.out.println(departmentService.getTotalRows(new Department()));
    }
}
