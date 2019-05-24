package com.github.oahnus.scaffold.web.service.impl;

import com.github.oahnus.scaffold.common.service.BaseService;
import com.github.oahnus.scaffold.domain.entity.SysRole;
import com.github.oahnus.scaffold.domain.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oahnus on 2019/4/24
 * 15:41.
 */
@Service
public class RoleService extends BaseService<SysRoleMapper, SysRole, Long> {

    public List<SysRole> getRolesByAuthId(Long id) {
        return mapper.findByAuthId(id);
    }
}
