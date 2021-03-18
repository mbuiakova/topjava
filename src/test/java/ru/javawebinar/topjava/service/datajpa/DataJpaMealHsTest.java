package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(profiles = {"datajpa", "hsqldb"})
public class DataJpaMealHsTest extends MealServiceTest {

}