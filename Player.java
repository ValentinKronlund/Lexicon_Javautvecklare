import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Player {
    public String name;
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
        System.out.println("🧿 The Captain sees " + name + "'s dice through his cursed eye: " + Arrays.toString(dice) + "🧿");
    };

    public int[] makeGuess(Scanner input){
        int[] guess = new int[2];
        while(true){
            int amountOfDie = helper.askInt(input, "\nHow many dice? (1-5) ");
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