package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;


@ActiveProfiles(profiles = {"datajpa", "hsqldb"})
public class DataJpaUserHsTest extends UserServiceTest {

}