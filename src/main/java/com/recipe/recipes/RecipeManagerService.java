package com.recipe.recipes;

import com.recipe.users.User;
import com.recipe.users.UserRepository;
import com.recipe.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

        return recipeRepository.findRecipesByIngredientNames(ingredientNames);
    }


    public Recipe getSingleRecipeByName(String recipeName) {
        return recipeRepository.findByName(recipeName);
    }

//    public List<Recipe> searchRecipeManagerForNoCommonAllergens() {
//        return recipeRepository.findRecipesWithNoCommonAllergens();
//    }
}

