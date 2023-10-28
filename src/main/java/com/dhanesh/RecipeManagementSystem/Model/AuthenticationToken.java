package com.dhanesh.RecipeManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    @OneToOne
    @JoinColumn(name = "fk_user")
    private User user;

    public AuthenticationToken(User user){
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime = LocalDateTime.now();
    }

}
