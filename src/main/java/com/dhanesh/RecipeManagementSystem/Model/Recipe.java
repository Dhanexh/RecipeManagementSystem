package com.dhanesh.RecipeManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    private String recipeName;
    private String ingredients;
    private String instructions;

    private LocalDateTime recipeCreationTime;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_owner")
    private User recipeOwner;

}
