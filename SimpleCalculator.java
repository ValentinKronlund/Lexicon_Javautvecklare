public class SimpleCalculator {
    public double calculate(String operation, double value_1, double value_2){
        switch (operation) {
            case "+": {return value_1 + value_2;}
            case "-": {return value_1 - value_2;}
            case "*": {return value_1 * value_2;}
            case "/": {return value_1 / value_2;}
            default:
                throw new AssertionError("We don't have support for that operation at the moment!");
        }
    }
}
