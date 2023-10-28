package com.dhanesh.RecipeManagementSystem.Service;

import com.dhanesh.RecipeManagementSystem.Model.Recipe;
import com.dhanesh.RecipeManagementSystem.Repository.IRecipeRepo;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    IRecipeRepo recipeRepo;

    public void postRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
    }

    public Recipe getRecipeById(Integer recipeId) {
        return recipeRepo.findById(recipeId).orElseThrow();
    }

    public void removeById(Integer recipeId) {
        recipeRepo.findById(recipeId);
    }

    public List<Recipe> getAllRecipe() {
        return recipeRepo.findAll();
    }
}
