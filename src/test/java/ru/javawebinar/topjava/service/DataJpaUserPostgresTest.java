package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles(profiles = {"datajpa", "postgres"})
public class DataJpaUserPostgresTest extends UserServiceTest {

}