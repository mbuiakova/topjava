package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"jdbc", "hsqldb"})
public class JdbcMealHsTest extends MealServiceTest {

}