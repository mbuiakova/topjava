package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(profiles = {"jpa", "hsqldb"})
public class JpaMealHsTest extends MealServiceTest {

}