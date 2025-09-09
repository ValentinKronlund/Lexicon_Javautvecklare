import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LiarsDice {
    boolean gameInProgress = false;
    private Player[] players;
    private int[][] allPlayerDice;
    private int[][] allPlayerGuesses;
    private Helpers helper = new Helpers();


    public void InitiateGame(Scanner input){
        System.out.println("\nThis is the most sorry lot o' land lubbers I must've ever seen! 💦");
        int playerAmount = helper.askInt(input, "How many souls have the guts to play Liar's Dice for their freedom? ");
        this.players = new Player[playerAmount];
        this.allPlayerDice = new int[playerAmount][];
        this.allPlayerGuesses = new int[playerAmount][];


        for(int i = 0; i < playerAmount; i++){
            Player newPlayer = new Player(input);
            players[i] = newPlayer;
        }

        gameInProgress = true;
        System.out.println("\nThe players have assembled, let's being!");
        System.out.println("Game in progress: " + gameInProgress + ", Players: ");
        for (Player p : players) {
            System.out.println(p.name);
        }
    }

    public void playGame(Scanner input){
        while(gameInProgress){
            for(int i = 0; i < players.length; i++){
                players[i].throwDice();
                allPlayerDice[i] = players[i].dice;
                allPlayerGuesses[i] = players[i].makeGuess(input);
            }
            System.out.println("\nAll dice: " + Arrays.deepToString(allPlayerDice));
            System.out.println("All guesses: " + Arrays.deepToString(allPlayerGuesses));

            System.out.println("Sum fine guesses have been made! ");



            gameInProgress = false;
        }
    }

    public int compareGuesses(int[][] guesses){
        int baseIndex = 0;
        for(int i = 1; i < guesses.length; i++){
            
        }
           
        return 0;
    }
    
}


class Player {
    String name;
    int[] dice;
    boolean isWinner = false;
    private Helpers helper = new Helpers();

    public Player(Scanner input){
        name = helper.askLine(input, "\nHarr Pirate! What be yerr name? ");
        System.out.println("A fine pirate has joined our crew, raise yer mugs for: " + name);
    };

    public void throwDice(){
        Random rng = new Random();
        System.out.println("\n" + name + " tosses his dice!");
        this.dice = new int[5];
        for(int i = 0; i < 5; i++){
            dice[i] = rng.nextInt(1, 7);
        }
        System.out.println("The Captain sees " + name + "'s dice through his cursed eye: " + Arrays.toString(dice));
    };

    public int[] makeGuess(Scanner input){
        int[] guess = new int[2];
        while(true){
            int amountOfDie = helper.askInt(input, "How many dice? (1-5) ");
            int faceValue = helper.askInt(input, "What are their faces? (1-6) ");
    
            if(amountOfDie >= 1 && amountOfDie <= 5 && faceValue >= 1 && faceValue <= 6){
                guess[0] = amountOfDie;
                guess[1] = faceValue;
                return guess;
            } else {
                System.out.println("Arr' you playing me for a fool!? Try again, or walk the plank!");
                continue;
            }
            
        }
    }
}