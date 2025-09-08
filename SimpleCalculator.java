import java.util.Scanner;

public class SimpleCalculator {

    public double calculate(Scanner input){
        Helpers helper = new Helpers();

        while(true){
            char operation = helper.askLine(input, "What operation would you like to run? '+', '-', '*', '/' ").charAt(0);
            if(!isCorrectOperator(operation)){
                System.out.println("Invalid operation -- try again");
                continue;
            }
            double value1 = helper.askDobule(input, "What is the first value? ");
            double value2 = helper.askDobule(input, "What is the second value? ");
            switch (operation) {
                case '+': {return value1 + value2;}
                case '-': {return value1 - value2;}
                case '*': {return value1 * value2;}
                case '/': {return value1 / value2;}
                default: {
                    System.out.println("We don't have support for that operation at the moment!");
                    break;
                }
            }
        }
    }

    private boolean isCorrectOperator(char operation){
        return
            operation == '+' ||
            operation == '-' ||
            operation == '*' ||
            operation == '/';
    }

}
