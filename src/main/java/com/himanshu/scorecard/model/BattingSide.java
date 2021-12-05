package com.himanshu.scorecard.model;

import java.util.LinkedList;
import java.util.Queue;

public class BattingSide extends Team {
    public boolean canBatMore = true;
    private Player onStrikePlayer;
    private Player nonStrikePlayer;
    private Queue<Player> playersQueue;
    private int extras;
    private double totalOvers;
    private int totalRunScored;
    private boolean canPlayMore;

    public boolean isCanBatMore() {
        return canBatMore;
    }

    public void setCanBatMore(boolean canBatMore) {
        this.canBatMore = canBatMore;
    }

    public boolean isCanPlayMore() {
        return canPlayMore;
    }

    public void setCanPlayMore(boolean canPlayMore) {
        this.canPlayMore = canPlayMore;
    }

    public int getTotalRunScored() {
        return totalRunScored;
    }

    public void setTotalRunScored(int totalRunScored) {
        this.totalRunScored = totalRunScored;
    }

    public int getTotalWicketsLost() {
        return totalWicketsLost;
    }

    public void setTotalWicketsLost(int totalWicketsLost) {
        this.totalWicketsLost = totalWicketsLost;
    }

    private int totalWicketsLost;

    public double getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(double totalOvers) {
        this.totalOvers = totalOvers;
    }

    public int getExtras() {
        return extras;
    }


    public void setExtras(int extras) {
        this.extras = extras;
    }

    public Queue<Player> getPlayersQueue() {
        return playersQueue;
    }

    public void setPlayersQueue(Queue<Player> playersQueue) {
        this.playersQueue = playersQueue;
    }

    public BattingSide(String name) {
        super(name);
        playersQueue = new LinkedList<>();

    }

    public void  addPlayertoQueue(Player player){
        this.playersQueue.add(player);
    }

    public Player getOnStrikePlayer() {
        return onStrikePlayer;
    }

    public void setOnStrikePlayer(Player onStrikePlayer) {
        this.onStrikePlayer = onStrikePlayer;
    }


    public Player getNonStrikePlayer() {
        return nonStrikePlayer;
    }

    public void setNonStrikePlayer(Player nonStrikePlayer) {
        this.nonStrikePlayer = nonStrikePlayer;
    }

    public boolean hasScoredMore(BattingSide otherTeam,int ballPlayed) {
        if(this.totalRunScored > otherTeam.totalRunScored){
            setTotalOvers(this.getTotalOvers() + (double)ballPlayed/10);
            canBatMore = false;
            return true;
        }
        return false;
    }
}
