package com.recipe.recipes;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @SequenceGenerator(name = "recipe_sequence", sequenceName = "recipe_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "recipe_sequence")
    private long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    private String instructions;
    private int difficulty;
    private boolean containsCommonAllergens;

    public Recipe() {
    }

    public Recipe(String name, List<Ingredient> ingredients, String instructions, int difficulty) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.difficulty = difficulty;
        this.containsCommonAllergens = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isContainsCommonAllergens() {
        return this.containsCommonAllergens;
    }

    public void setContainsCommonAllergens(boolean containsCommonAllergens) {
        this.containsCommonAllergens = containsCommonAllergens;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", difficulty=" + difficulty +
                ", containsCommonAllergens=" + containsCommonAllergens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (containsCommonAllergens != recipe.containsCommonAllergens) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null) return false;
        return instructions != null ? instructions.equals(recipe.instructions) : recipe.instructions == null;
    }


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        result = 31 * result + (containsCommonAllergens ? 1 : 0);
        return result;
    }

}
