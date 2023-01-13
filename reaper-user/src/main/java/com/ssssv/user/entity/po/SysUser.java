package com.ssssv.user.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUser)实体类
 *
 * @author Ssssv
 * @since 2023-01-13 15:26:13
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -44763338296240268L;

    private Long id;
    
    private String name;
    
    private Integer age;
    
    private String createBy;
    
    private Date createTime;
    
    private String updateBy;
    
    private Date updateTime;
    
    private Integer deleteFlag;
    
    private Integer version;

}

