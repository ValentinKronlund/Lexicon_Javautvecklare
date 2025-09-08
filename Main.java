import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Helpers helper = new Helpers();

        try (Scanner input = new Scanner(System.in)){
            String name = helper.askLine(input, "What's your name? ");
            int age = helper.askInt(input, "How old are you? ");
            double score = helper.askDobule(input, "What did you score on your last test? ");
            String operation = helper.askLine(input, "What operation would you like to run? ");
            double value1 = helper.askDobule(input, "What is the first value? ");
            double value2 = helper.askDobule(input, "What is the second value? ");

            Person myPerson = new Person(name, age);
            ScoreConverter myScoreConverter = new ScoreConverter();
            SimpleCalculator mySimpleCalculator = new SimpleCalculator();
            TemperatureConverter myTemperatureConverter = new TemperatureConverter();
            Bank myBank = new Bank();

            System.out.println(myPerson.getName() + " is " + myPerson.getAge() + " years old, and " + myPerson.printStatus());
            System.out.println(myScoreConverter.scoreToGrade(score));
            System.err.println(mySimpleCalculator.calculate(operation, value1, value2));

            System.out.println("Btw, the weather outside looks ... ");

            System.err.println("\n" + myTemperatureConverter.convertTemperature(input));

            System.out.println("\nYou have a bank account with us, with a value of: " + myBank.getCredit());

            while(true){
                boolean isTransactionCompleted = myBank.transaction(input);
                if(isTransactionCompleted) return;
            }

        }

    }

}
