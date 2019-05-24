package com.github.oahnus.scaffold.web.service.impl;

import com.github.oahnus.scaffold.common.service.BaseService;
import com.github.oahnus.scaffold.domain.entity.SysPermission;
import com.github.oahnus.scaffold.domain.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oahnus on 2019/4/24
 * 15:42.
 */
@Service
public class PermissionService extends BaseService<SysPermissionMapper, SysPermission, Long> {

    public List<SysPermission> getPermByRoleIds(Long id) {
        return mapper.findByRoleId(id);
    }
}
