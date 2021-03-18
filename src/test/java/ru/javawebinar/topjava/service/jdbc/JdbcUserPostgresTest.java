package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;


@ActiveProfiles(profiles = {"jdbc", "postgres"})
public class JdbcUserPostgresTest extends UserServiceTest {

}