package JAVAPROJECT.CLASSES;

import java.util.Arrays;

public class Game {

    // on the score keeping class we access the getters from the three player
    // instances and compare the values of the index of the arrays when the column
    // is complete, then we give the winner the points.
    Player player1 = new Player();
    Player player2 = new Player();
    Player player3 = new Player();

    Player[] playersList = { player1, player2, player3 };

    int winner;

    int NUMBEROFTURNS = 12;

    int XCHAR = -1;

    // Array of booleans to check the first turn column is not chosen by two people
    boolean[] firstTurnArray = new boolean[11];

    // initializes as 12 falses. I'll use them to accurately check arrays for scores
    boolean[] takenColumn = new boolean[11];

    String[] scoreBoardTop = { "NAME", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "SCORE" };

    public void checkArrays() { // function to check the state of all the table columns
        int[] player1list = player1.getDiceRollArray();
        int[] player2list = player2.getDiceRollArray();
        int[] player3list = player3.getDiceRollArray();

        for (int i = 0; i < 11; i++) { // If there's a clear winner but the other two are a draw, it will draw
            // check that all the values of the column are taken and that column has not
            // been evaluated before

            if (player1list[i] != 0 && player2list[i] != 0
                    && player3list[i] != 0 && !takenColumn[i]) {
                // if two players have the same roll in a column, discard the points. If there's
                // a max give points to the max regardless of the tie
                if (player1list[i] == player2list[i] || player1list[i] == player3list[i]
                        || player2list[i] == player3list[i]) {
                    // if there is some instance of a tie, check if there's a max
                    if (player1list[i] > player2list[i] && player1list[i] > player3list[i]) {
                        player1.totalScore += (i + 2);
                        player2list[i] = XCHAR;
                        player3list[i] = XCHAR;
                    } else if (player2list[i] > player1list[i] && player2list[i] > player3list[i]) {
                        player2.totalScore += (i + 2);
                        player1list[i] = XCHAR;
                        player3list[i] = XCHAR;
                    } else if (player3list[i] > player2list[i] && player3list[i] > player1list[i]) {
                        player3.totalScore += (i + 2);
                        player2list[i] = XCHAR;
                        player1list[i] = XCHAR;
                    } else {
                        System.out.println("column " + i + " tie -- 0 points");
                    }

                    // if either player has a higher roll, then give the points to the player
                } else if (player1list[i] > player2list[i] && player1list[i] > player3list[i]) {
                    player1.totalScore += (i + 2);
                    player2list[i] = XCHAR;
                    player3list[i] = XCHAR;
                    System.out.println("column " + (i + 2) + " winner is: " + player1.name);
                } else if (player2list[i] > player1list[i] && player2list[i] > player3list[i]) {
                    player2.totalScore += (i + 2);
                    player1list[i] = XCHAR;
                    player3list[i] = XCHAR;
                    System.out.println("column " + (i + 2) + " winner is: " + player2.name);
                } else {
                    player3.totalScore += (i + 2);
                    player2list[i] = XCHAR;
                    player1list[i] = XCHAR;
                    System.out.println("column " + (i + 2) + " winner is: " + player3.name);

                }
                takenColumn[i] = true;
            }
        }

    }

    public void gameTable() {

        // a bunch of print statements, it looks bad
        // System.out.println("----------------------------------------------");
        // System.out.println("----------------------------------------------");

        // System.out.println("Game table:");
        // System.out.println(Arrays.toString(scoreBoardTop));
        // for (int playerIndex = 0; playerIndex < playersList.length; playerIndex++) {
        // System.out.println(
        // playersList[playerIndex].name + ": "
        // + " " + Arrays.toString(playersList[playerIndex].getDiceRollArray())
        // + " " + playersList[playerIndex].totalScore);
        // }
        // System.out.println("----------------------------------------------");
        // System.out.println("----------------------------------------------");

        String rowFormat = "| %-10s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-5s |%n";

        System.out.println("----------------------------------------------");
        System.out.printf(rowFormat, (Object[]) scoreBoardTop); // Print header

        // Print player rows
        for (Player player : playersList) {
            System.out.printf(rowFormat,
                    player.name,
                    (Object[]) player.getDiceRollArray(),
                    player.totalScore);
        }

        System.out.println("----------------------------------------------");
    }

    public void compareTotalScores() {
        int topScore = 0;

        for (int playerIndex = 0; playerIndex < playersList.length; playerIndex++) {
            if (playersList[playerIndex].totalScore > topScore) {
                topScore = playersList[playerIndex].totalScore;
                winner = playerIndex;
            }
        }

        System.out.println("The winner is " + playersList[winner].name + "with a score of " + topScore + "!");

    }

    public void play() {

        // for the duration of a game play each player's turn -- need to add first turn
        // restriction
        for (int currentTurn = 0; currentTurn < (NUMBEROFTURNS - 1); currentTurn++) {
            for (int playerIndex = 0; playerIndex < playersList.length; playerIndex++) {
                System.out.println(playersList[playerIndex].name + "'s Turn");
                playersList[playerIndex].turn(firstTurnArray);
            }
            checkArrays();
            // I add two here to keep up with the for loop stating on turn 2. -redo
            System.out.println("Turn number " + (currentTurn + 2) + " over...");
            gameTable();
        }
        compareTotalScores();

    }

}