package top.oahnus.mapper.secondary;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.oahnus.common.mymapper.BaseMapper;
import top.oahnus.domain.secondary.Category;

/**
 * Created by oahnus on 2018/8/2
 * 10:41.
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("SELECT * FROM user WHERE user = #{dd} AND where ")
    public void testSee(@Param("dd") String user);
}
