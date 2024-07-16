package com.recipe.recipes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.recipe.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.users.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class RecipeManagerController {

    @Autowired
    private RecipeManagerService recipeManagerService;

    @GetMapping("recipe")
    public String getHomeMessage() {
        return "Recipe Application";
    }


    @GetMapping("recipe/{recipeName}")
    public Recipe getRecipeByName(@PathVariable String recipeName) {
        return recipeManagerService.getSingleRecipeByName(recipeName);
    }


    @GetMapping("recipes")
    public List<Recipe> getAllRecipes() {
        return recipeManagerService.getAllRecipes();
    }


    @GetMapping("recipe/userMatches")
    public List<Recipe> searchForRecipesByUserIngredients(@RequestParam long userId) throws JsonProcessingException {

        return recipeManagerService.searchRecipeManagerByUserIngredients(userId);
    }

    @GetMapping("recipe/noCommonAllergens")
    public List<Recipe> searchForRecipesWithoutCommonAllergens() {

        return recipeManagerService.searchRecipeManagerForNoCommonAllergens();
    }


    @PostMapping("/newRecipe")
    public Recipe addRecipe(@RequestBody Recipe newRecipe) {
        return recipeManagerService.addRecipe(newRecipe);
    }


    @DeleteMapping("recipe/{recipeName}")
    public String deleteRecipe(@PathVariable String recipeName) {
        return recipeManagerService.deleteRecipe(recipeName);
    }
}