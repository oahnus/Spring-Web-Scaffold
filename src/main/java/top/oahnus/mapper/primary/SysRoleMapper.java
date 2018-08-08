package top.oahnus.mapper.primary;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.oahnus.common.mymapper.BaseMapper;
import top.oahnus.domain.primary.SysRole;


/**
 * Created by oahnus on 2017/3/13.
 * 22:53
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    SysRole findById(@Param("id") Long id);
}
