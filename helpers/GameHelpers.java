package helpers;
import day2.Player;
import java.util.Scanner;

public class GameHelpers {
    
    public int compareGuesses(int[][] guesses){
        int bestIndex = 0;
        for(int i = 1; i < guesses.length; i++){
            int[] currentGuess = guesses[i];
            int[] bestGuess = guesses[bestIndex];

            if(currentGuess[0] > bestGuess[0]){
                bestIndex = i;
            }
            else if(currentGuess[0] == bestGuess[0] && currentGuess[1] > bestGuess[1]){
                bestIndex = i;
            }

        }
           
        return bestIndex;
    }

    
    public boolean isPlayerALiar(int[] playerDice, int[] playerGuess){
        int diceCountNeeded = playerGuess[0];
        int faceValue = playerGuess[1];

        int actualDiceCount = 0;
        for(int d : playerDice){
            if(d == faceValue){
                actualDiceCount++;
            }
        }

        System.out.println("\nIS HE LYING?");
        System.out.println("The count needed is: " + diceCountNeeded + ", with a face of: " + faceValue);
        System.out.println("The landlubber has a count of: " + actualDiceCount);


        return diceCountNeeded != actualDiceCount;
    }

    public void winConditionMet(boolean didHeLie, String playerName){
        if(didHeLie){
                System.out.println("\nBahaha, looks like you caught " + playerName + " in his lie!");
                System.out.println("** A smile creeps across The Captain's face, as his maw grows into that of a Kraken! **");
                System.out.println("🦑 And the oceans'll have his soul for it!🦑");
                System.out.println("\n☠ " + playerName + ", you lose! ☠");
            } else {
                System.out.println("\nArr' an honest pirate we 'ave here! Bahahahaha!");
                System.out.println("** A smile creeps across The Captain's face, as his maw grows into that of a Kraken! **");
                System.out.println("🦑 Good for you, Landlubber, for it shall spare you yer soul! 🦑");
                System.out.println("** The Captain gobbles up the other players **");
                System.out.println("\n🏝️ " + playerName + ", you win! 🏝️");
            }
    }


    public void makeDecisions(Player[] players, Scanner input, boolean guessedLeaderIsLiar, int currentLeaderIndex){
        Helpers helper = new Helpers();
        for(int i = 0; i < players.length; i++){
                if(i == currentLeaderIndex) {continue;}

                boolean madeDecision = false;
                while(!madeDecision){
                    char guessALie = helper.askChar(input, "\nWhat will you do " + players[i].name + " -- Will you call this spineless maggot on his lie?");
                    
                    if(guessALie == 'Y'){
                        System.out.println("\n⚔️ Looks like a fight will begin!");
                        guessedLeaderIsLiar = true;
                        madeDecision = true;
                    } 
                    else if(guessALie == 'N'){
                        System.out.println("\nOoh, the safe bet it is. We'll see if it be gettin' ye back to shore!");
                        madeDecision = true;
                    }
                    else {
                        System.out.println("\n** The Captain spits onto the deck💦 ** Grow some guts landlubber! You may only say (Y)es or (N)o!");
                        continue;
                    }
                }
            }

    }
}
