package top.oahnus.mybatis.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import top.oahnus.common.enums.ESex;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by oahnus on 2018/8/21
 * 17:44.
 */
@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(ESex.class)
public class ESexHandler implements TypeHandler<ESex> {
    @Override
    public void setParameter(PreparedStatement ps, int i, ESex parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setInt(i, parameter.getCode());
        }
    }

    @Override
    public ESex getResult(ResultSet rs, String columnName) throws SQLException {
        return ESex.getESex(rs.getInt(columnName));
    }

    @Override
    public ESex getResult(ResultSet rs, int columnIndex) throws SQLException {
        return ESex.getESex(rs.getInt(columnIndex));
    }

    @Override
    public ESex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return ESex.getESex(cs.getInt(columnIndex));
    }
}
