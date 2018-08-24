package top.oahnus.mybatis.provider;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import top.oahnus.mybatis.condition.Condition;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by oahnus on 2018/8/3
 * 11:25.
 */
@Data
public class ConditionSelectProvider {
    private static Map<Class, String> tableFieldsCache = new HashMap<>();
    private static Map<Class, String> tableNameCache = new HashMap<>();

    public String selectListByCondition(Condition condition) {
        Class clazz = condition.getClazz();

        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String whereSql = condition.getSql().replace("where", "");

        final String tableName;
        tableName = getTableName(clazz);

        return new SQL(){{
            SELECT(formatFields(clazz));
            FROM(tableName);
            if (StringUtils.isNotBlank(whereSql)) {
                WHERE(whereSql);
            }
        }}.toString();
    }

    /**
     * 调用时， 要保证类中有包含全部参数的构造函数
     * @param condition
     * @return
     */
    public String selectOneByCondition(Condition condition) {
        Class clazz = condition.getClazz();

        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String whereSql = condition.getSql().replace("where", "");

        final String tableName;
        tableName = getTableName(clazz);

        return new SQL(){{
            SELECT(formatFields(clazz));
            FROM(tableName);
            if (StringUtils.isNotBlank(whereSql)) {
                WHERE(whereSql);
            }
        }}.toString() + "LIMIT 1";
    }

    private String getTableName(Class clazz) {
        String cachedTableName = tableNameCache.get(clazz);
        if (cachedTableName == null) {
            Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
            if (tableAnnotation != null) {
                return tableAnnotation.name();
            } else {
                return hump2Underline(clazz.getSimpleName());
            }
        }
        return cachedTableName;
    }

    private String formatFields(Class clazz) {
        String formattedFields = tableFieldsCache.get(clazz);
        if (formattedFields != null) {
            return formattedFields;
        }
        formattedFields = String.join(", ", Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Transient.class) == null)
                .map(field -> {
                    String fieldName = field.getName();
                    return String.format("%s as `%s`", hump2Underline(fieldName), fieldName);
                }).collect(Collectors.toList()));
        tableFieldsCache.put(clazz, formattedFields);
        return formattedFields;
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
