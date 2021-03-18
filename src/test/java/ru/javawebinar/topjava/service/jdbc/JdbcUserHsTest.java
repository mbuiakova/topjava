package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;


@ActiveProfiles(profiles = {"jdbc", "hsqldb"})
public class JdbcUserHsTest extends UserServiceTest {

}