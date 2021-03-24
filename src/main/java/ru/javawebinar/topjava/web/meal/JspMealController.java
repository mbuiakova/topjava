package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController extends AbstractMealController {

    @Autowired
    public JspMealController(MealService mealService) {
        super.service = mealService;
    }

    @GetMapping("/createOrUpdateMeal")
    public String createOrUpdateMeal(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("id");
        Meal meal = null;

        if (idString == null) {
            meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        } else {
            meal = super.get(Integer.parseInt(idString));
        }

        model.addAttribute("meal", meal);
        return "/mealForm";
    }

    @GetMapping("/deleteMeal")
    public String deleteMeal(@RequestParam("id") int id) {
        super.delete(id);
        return "redirect:meals";
    }

    @GetMapping("/filterMeal")
    public String filterMeal(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        List<MealTo> meals = super.getBetween(startDate, startTime, endDate, endTime);
        model.addAttribute("meals", meals);
        return "/meals";
    }

    @GetMapping("/meals")
    public String get(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        List<MealTo> meals = super.getAll();
        model.addAttribute("meals", meals);
        return "/meals";
    }

    @PostMapping("/meals")
    public String post(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            super.update(meal, Integer.parseInt(request.getParameter("id")));
        } else {
            super.create(meal);
        }
        return "redirect:meals";

    }


}

