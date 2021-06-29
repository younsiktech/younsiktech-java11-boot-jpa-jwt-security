package com.tech.younsik.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@Configuration
public class RoutingDataSourceConfig {
    
    @Value("${db.url.writer}")
    private String DB_WRITER_URL;
    
    @Value("${db.url.reader}")
    private String DB_READER_URL;
    
    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource writeDataSource(DataSourceProperties properties) {
        properties.setUrl(DB_WRITER_URL);
        return properties.initializeDataSourceBuilder().build();
    }
    
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource readDataSource(DataSourceProperties properties) {
        properties.setUrl(DB_READER_URL);
        return properties.initializeDataSourceBuilder().build();
    }
    
    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(
        @Qualifier("writeDataSource") DataSource writeDataSource,
        @Qualifier("readDataSource") DataSource readDataSource) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("write", writeDataSource);
        dataSourceMap.put("read", readDataSource);
        
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        
        return routingDataSource;
    }
    
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
