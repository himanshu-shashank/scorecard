package com.himanshu.scorecard.service;

import com.himanshu.scorecard.exception.InvalidScoreException;
import com.himanshu.scorecard.model.BattingSide;
import com.himanshu.scorecard.model.Player;

public class ScoreCalculatorService {
    private BattingSide teamBatting;

    public ScoreCalculatorService(BattingSide teamBatting ) {
        this.teamBatting = teamBatting;
    }
    
    public void CalculateScore(String ball,int ballPlayed) {
        calculate(ball);

        if(ballPlayed== 6 && !needToBallMore(ball)){
            teamBatting.setTotalOvers(teamBatting.getTotalOvers()+1);
            swapPlayers();
        }
        else if(teamBatting.getOnStrikePlayer()==null){
            teamBatting.setTotalOvers(teamBatting.getTotalOvers() + (double)ballPlayed/10);
        }

    }

    public void calculate(String value) {

        Player onStrikePlayer = teamBatting.getOnStrikePlayer();
        Player nonStrikePlayer = teamBatting.getNonStrikePlayer();

        if(needToBallMore(value)) {
            teamBatting.setExtras(teamBatting.getExtras()+1);
            teamBatting.setTotalRunScored(teamBatting.getTotalRunScored() + 1);
        }
        else if(value.toLowerCase().equals("w")){
            onStrikePlayer.setBallsPlayed(onStrikePlayer.getBallsPlayed() + 1);
            teamBatting.setTotalWicketsLost(teamBatting.getTotalWicketsLost() + 1);

            if(noMorePlayerLeftToBat()) {
                teamBatting.setOnStrikePlayer(null);
                teamBatting.canBatMore = false;
            }
            else
                teamBatting.setOnStrikePlayer(teamBatting.getPlayersQueue().poll());

        }
        else if(value.matches("[0-6]")){
            int runScored = Integer.parseInt(value);

            if(runScored%2==1) {
                onStrikePlayer.setScore(onStrikePlayer.getScore()+ runScored);
                onStrikePlayer.setBallsPlayed(onStrikePlayer.getBallsPlayed() + 1);
                swapPlayers();

            } else {

                if(runScored == 2){
                    onStrikePlayer.setScore(onStrikePlayer.getScore()+ runScored);
                }

                if(runScored == 4){
                    onStrikePlayer.setScore(onStrikePlayer.getScore() + runScored);
                    onStrikePlayer.setFours(onStrikePlayer.getFours()+1);
                }

                if(runScored == 6){
                    onStrikePlayer.setScore(onStrikePlayer.getScore() + runScored);
                    onStrikePlayer.setSixes(onStrikePlayer.getSixes() + 1);
                }

                onStrikePlayer.setBallsPlayed(onStrikePlayer.getBallsPlayed() + 1);
            }

            teamBatting.setTotalRunScored(teamBatting.getTotalRunScored()+runScored);

        } else throw new InvalidScoreException();
    }

    public void swapPlayers() {
        Player player = teamBatting.getOnStrikePlayer();
        teamBatting.setOnStrikePlayer(teamBatting.getNonStrikePlayer());
        teamBatting.setNonStrikePlayer(player);
    }

    public void calculateFinalResult(BattingSide team1, BattingSide team2) {
        int team1Score = team1.getTotalRunScored();
        int team2Score = team2.getTotalRunScored();
        if(team1Score > team2Score){
            System.out.println("Result: "+team1.getName() +" won the match by "+(team1Score - team2Score )+" runs");
        } else if(team1Score < team2Score){
            System.out.println("Result: "+team2.getName() +" won the match by "+(team1.getPlayers().size()-team2.getTotalWicketsLost())+" wickets");
        }
    }

    public boolean needToBallMore(String score) {
        if(score.toLowerCase().equals("wd") || score.toLowerCase().equals("nb"))
            return true;
        return false;
    }

    private boolean noMorePlayerLeftToBat() {
        if(teamBatting.getPlayersQueue().isEmpty()){
            return true;
        }

        return false;
    }
}
