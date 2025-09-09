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


        return diceCountNeeded == actualDiceCount;
    }
}
