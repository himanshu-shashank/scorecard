package com.himanshu.scorecard.service;

import com.himanshu.scorecard.model.BattingSide;
import com.himanshu.scorecard.model.Player;

public class ScorecardPrinterService {

    public void printScoreCard(BattingSide team) {
        System.out.printf("Scorecard for %s \n",team.getName());
        System.out.printf("%s %5s %5s %10s %10s\n","Player Name","Score","4s","6s","Balls");

        for(int i=0; i<team.getPlayers().size(); i++) {
            Player player = team.getPlayers().get(i);
            String onStrike = team.getOnStrikePlayer()!= null && team.getOnStrikePlayer().equals(player) ? "*" : "";
            String nonStrike = team.getNonStrikePlayer()!= null && team.getNonStrikePlayer().equals(player) ? "*" : "";
            System.out.printf("%s%10d%10d%10d%10d\n",player.getName()+onStrike+nonStrike ,player.getScore(), player.getFours(), player.getSixes(),player.getBallsPlayed());
        }

        System.out.printf("Total: %d/%d\n",team.getTotalRunScored(), team.getTotalWicketsLost());
        System.out.printf("Overs: %.1f\n",team.getTotalOvers());
    }
}
