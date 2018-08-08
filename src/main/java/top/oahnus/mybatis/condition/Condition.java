package top.oahnus.mybatis.condition;

import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by oahnus on 2018/8/3
 * 11:19.
 */
@Data
public class Condition<T> {

    private Class clazz;

    private StringBuilder sql = new StringBuilder();

    private Condition() {}

    public static Condition create(Class clazz) {
        Condition condition = new Condition();
        condition.setClazz(clazz);
        return condition;
    }

    public static <N> Condition create() {
        Condition<N> condition = new Condition<>();
        return condition;
    }

    public String getSql() {
        return this.sql.toString();
    }

    public Condition eq(String colName, String val) {
        this.sql.append(colName).append(" = ").append(val);
        return this;
    }

    public Condition notEq(String colName, String val) {
        this.sql.append(colName).append(" <> ").append(val);
        return this;
    }

    public Condition isNull(String colName) {
        this.sql.append(colName).append(" IS NULL ");
        return this;
    }

    public Condition isNotNull(String colName) {
        this.sql.append(colName).append(" IS NOT NULL ");
        return this;
    }

    public Condition and() {
        this.sql.append(" AND ");
        return this;
    }

    public Condition or() {
        this.sql.append(" OR ");
        return this;
    }

    public Condition gt(String colName, String val) {
        this.sql.append(colName).append(" > ").append(val);
        return this;
    }

    public Condition ge(String colName, String val) {
        this.sql.append(colName).append(" >= ").append(val);
        return this;
    }

    public Condition le(String colName, String val) {
        this.sql.append(colName).append(" <= ").append(val);
        return this;
    }

    public Condition lt(String colName, String val) {
        this.sql.append(colName).append(" < ").append(val);
        return this;
    }

    public Condition like(String colName, String val) {
        this.sql.append(colName).append(" LIKE ").append(val);
        return this;
    }

    public Condition notLike(String colName, String val) {
        this.sql.append(" NOT ");
        return this.like(colName, val);
    }

    public Condition in(String colName, Collection<Object> collection) {
        Collection<String> collect = collection.stream().map(Object::toString).collect(Collectors.toList());
        this.sql.append(colName).append(" IN ( ").append(String.join(",", collect)).append(" ) ");
        return this;
    }

    public Condition notIn(String colName, Collection<Object> collection) {
        this.sql.append(" NOT ");
        return this.in(colName, collection);
    }
}
