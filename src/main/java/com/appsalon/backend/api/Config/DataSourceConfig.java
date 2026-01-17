package com.appsalon.backend.api.Config;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource(Environment env) {
        String databaseUrl = env.getProperty("DATABASE_URL");
        if (databaseUrl != null && !databaseUrl.isBlank()) {
            try {
                // DATABASE_URL example: postgres://user:pass@host:port/dbname
                URI uri = new URI(databaseUrl);
                String userInfo = uri.getUserInfo();
                String username = null;
                String password = null;
                if (userInfo != null && userInfo.contains(":")) {
                    String[] parts = userInfo.split(":", 2);
                    username = parts[0];
                    password = parts[1];
                }
                String host = uri.getHost();
                int port = uri.getPort();
                String path = uri.getPath(); // includes leading '/'
                String jdbcUrl = String.format("jdbc:postgresql://%s:%d%s", host, port, path);

                HikariDataSource ds = new HikariDataSource();
                ds.setJdbcUrl(jdbcUrl);
                if (username != null) ds.setUsername(username);
                if (password != null) ds.setPassword(password);
                // Recommended settings for many managed Postgres providers
                ds.addDataSourceProperty("sslmode", "require");
                return ds;
            } catch (Exception e) {
                // If parsing fails, fallthrough to default Spring properties
            }
        }

        // No DATABASE_URL: use properties from application.properties / env vars
        String url = env.getProperty("spring.datasource.url");
        String user = env.getProperty("spring.datasource.username");
        String pass = env.getProperty("spring.datasource.password");
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(url)
                .username(user)
                .password(pass)
                .build();
    }
}
