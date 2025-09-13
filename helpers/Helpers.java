package helpers;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import day3.Student;

public class Helpers {
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

    public void sortByScore(List<Student> students, boolean ascending) {
        Comparator<Student> comparator = Comparator.comparingInt(Student::getTestResults);
        comparator = !ascending ? comparator.reversed() : comparator;
        comparator = comparator.thenComparing(Student::getName);
        students.sort(comparator);
    }

}
