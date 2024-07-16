package com.recipe.recipes;

import com.recipe.ingredients.Ingredient;
import com.recipe.ingredients.IngredientRepository;
import com.recipe.users.User;
import com.recipe.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeManagerService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientRepository ingredientRepository;


    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    public Recipe addRecipe(Recipe recipe) {
        List<Ingredient> persistedIngredients = new ArrayList<>();

        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient != null && ingredient.getName() != null && !ingredient.getName().isEmpty()) {
                Ingredient ingredientInDB = ingredientRepository.findIngredientByName(ingredient.getName());

                if (ingredientInDB == null) {
                    ingredientInDB = ingredientRepository.save(ingredient);
                }

                persistedIngredients.add(ingredientInDB);
            }
        }

        recipe.setIngredients(persistedIngredients);

        for (Ingredient ingredient : persistedIngredients) {
            if (ingredient.getCommonAllergen() == true) {
                recipe.setContainsCommonAllergens(true);
            }
        }
        return recipeRepository.save(recipe);
    }



    public String deleteRecipe(String name) {
        Recipe recipeToDelete = getSingleRecipeByName(name);
        recipeRepository.delete(recipeToDelete);
        return "Recipe removed from list";
    }


    public List<Recipe> searchRecipeManagerByUserIngredients(long userId) {

        User selectedUser = userRepository.findById(userId);
        List<Ingredient> userIngredients = selectedUser.getUserCurrentIngredients();

        List<String> ingredientNames = userIngredients.stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());

        List<Recipe> allRecipes = (List<Recipe>) recipeRepository.findAll();

        List<Recipe> matchingRecipes = allRecipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .allMatch(recipeIngredient -> ingredientNames.contains(recipeIngredient.getName())))
                .collect(Collectors.toList());


        return matchingRecipes;

    }


    public Recipe getSingleRecipeByName(String recipeName) {
        return recipeRepository.findByName(recipeName);
    }

    public List<Recipe> searchRecipeManagerForNoCommonAllergens() {
            return recipeRepository.findByContainsCommonAllergensFalse();
    }
}

