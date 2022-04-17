package ru.otus.library.actuator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    @Override
    public Health health() {
        try (var connection = dataSource.getConnection()) {
            return Health.up().withDetail("message", "DB available!").build();
        } catch (SQLException e) {
            log.warn("DB not available");
            return Health.down().withDetail("message", "DB not available >>> ".concat(e.getMessage())).build();

        }
    }
}
