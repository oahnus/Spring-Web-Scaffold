package com.github.oahnus.scaffold.domain.mapper;

import com.github.oahnus.scaffold.domain.entity.SysRole;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {
    @Select("select * from sys_role where id in (select role_id from sys_auth_role_relation where auth_id = #{authId})")
    List<SysRole> findByAuthId(@Param("authId") Long authId);
}