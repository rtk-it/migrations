package ru.rgordeev.migrations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = MigrationsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MigrationsApplicationTests {

    @Test
    void contextLoads() {
    }

}
