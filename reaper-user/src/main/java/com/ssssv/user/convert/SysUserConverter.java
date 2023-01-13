package com.ssssv.user.convert;

import com.ssssv.user.entity.po.SysUser;
import com.ssssv.user.entity.req.SysUserReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    SysUser convertReq2Sys(SysUserReq sysUserReq);
}
