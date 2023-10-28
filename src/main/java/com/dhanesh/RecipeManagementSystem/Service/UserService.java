package com.dhanesh.RecipeManagementSystem.Service;

import com.dhanesh.RecipeManagementSystem.Model.AuthenticationToken;
import com.dhanesh.RecipeManagementSystem.Model.Comment;
import com.dhanesh.RecipeManagementSystem.Model.Recipe;
import com.dhanesh.RecipeManagementSystem.Model.User;
import com.dhanesh.RecipeManagementSystem.Repository.IUserRepo;
import com.dhanesh.RecipeManagementSystem.Service.HashingUtility.PasswordEncryptor;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;

    public String signUp(User newUser) {

        String newEmail = newUser.getUserEmail();
        User existingEmail = userRepo.findFirstByUserEmail(newEmail);

        if(existingEmail != null){
            return "User already exist";
        }

        String password = newUser.getUserPassword();

        try{
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            newUser.setUserPassword(encryptedPassword);

            userRepo.save(newUser);
            return "User Registered";

        } catch (NoSuchAlgorithmException e) {
            return "Internal Server Issue, Try Again !!";
        }

    }

    public String signIn(String email, String password) throws NoSuchAlgorithmException {
        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null){
            return "Email not registered !!";
        }

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if (existingUser.getUserPassword().equals(encryptedPassword)) {
                AuthenticationToken token = new AuthenticationToken(existingUser);
                authenticationService.createToken(token);

            }else{
                return "Password Incorrect";
            }

        }catch (NoSuchAlgorithmException e){
            return "Internal Server Issue, Try Again !!";
        }
        return "Login Successful";
    }

    public String signOut(String email, String tokenValue) {

        if(authenticationService.authenticate(email,tokenValue)){
            authenticationService.deleteToken(tokenValue);
            return "SignOut Successful";
        }else {
            return "Unauthenticated access !!";
        }
    }

    public String postRecipe(String email, String tokenValue, Recipe recipe) {

        if(authenticationService.authenticate(email,tokenValue)){

            User existingUser = userRepo.findFirstByUserEmail(email);
            recipe.setRecipeOwner(existingUser);

            recipeService.postRecipe(recipe);
            return "Recipe posted !!";

        }else{
            return "Unauthenticated access !!";
        }
    }

    public String deleteRecipe(String email, String tokenValue, Integer recipeId) {

        if(authenticationService.authenticate(email, tokenValue)){

            Recipe existingRecipe = recipeService.getRecipeById(recipeId);
            String recipeOwnerEmail = existingRecipe.getRecipeOwner().getUserEmail();

            if(email.equals(recipeOwnerEmail)){
                recipeService.removeById(recipeId);
                return "Recipe removed";
            }else{
                return "Unauthenticated access !!";
            }

        }else{
            return "Unauthenticated access !!";
        }
    }


    public String addComment(String email, String tokenValue, Integer recipeId, String commentBody) {

        if(authenticationService.authenticate(email,tokenValue)){

            Recipe recipeToBeCommented = recipeService.getRecipeById(recipeId);
            User commenter = userRepo.findFirstByUserEmail(email);

            Comment newComment = new Comment(null,commentBody,LocalDateTime.now(),
                                                recipeToBeCommented,commenter);

            commentService.addComment(newComment);
            return commenter.getUserName() +" commented on "+ recipeId;

        } else{
            return "Unauthenticated access !!";
        }

    }

    public String removeComment(String email, String tokenValue, Integer commentId) {

        if(authenticationService.authenticate(email,tokenValue)){

            Comment comment = commentService.findCommentById(commentId);
            Recipe commentedRecipe = comment.getRecipe();

            if(authorizeCommentRemover(email,commentedRecipe,comment)){
                commentService.removeCommentById(commentId);
                return "comment deleted";

            }else{
                return "unauthorized access !!";
            }

        }
        else{
            return "unauthorized access !!";
        }
    }

    private boolean authorizeCommentRemover(String email, Recipe commentedRecipe, Comment comment) {

        User remover = userRepo.findFirstByUserEmail(email);

        return remover.equals(commentedRecipe.getRecipeOwner()) || remover.equals(comment.getCommenter());
    }

    public List<Recipe> getAllRecipe() {
        return recipeService.getAllRecipe();
    }
}
