package com.dhanesh.RecipeManagementSystem.Repository;

import com.dhanesh.RecipeManagementSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);
}
