package top.oahnus.common.mymapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import top.oahnus.mybatis.condition.Condition;
import top.oahnus.mybatis.provider.ConditionSelectProvider;

import java.util.List;

/**
 * Created by oahnus on 2018/5/9
 * 16:02.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
    @SelectProvider(type = ConditionSelectProvider.class, method = "selectListByCondition")
    List<T> selectListByCondition(Condition condition);

    @SelectProvider(type = ConditionSelectProvider.class, method = "selectOneByCondition")
    T selectOneByCondition(Condition condition);
}
