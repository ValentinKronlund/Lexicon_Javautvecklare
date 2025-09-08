import java.util.Scanner;

public class Helpers {
    /* ----- HELPERS! 👷🏽 ----- */
    public String askLine(Scanner input, String prompt){
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    public int askInt(Scanner input, String prompt){
        while (true) { 
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim();
            try {return Integer.parseInt(inputAsString);}
            catch (NumberFormatException e) {System.out.println("Please enter a whole number!");}
            
        }
    }

    public double askDobule(Scanner input, String prompt){
         while (true) { 
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim().replace(',', '.');
            try {return Double.parseDouble(inputAsString);}
            catch (NumberFormatException e) {System.out.println("Please enter a number!");}
            
        }
    }

    public char askChar(Scanner input, String prompt){
        System.err.println(prompt);
        return input.nextLine().trim().charAt(0);
    }
    
}
