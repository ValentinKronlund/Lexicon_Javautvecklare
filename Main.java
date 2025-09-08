import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        // byte xByte = 1;
        // short xShort = 2;
        // int x = 5;
        // long xLong = 50L;

        // float xFloat = 5.0f;
        // double xDouble = 10.0;

        // char xChar = 'a';
        // boolean xBool = true;

        // String text = "hej";

        // int index = 0;

        try (Scanner input = new Scanner(System.in)){
            String name = askLine(input, "What's your name? ");
            int age = askInt(input, "How old are you? ");
            double score = askDobule(input, "What did you score on your last test? ");
            String operation = askLine(input, "What operation would you like to run? ");
            double value1 = askDobule(input, "What is the first value? ");
            double value2 = askDobule(input, "What is the second value? ");

            Person myPerson = new Person(name, age);
            ScoreConverter myScoreConverter = new ScoreConverter();
            SimpleCalculator mySimpleCalculator = new SimpleCalculator();
            TemperatureConverter myTemperatureConverter = new TemperatureConverter();

            System.out.println(myPerson.getName() + " is " + myPerson.getAge() + " years old, and " + myPerson.PrintStatus());
            System.out.println(myScoreConverter.ScoreToGrade(score));
            System.err.println(mySimpleCalculator.Calculate(operation, value1, value2));

            System.out.println("Btw, the weather outside looks ... ");

            char tempFormat = askChar(input, "What temperature are you converting from? ");
            double temperature = askDobule(input, "And how warm is it outside? ");

            System.err.println(myTemperatureConverter.ConvertTemperature(tempFormat, temperature));
        }

        

    }

    /* ----- HELPERS! 👷🏽 ----- */
    static String askLine(Scanner input, String prompt){
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    static int askInt(Scanner input, String prompt){
        while (true) { 
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim();
            try {return Integer.parseInt(inputAsString);}
            catch (NumberFormatException e) {System.out.println("Please enter a whole number!");}
            
        }
    }

    static double askDobule(Scanner input, String prompt){
         while (true) { 
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim().replace(',', '.');
            try {return Double.parseDouble(inputAsString);}
            catch (NumberFormatException e) {System.out.println("Please enter a number!");}
            
        }
    }

    static char askChar(Scanner input, String prompt){
        System.err.println(prompt);
        return input.nextLine().trim().charAt(0);
    }

}
