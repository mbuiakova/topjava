package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;


@ActiveProfiles(profiles = {"jpa", "hsqldb"})
public class JpaUserHsTest extends UserServiceTest {

}