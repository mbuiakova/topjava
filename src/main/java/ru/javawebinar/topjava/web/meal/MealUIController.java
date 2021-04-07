package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.FilterUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/profile/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealUIController extends AbstractMealController {

    private Map<String, FilterUtil> filters = new HashMap<>();

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @GetMapping
    public List<MealTo> getAll(HttpServletRequest request) {
        String sessionId = request.getRequestedSessionId();
        if(filters.containsKey(sessionId)){
            FilterUtil fu = filters.get(sessionId);
            return super.getBetween(fu.getStartDate(), fu.getStartTime(), fu.getEndDate(), fu.getEndTime());
        }
        return super.getAll();
    }

    @Override
    @PostMapping
    public Meal create(@RequestBody Meal meal) {
        return super.create(meal);
    }

    @Override
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(Meal meal, int id) {
        super.update(meal, id);
    }

    //    @Override
//    @GetMapping("/filter")
//    public List<MealTo> getBetween(@RequestParam @Nullable LocalDate startDate,
//                                   @RequestParam @Nullable LocalTime startTime,
//                                   @RequestParam @Nullable LocalDate endDate,
//                                   @RequestParam @Nullable LocalTime endTime) {
//        return super.getBetween(startDate, startTime, endDate, endTime);
//    }
    @GetMapping("/filter")
    public void filter(HttpServletRequest request, @RequestParam @Nullable LocalDate startDate,
                       @RequestParam @Nullable LocalTime startTime,
                       @RequestParam @Nullable LocalDate endDate,
                       @RequestParam @Nullable LocalTime endTime) {
        String sessionId = request.getRequestedSessionId();
        FilterUtil filterUtil = new FilterUtil(startDate, startTime, endDate, endTime);
        filters.put(sessionId, filterUtil);
    }
}
