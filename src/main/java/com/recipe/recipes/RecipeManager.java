package com.recipe.recipes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.util.ArrayList;

@Entity
public class RecipeManager {

    @Id
    @SequenceGenerator(name = "recipeManager_sequence", sequenceName = "recipeManager_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "recipeManager_sequence")
    long id;

    private String recipeManagerName;
    private String recipesType;
    private ArrayList<Recipe> recipeArrayList;



    public ArrayList<Recipe> getRecipeArrayList() {
        return recipeArrayList;
    }

    public void setRecipeArrayList(ArrayList<Recipe> recipeArrayList) {
        this.recipeArrayList = recipeArrayList;
    }

    public String getRecipesType() {
        return recipesType;
    }

    public void setRecipesType(String recipesType) {
        this.recipesType = recipesType;
    }

    public String getRecipeManagerName() {
        return recipeManagerName;
    }

    public void setRecipeManagerName(String recipeManagerName) {
        this.recipeManagerName = recipeManagerName;
    }


}
