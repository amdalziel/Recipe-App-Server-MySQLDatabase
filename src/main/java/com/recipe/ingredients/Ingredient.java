package com.recipe.ingredients;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Ingredient {

    @Id
    @SequenceGenerator(name = "ingredient_sequence", sequenceName = "ingredient_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "ingredient_sequence")
    private long id;

    private String name;
    private Boolean isCommonAllergen;

    public Ingredient() {

    }

    @JsonCreator
    public Ingredient(@JsonProperty("name") String name) {
        this.name = name;
        this.isCommonAllergen = compareIngredientWithCommonAllergensList();
    }

    public Boolean compareIngredientWithCommonAllergensList(){
        String[] commonAllergenList = {"peanuts", "milk", "eggs", "pecans", "walnuts", "soy", "almonds"};

        Boolean isOnAllergenList = false;

        for (String allergen : commonAllergenList) {
            if (allergen.equalsIgnoreCase(this.name)){
                isOnAllergenList = true;
            }
        }
        return isOnAllergenList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCommonAllergen() {
        return isCommonAllergen;
    }

    public void setCommonAllergen(Boolean commonAllergen) {
        isCommonAllergen = commonAllergen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingredient that = (Ingredient) obj;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
