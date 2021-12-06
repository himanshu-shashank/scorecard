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
        System.out.printf("Batting Order for %s\n", team1.getName());
        for(int i=0; i<playersEachTeam; i++) {
            Player player = new Player("P"+(i+1));
            System.out.printf("%s\n",player.getName());
            team1.addPlayer(player);
            if(i==0)
                team1.setOnStrikePlayer(player);
            else if(i==1)
                team1.setNonStrikePlayer(player);
            else
                team1.addPlayertoQueue(player);
        }

        ScoreCalculatorService scoreCalculator1 = new ScoreCalculatorService(team1);
        String[] ball = new String[]{"1","1","1","1","1","2"};
            System.out.printf("Over %d :\n",(1));
            int j;
            int k=0;
            for(j=1; j<=6;j++) {
                String score = ball[k++];
                System.out.printf("%s\n",(score));
                if (scoreCalculator1.needToBallMore(score))
                    j--;

                scoreCalculator1.CalculateScore(score, j);
                if (!team1.canBatMore)
                    break;
            }
            scorecardPrinter.printScoreCard(team1);

        ball = new String[]{"w","4","4","wd","w","1","6"};
        System.out.printf("Over %d :\n",(2));
        k=0;
        for(j=1; j<=6 ;j++) {
            String score = ball[k++];
            System.out.printf("%s\n",(score));
            if (scoreCalculator1.needToBallMore(score))
                j--;

            scoreCalculator1.CalculateScore(score, j);
            if (!team1.canBatMore)
                break;
        }
        scorecardPrinter.printScoreCard(team1);

        BattingSide team2 = new BattingSide("Team 2");
        System.out.printf("Batting Order for %s\n", team2.getName());
        ScoreCalculatorService scoreCalculator2 = new ScoreCalculatorService(team2);
        for(int i=0;i<playersEachTeam;i++) {
            Player player = new Player("P"+(playersEachTeam+i+1));
            System.out.printf("%s\n",player.getName());
            team2.addPlayer(player);
            if(i==0)
                team2.setOnStrikePlayer(player);
            else if(i==1)
                team2.setNonStrikePlayer(player);
            else
                team2.addPlayertoQueue(player);
        }

        ball = new String[]{"4","6","w","w","1","1"};
        k=0;
            System.out.printf("Over %d :\n",(1));
            for(j=1;j<=6;j++) {
                String score = ball[k++];
                System.out.printf("%s\n",(score));
                if(scoreCalculator2.needToBallMore(score))
                    j--;

                scoreCalculator2.CalculateScore(score, j);
                if(!team2.canBatMore || team2.hasScoredMore(team1, j))
                    break;
            }
            scorecardPrinter.printScoreCard(team2);
        ball = new String[]{"6","1","w","w"};
        k=0;
        System.out.printf("Over %d :\n",(1));
        for(j=1;j<=6;j++) {
            String score = ball[k++];
            System.out.printf("%s\n",(score));
            if(scoreCalculator2.needToBallMore(score))
                j--;

            scoreCalculator2.CalculateScore(score, j);
            if(!team2.canBatMore || team2.hasScoredMore(team1, j))
                break;
        }
        scorecardPrinter.printScoreCard(team2);

        scoreCalculator2.calculateFinalResult(team1, team2);


    }
}
