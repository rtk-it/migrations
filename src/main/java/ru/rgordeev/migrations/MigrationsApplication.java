package ru.rgordeev.migrations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EntityScan(basePackages = {"ru.rgordeev.migrations.model"})
@EnableJpaRepositories(basePackages = {"ru.rgordeev.migrations.repository"})
@SpringBootApplication
public class MigrationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MigrationsApplication.class, args);
    }

}
