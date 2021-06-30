package com.tech.younsik;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Slf4j
@EntityScan(
    basePackageClasses = {Jsr310JpaConverters.class},
    basePackages = {"com.tech.younsik.entity"})
@EnableJpaRepositories(
    basePackages = {"com.tech.younsik.repository"})
@EnableJpaAuditing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MainApplication {
    
    @PostConstruct
    void started() {
        log.info("DEFAULT TIME ZONE : UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
