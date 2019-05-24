package com.github.oahnus.scaffold.domain.mapper;

import com.github.oahnus.scaffold.domain.entity.SysPermission;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysPermissionMapper extends Mapper<SysPermission> {
    @Select("select * from sys_permission where id in (select permission_id from sys_role_perm_relation where role_id = #{roleId})")
    List<SysPermission> findByRoleId(@Param("roleId") Long roleId);
}