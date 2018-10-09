package com.github.oahnus.scaffold.common.base_mapper;

import com.github.oahnus.scaffold.common.mybatis.condition.Condition;
import com.github.oahnus.scaffold.common.mybatis.provider.ConditionSelectProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

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
