import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)){
            Person myPerson = new Person(input);
            ScoreConverter myScoreConverter = new ScoreConverter();
            SimpleCalculator mySimpleCalculator = new SimpleCalculator();
            TemperatureConverter myTemperatureConverter = new TemperatureConverter();
            Bank myBank = new Bank();

            System.out.println(myPerson.getName() + " is " + myPerson.getAge() + " years old, and " + myPerson.printStatus());
            System.out.println(myScoreConverter.scoreToGrade(input));
            System.out.println(mySimpleCalculator.calculate(input));

            System.out.println("Btw, the weather outside looks ... ");

            System.out.println("\n" + myTemperatureConverter.convertTemperature(input));

            System.out.println("\nYou have a bank account with us, with a value of: " + myBank.getCredit());

            while(true){
                boolean isTransactionCompleted = myBank.transaction(input);
                if(isTransactionCompleted) return;
            }

        }

    }

}
