package com.mealsapi.controller;

import com.mealsapi.model.Meal;
import com.mealsapi.service.MealService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<Set<MealDTO>> getMeals() {
        return ResponseEntity.ok(mealService.getMeals()
                .stream()
                .map(this::toMealDTO)
                .collect(toSet()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<MealDTO> getMeal(@PathVariable String name) {
       return ResponseEntity.ok(toMealDTO(mealService.getMeal(name)));
    }

    private MealDTO toMealDTO(Meal meal) {
        return MealDTO.builder()
                .name(meal.getName())
                .category(meal.getCategory())
                .area(meal.getArea())
                .cookingInstructions(Stream.of(meal.getCookingInstructions()
                                .split("\r\n"))
                        .collect(toList()))
                .thumbnail(meal.getThumbnail())
                .build();
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class MealDTO{
        private String name;
        private String category;
        private String area;
        private List<String> cookingInstructions;
        private String thumbnail;
    }
}