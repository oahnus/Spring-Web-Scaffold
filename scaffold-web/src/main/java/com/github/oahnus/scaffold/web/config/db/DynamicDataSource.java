package com.github.oahnus.scaffold.web.config.db;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by oahnus on 2019/10/22
 * 21:53.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultDataSource, Map<Object, Object> dataSourceMap) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(dataSourceMap);
//        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static String getDataSource() {
        return HOLDER.get();
    }

    public static void setDataSource(String dataSource) {
        HOLDER.set(dataSource);
    }

    public static void clearDataSource() {
        HOLDER.remove();
    }
}
