package Generics.Product_Filter.Errors;

public class InvalidAmountException extends IllegalArgumentException {

    public InvalidAmountException() {
        super("Inavlid amount, must be larger than 0");
    }

}
