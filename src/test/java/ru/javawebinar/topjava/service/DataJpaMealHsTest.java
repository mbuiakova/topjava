package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"datajpa", "hsqldb"})
public class DataJpaMealHsTest extends MealServiceTest {

}