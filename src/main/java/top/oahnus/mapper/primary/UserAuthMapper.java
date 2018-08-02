package top.oahnus.mapper.primary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oahnus.common.mymapper.BaseMapper;
import top.oahnus.domain.primary.UserAuth;


/**
 * Created by oahnus on 2017/3/13.
 * 22:53
 */
public interface UserAuthMapper extends BaseMapper<UserAuth> {
    UserAuth findFirstByUsername(@Param("username") String username);
}
