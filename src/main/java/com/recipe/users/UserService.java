package com.recipe.users;

import com.recipe.recipes.Ingredient;
import com.recipe.recipes.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public User addUser(User newUser) {
        List<Ingredient> persistedIngredients = new ArrayList<>();

        for (Ingredient ingredient : newUser.getUserCurrentIngredients()) {
            if (ingredient != null && ingredient.getName() != null && !ingredient.getName().isEmpty()) {
                Ingredient ingredientInDB = ingredientRepository.findIngredientByName(ingredient.getName());

                if (ingredientInDB == null) {
                    ingredientInDB = ingredientRepository.save(ingredient);
                }

                persistedIngredients.add(ingredientInDB);
            }
        }

        newUser.setUserCurrentIngredients(persistedIngredients);
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.findById(id);
    }
}