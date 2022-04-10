package com.qingyun.db.controller;

import com.qingyun.db.base.R;
import org.springframework.web.bind.annotation.*;

/**
 * @description： 前端登录校验
 * @author: 張青云
 * @create: 2022-04-09 15:52
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {
    @PostMapping("/login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R info() {
        return R.ok()
                .data("roles", "admin")
                .data("name", "qingyun")
                .data("avatar", "https://test1-qingyun.oss-cn-beijing.aliyuncs.com/%E5%A4%B4%E5%83%8F.jpg");
    }
}
