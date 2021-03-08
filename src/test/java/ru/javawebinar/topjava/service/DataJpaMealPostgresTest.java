package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles(profiles = {"datajpa", "postgres"})
public class DataJpaMealPostgresTest extends MealServiceTest {

}