package com.ssssv.user.service.impl;

import com.ssssv.user.entity.dto.UserDto;
import com.ssssv.user.entity.po.UserPo;
import com.ssssv.user.mapper.UserMapper;
import com.ssssv.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(UserDto userDto) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userDto, userPo);
        return userMapper.insert(userPo);
    }
}
