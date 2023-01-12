package com.ssssv.user.controller;

import com.ssssv.user.entity.dto.UserDto;
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
    public Integer addUser(@RequestBody UserReq userReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq, userDto);
        return userService.addUser(userDto);
    }
}
