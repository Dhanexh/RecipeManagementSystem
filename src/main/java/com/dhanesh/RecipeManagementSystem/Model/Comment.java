package com.dhanesh.RecipeManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private String commentBody;

    private LocalDateTime commentCreationTime;


    @ManyToOne
    @JoinColumn(name = "fk_recipe")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "fk_commenter")
    private User commenter;
}