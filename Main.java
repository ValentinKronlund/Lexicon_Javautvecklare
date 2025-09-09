import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)){
            LiarsDice game = new LiarsDice();
            game.InitiateGame(input);
            game.playGame(input);
        }

    }

}
