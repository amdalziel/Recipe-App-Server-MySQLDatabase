package com.recipe.recipes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    public Recipe findByName(String name);

    @Query("SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.name IN :ingredientNames")
    public List<Recipe> findRecipesByIngredientNames(@Param("ingredientNames") List<String> ingredientNames);

    public List<Recipe> findByContainsCommonAllergensFalse();


}
