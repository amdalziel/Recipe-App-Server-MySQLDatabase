package com.recipe.ingredients;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    public Ingredient findIngredientByName(String ingredientName);

}
