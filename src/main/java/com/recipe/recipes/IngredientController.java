package com.recipe.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping("/ingredient/{ingredientName}")
    public Ingredient getIngredientByName(@PathVariable String ingredientName) {
        return ingredientService.searchIngredientByName(ingredientName);
    }
}
