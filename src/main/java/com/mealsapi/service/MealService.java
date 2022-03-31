package com.mealsapi.service;

import com.mealsapi.model.Meal;

import java.util.Set;

public interface MealService {
    Set<Meal> getMeals();
    Meal getMeal(String name);
}
