package com.pnc.gamestore.model;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "game_details")
public class GameDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 2000)
    private String about;

    @Column
    private Integer publishYear;

    @OneToOne
    @JoinColumn(name = "game_id", unique = true)
    private Game game;

    public GameDetails() {
    }

    public GameDetails(String about, Integer publishYear) {
        this.about = about;
        this.publishYear = publishYear;
    }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
}
