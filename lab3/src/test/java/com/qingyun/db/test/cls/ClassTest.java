package com.qingyun.db.test.cls;

import com.qingyun.db.service.ClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description：
 * @author: 張青云
 * @create: 2022-04-11 23:28
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClassTest {
    @Autowired
    private ClassService classService;

    @Test
    public void getClassByIdTest() {
        System.out.println(classService.getClassById(2500));
    }
}
