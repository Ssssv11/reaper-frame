package com.ssssv.user.controller;

import com.ssssv.bean.Result;
import com.ssssv.user.entity.dto.UserDto;
import com.ssssv.user.entity.req.UserListReq;
import com.ssssv.user.entity.req.UserReq;
import com.ssssv.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result addUser(@RequestBody UserReq userReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq, userDto);
        return Result.ok(userService.addUser(userDto));
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        return Result.ok(userService.delete(id));
    }

    @GetMapping()
    public Result getUserInfoPage(@RequestBody UserListReq userListReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userListReq, userDto);
        return Result.ok(userService.getUserPage(userDto));
    }
}
