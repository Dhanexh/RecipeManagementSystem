package com.dhanesh.RecipeManagementSystem.Repository;

import com.dhanesh.RecipeManagementSystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment,Integer> {
}
