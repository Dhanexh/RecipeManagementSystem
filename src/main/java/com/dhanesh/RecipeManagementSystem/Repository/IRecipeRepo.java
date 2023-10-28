package com.dhanesh.RecipeManagementSystem.Repository;

import com.dhanesh.RecipeManagementSystem.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeRepo extends JpaRepository<Recipe,Integer> {
}
