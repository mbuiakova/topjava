package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<MealTo> mealsTo = MealsUtil.getMealsTo(2000);
        request.setAttribute("mealsTo", mealsTo);
        String[] tableColumns = {"Date/Time", "Description", "Calories"};
        request.setAttribute("tableColumns", tableColumns);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

        response.sendRedirect("meals.jsp");
    }
}
