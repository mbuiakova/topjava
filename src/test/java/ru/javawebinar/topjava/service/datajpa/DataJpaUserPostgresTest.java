package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;


@ActiveProfiles(profiles = {"datajpa", "postgres"})
public class DataJpaUserPostgresTest extends UserServiceTest {

}