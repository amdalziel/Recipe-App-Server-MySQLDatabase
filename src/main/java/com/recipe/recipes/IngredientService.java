package com.recipe.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Ingredient searchIngredientByName(String ingredientName) {
        return ingredientRepository.findIngredientByName(ingredientName);


    }
}
