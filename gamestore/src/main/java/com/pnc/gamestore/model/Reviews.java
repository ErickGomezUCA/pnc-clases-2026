package com.pnc.gamestore.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "player")
    private String user;

    @Column
    private Integer rating;

    @Column(length = 1000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Reviews() {
    }

    public Reviews(String user, Integer rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public UUID getId() { return id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
}
