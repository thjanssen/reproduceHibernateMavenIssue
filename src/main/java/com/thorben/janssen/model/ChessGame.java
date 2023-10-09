package com.thorben.janssen.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.processing.CheckHQL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@CheckHQL
@NamedQuery(name = "#findByPlayerWhite", query = "SELECT g FROM ChessGame g WHERE g.playerWhite = :playerWhite")
@Entity
public class ChessGame {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate date;

    private int round;

    private String playerWhite;
    
    private String playerBlack;

    @OneToMany(mappedBy = "game")
    private Set<ChessMove> moves = new HashSet<>();

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getVersion() {
        return version;
    }

    public Set<ChessMove> getMoves() {
        return moves;
    }

    public void setMoves(Set<ChessMove> moves) {
        this.moves = moves;
    }

    public String getPlayerWhite() {
        return playerWhite;
    }

    public void setPlayerWhite(String playerWhite) {
        this.playerWhite = playerWhite;
    }

    public String getPlayerBlack() {
        return playerBlack;
    }

    public void setPlayerBlack(String playerBlack) {
        this.playerBlack = playerBlack;
    }

    @Override
    public String toString() {
        return "ChessGame [id=" + id + ", date=" + date + ", round=" + round + ", playerWhite=" + playerWhite
                + ", playerBlack=" + playerBlack + ", version=" + version + "]";
    }

    
}