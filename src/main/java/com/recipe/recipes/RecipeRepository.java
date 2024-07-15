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
    List<Recipe> findRecipesByIngredientNames(@Param("ingredientNames") List<String> ingredientNames);

//    @Query("SELECT r FROM Recipe r JOIN r.ingredients i GROUP BY r.id HAVING SUM(CASE WHEN i.commonAllergen = true THEN 1 ELSE 0 END) = 0")
//    List<Recipe> findRecipesWithNoCommonAllergens();


}
