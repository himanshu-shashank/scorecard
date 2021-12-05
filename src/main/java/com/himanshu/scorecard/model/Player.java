package com.himanshu.scorecard.model;

public class Player {
    private String name;
    private int fours;
    private int sixes;
    private int ballsPlayed;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
