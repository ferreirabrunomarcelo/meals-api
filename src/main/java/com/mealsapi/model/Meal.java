package com.mealsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Meal {
    @JsonProperty("strMeal")
    private String name;
    @JsonProperty("strCategory")
    private String category;
    @JsonProperty("strArea")
    private String area;
    @JsonProperty("strInstructions")
    private String cookingInstructions;
    @JsonProperty("strMealThumb")
    private String thumbnail;
}
