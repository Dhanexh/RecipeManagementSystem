package com.dhanesh.RecipeManagementSystem.Service;

import com.dhanesh.RecipeManagementSystem.Model.Comment;
import com.dhanesh.RecipeManagementSystem.Repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    public void addComment(Comment newComment) {
        commentRepo.save(newComment);
    }

    public Comment findCommentById(Integer commentId) {
        return commentRepo.findById(commentId).orElseThrow();
    }

    public void removeCommentById(Integer commentId) {
        commentRepo.deleteById(commentId);
    }
}
