package com.recipe.users;

import com.recipe.ingredients.Ingredient;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "user_sequence")
    private long id;

    private String firstName;
    private String lastName;

    @ManyToMany
    private List<Ingredient> userCurrentIngredients;

    public User() {

    }

    public long getUserId() {
        return id;
    }

    public void setUserId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public List<Ingredient> getUserCurrentIngredients() {
        return userCurrentIngredients;
    }

    public void setUserCurrentIngredients(List<Ingredient> userCurrentIngredients) {
        this.userCurrentIngredients = userCurrentIngredients;
    }

    @Override
    public String toString() {
        return "User " + this.id + ": " + this.firstName + " " + this.lastName + this.userCurrentIngredients;
    }
}
