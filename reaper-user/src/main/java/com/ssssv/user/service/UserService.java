package com.ssssv.user.service;

import com.ssssv.entity.PageResult;
import com.ssssv.user.entity.dto.UserDto;
import com.ssssv.user.entity.po.UserPo;

public interface UserService {

    int addUser(UserDto userDto);

    int delete(Long id);

    PageResult<UserPo> getUserPage(UserDto userDto);
}
