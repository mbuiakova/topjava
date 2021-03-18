package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(profiles = {"jdbc", "hsqldb"})
public class JdbcMealHsTest extends MealServiceTest {

}