package com.mealsapi.wrapper;

import com.mealsapi.model.Meal;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MealWrapper {
    private Set<Meal> meals;
}
