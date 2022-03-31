package com.mealsapi.service;

import com.mealsapi.exception.ResourceNotFoundException;
import com.mealsapi.model.Meal;
import com.mealsapi.wrapper.MealWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    @Override
    public Set<Meal> getMeals() {
        return restTemplate.getForObject(URL, MealWrapper.class)
                .getMeals();
    }

    @Override
    public Meal getMeal(String name) {
        try {
            return restTemplate.getForObject(URL + name, MealWrapper.class)
                    .getMeals()
                    .stream()
                    .findFirst()
                    .get();
        } catch (NullPointerException exception) {
            throw new ResourceNotFoundException(format("Resource named %s not found!", name));
        }
    }
}
