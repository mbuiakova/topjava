package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if(!meal.isNew()){
            Meal current = get(meal.id(), userId);
            if(current == null){
                return null;
            }else {
                meal.setUser(current.getUser());
            }
        }else {
            User user = new User();
            user.setId(userId);
            meal.setUser(user);
        }
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal m = get(id, userId);
        if(m == null){
            throw new NotFoundException("the food is wrong");
        }
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Optional<Meal> optional = crudRepository.findById(id);
        if (optional.isPresent() && optional.get().getUser().getId() != userId) return null;
        return optional.orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAllByUserId(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.findAllByDateBetween(startDateTime, endDateTime, userId);
    }
}
