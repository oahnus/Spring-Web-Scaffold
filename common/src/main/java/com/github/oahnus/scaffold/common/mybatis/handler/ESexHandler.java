package com.github.oahnus.scaffold.common.mybatis.handler;

import com.github.oahnus.scaffold.common.enums.Sex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by oahnus on 2018/8/21
 * 17:44.
 */
@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(Sex.class)
public class ESexHandler implements TypeHandler<Sex> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Sex parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setInt(i, parameter.getCode());
        }
    }

    @Override
    public Sex getResult(ResultSet rs, String columnName) throws SQLException {
        return Sex.getSex(rs.getInt(columnName));
    }

    @Override
    public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Sex.getSex(rs.getInt(columnIndex));
    }

    @Override
    public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Sex.getSex(cs.getInt(columnIndex));
    }
}
