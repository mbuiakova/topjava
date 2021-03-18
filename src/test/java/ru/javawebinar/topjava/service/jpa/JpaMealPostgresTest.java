package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;


@ActiveProfiles(profiles = {"jpa", "postgres"})
public class JpaMealPostgresTest extends MealServiceTest {

}