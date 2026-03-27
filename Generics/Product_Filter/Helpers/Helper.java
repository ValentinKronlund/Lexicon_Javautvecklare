package Generics.Product_Filter.Helpers;

import java.util.Scanner;

public class Helper {
    /* ----- HELPERS! 👷🏽 ----- */
    public String askLine(Scanner input, String prompt) {
        System.out.print(prompt);
        while (true) {
            String correctInput = input.nextLine().trim();

            if (correctInput != null && !correctInput.isEmpty()) {
                return correctInput;
            } else {
                System.out.println("⚠️ Please enter a correct input value, don't leave it blank! ⚠️");
            }
        }
    }

    public int askInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim();
            try {
                return Integer.parseInt(inputAsString);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a whole number! ⚠️");
            }

        }
    }

    public double askDobule(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(inputAsString);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }

        }
    }

    public char askChar(Scanner input, String prompt) {
        try {
            System.out.println(prompt);
            return input.nextLine().trim().charAt(0);

        } catch (Exception e) {
            System.out.println(prompt);
            return '.';
        }
    }

}
