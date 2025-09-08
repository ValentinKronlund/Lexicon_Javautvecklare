import java.util.Scanner;

public class SimpleCalculator {
    public double calculate(Scanner input){
        Helpers helper = new Helpers();

        String operation = helper.askLine(input, "What operation would you like to run? ");
        double value1 = helper.askDobule(input, "What is the first value? ");
        double value2 = helper.askDobule(input, "What is the second value? ");
        switch (operation) {
            case "+": {return value1 + value2;}
            case "-": {return value1 - value2;}
            case "*": {return value1 * value2;}
            case "/": {return value1 / value2;}
            default:
                throw new AssertionError("We don't have support for that operation at the moment!");
        }
    }
}
