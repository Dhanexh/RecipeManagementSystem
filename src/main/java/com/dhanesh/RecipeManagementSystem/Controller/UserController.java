package com.dhanesh.RecipeManagementSystem.Controller;

import com.dhanesh.RecipeManagementSystem.Model.Recipe;
import com.dhanesh.RecipeManagementSystem.Model.User;
import com.dhanesh.RecipeManagementSystem.Service.AuthenticationService;
import com.dhanesh.RecipeManagementSystem.Service.RecipeService;
import com.dhanesh.RecipeManagementSystem.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    RecipeService recipeService;

    @PostMapping("/signUp")
    public String signUp(@Valid @RequestBody User newUser){
        return userService.signUp(newUser);
    }

    @PostMapping("/signIn/{email}/{password}")
    public String signIn(@Valid @PathVariable String email, @PathVariable String password) throws NoSuchAlgorithmException {
        return userService.signIn(email,password);
    }

    @DeleteMapping("/signOut/{email}/{tokenValue}")
    public String signOut(@Valid @PathVariable String email, @PathVariable String tokenValue){
        return userService.signOut(email,tokenValue);
    }

    @PostMapping("/postRecipe")
    public String postRecipe(@RequestParam String email,@RequestParam String tokenValue, @RequestBody Recipe recipe){
        return userService.postRecipe(email,tokenValue,recipe);
    }

    @GetMapping("/recipe")
    public List<Recipe> getAllRecipe(){
        return userService.getAllRecipe();
    }
    
    @DeleteMapping("/recipe/{recipeId}")
    public String deleteRecipe(@RequestParam String email,@RequestParam String tokenValue,@PathVariable Integer recipeId){
        return userService.deleteRecipe(email,tokenValue,recipeId);
    }

    @PostMapping("/comment/{recipeId}")
    public String addComment(@RequestParam String email,@RequestParam String tokenValue,
                             @PathVariable Integer recipeId, @RequestBody String commentBody){
        return userService.addComment(email,tokenValue,recipeId,commentBody);
    }

    @DeleteMapping("/comment/{commentId}")
    public String removeComment(@RequestParam String email,@RequestParam String tokenValue, @PathVariable Integer commentId){
        return userService.removeComment(email,tokenValue,commentId);
    }




}
