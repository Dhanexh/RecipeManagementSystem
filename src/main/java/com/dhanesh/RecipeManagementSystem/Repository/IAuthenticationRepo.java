package com.dhanesh.RecipeManagementSystem.Repository;

import com.dhanesh.RecipeManagementSystem.Model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
