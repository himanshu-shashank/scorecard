package com.himanshu.scorecard.model;

import com.himanshu.scorecard.model.Player;

import java.util.ArrayList;
import java.util.List;


public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
