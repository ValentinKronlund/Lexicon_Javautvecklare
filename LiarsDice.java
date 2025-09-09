import java.util.Arrays;
import java.util.Scanner;

public class LiarsDice {
    boolean gameInProgress = false;
    private Player[] players;
    private int[][] allPlayerDice;
    private int[][] allPlayerGuesses;
    private Helpers helper = new Helpers();
    private GameHelpers gameHelpers = new GameHelpers();


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
            boolean guessedLeaderIsLiar = false;

            for(int i = 0; i < players.length; i++){
                players[i].throwDice();
                allPlayerDice[i] = players[i].dice;
                allPlayerGuesses[i] = players[i].makeGuess(input);
            }
            System.out.println("\nAll dice: " + Arrays.deepToString(allPlayerDice));
            System.out.println("All guesses: " + Arrays.deepToString(allPlayerGuesses));

            System.out.println("\nSum fine guesses have been made! \n");

            int currentLeaderIndex = gameHelpers.compareGuesses(allPlayerGuesses);

            System.out.println(players[currentLeaderIndex].name + " is the current leader! He's betting " + Arrays.toString(allPlayerGuesses[currentLeaderIndex]));
            gameHelpers.makeDecisions(players, input, guessedLeaderIsLiar, currentLeaderIndex);

            if(guessedLeaderIsLiar){
                boolean didHeLie = gameHelpers.isPlayerALiar(players[currentLeaderIndex].dice, allPlayerGuesses[currentLeaderIndex]);
                gameHelpers.winConditionMet(didHeLie, players[currentLeaderIndex].name);
            } else {
                gameHelpers.winConditionMet(false, players[currentLeaderIndex].name);
            }

            gameInProgress = false;
        }
    }

}