package top.oahnus.mybatis.provider;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import top.oahnus.mybatis.condition.Condition;

import javax.persistence.Table;

/**
 * Created by oahnus on 2018/8/3
 * 11:25.
 */
@Data
public class ConditionSelectProvider {
    public String selectListByCondition(Condition condition) {
        Class clazz = condition.getClazz();
        System.out.println(clazz.getCanonicalName());

        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String whereSql = condition.getSql().replace("where", "");

        final String tableName;
        if (tableAnnotation != null) {
            tableName = tableAnnotation.name();
        } else {
            tableName = hump2Underline(clazz.getSimpleName());
        }

        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            if (StringUtils.isNotBlank(whereSql)) {
                WHERE(whereSql);
            }
        }}.toString();
    }

    public String selectOneByCondition(Condition condition) {
        Class clazz = condition.getClazz();
        System.out.println(clazz.getCanonicalName());

        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String whereSql = condition.getSql().replace("where", "");

        final String tableName;
        if (tableAnnotation != null) {
            tableName = tableAnnotation.name();
        } else {
            tableName = hump2Underline(clazz.getSimpleName());
        }

        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            if (StringUtils.isNotBlank(whereSql)) {
                WHERE(whereSql);
            }
        }}.toString() + "LIMIT 1";
    }

    private String hump2Underline(String className) {
        StringBuilder underline = new StringBuilder();
        char[] letters = className.toCharArray();
        for (char letter: letters) {
            if (Character.isUpperCase(letter)) {
                underline.append("_").append(Character.toLowerCase(letter));
            } else {
                underline.append(letter);
            }
        }
        return underline.toString();
    }
}
