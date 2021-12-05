package com.himanshu.scorecard;

import com.himanshu.scorecard.model.BattingSide;
import com.himanshu.scorecard.model.Player;
import com.himanshu.scorecard.service.ScoreCalculatorService;
import com.himanshu.scorecard.service.ScorecardPrinterService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int playersEachTeam = 5;
        int overs = 2;
        ScorecardPrinterService scorecardPrinter = new ScorecardPrinterService();
        Scanner scanner = new Scanner(System.in);

        BattingSide team1 = new BattingSide("Team 1");
        for(int i=0; i<playersEachTeam; i++) {
            Player player = new Player("P"+(i+1));
            team1.addPlayer(player);
            if(i==0)
                team1.setOnStrikePlayer(player);
            else if(i==1)
                team1.setNonStrikePlayer(player);
            else
                team1.addPlayertoQueue(player);
        }

        ScoreCalculatorService scoreCalculator1 = new ScoreCalculatorService(team1);
        for(int i=0; i<overs; i++) {
            System.out.printf("Over %d :\n",(i+1));
            int j;
            for(j=1; j<=6;j++) {
                String score = scanner.nextLine().trim();
                if(scoreCalculator1.needToBallMore(score))
                    j--;

                scoreCalculator1.CalculateScore(score, j);
                if(!team1.canBatMore)
                    break;

            }
            scorecardPrinter.printScoreCard(team1);
            if(!team1.canBatMore)
                break;
        }

        BattingSide team2 = new BattingSide("Team 2");
        ScoreCalculatorService scoreCalculator2 = new ScoreCalculatorService(team2);
        for(int i=0;i<playersEachTeam;i++) {
            Player player = new Player("P"+(playersEachTeam+i+1));
            team2.addPlayer(player);
            if(i==0)
                team2.setOnStrikePlayer(player);
            else if(i==1)
                team2.setNonStrikePlayer(player);
            else
                team2.addPlayertoQueue(player);
        }

        for(int i=0; i<overs; i++) {
            int j;
            System.out.printf("Over %d :\n",(i+1));
            for(j=1;j<=6;j++) {
                String score = scanner.nextLine().trim();
                if(scoreCalculator2.needToBallMore(score))
                    j--;

                scoreCalculator2.CalculateScore(score, j);
                if(!team2.canBatMore || team2.hasScoredMore(team1, j))
                    break;
            }
            scorecardPrinter.printScoreCard(team2);
            if(!team2.canBatMore)
                break;
        }

        scoreCalculator2.calculateFinalResult(team1, team2);


    }
}
