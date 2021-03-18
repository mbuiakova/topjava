package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;


@ActiveProfiles(profiles = {"jpa", "postgres"})
public class JpaUserPostgresTest extends UserServiceTest {

}