package JAVAPROJECT.CLASSES;

public class Player {

    // create variables for differnet scores?
    // maybe I should create a differnt class for the scores... ask
    int aces;
    int twos;
    int threes;
    int fours;
    int fives;
    int sixes;
    int totalScore;

    // Max number of rerolls
    int maxReroll = 2;

    // initialize Dice constructor
    Dice dice = new Dice();

    public Player(String name) {
        aces = 0;
        twos = 0;
        threes = 0;
        fours = 0;
        fives = 0;
        sixes = 0;
        totalScore = 0;

        turn();
    }

    public void turn() {
        // function to representa turn, ideally will be called for each player back to
        // back for a set number of turns
        dice.generateDice();
        dice.keepDice();
        System.out.println(aces);

        // if the reroll index is lower than max, reroll and call keepDice
        for (int rerollNumber = 0; rerollNumber < maxReroll; rerollNumber++) {
            System.out.println("Reroll number " + (rerollNumber + 1) + " out of 2");
            dice.rerollDice();
            dice.keepDice();
        }

        System.out.println("TURN FINISHED");
    }

}
