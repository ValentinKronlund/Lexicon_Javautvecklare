package Generics.Product_Filter.Errors;

public class OutOfStockException extends IllegalArgumentException {

    public OutOfStockException() {
        super("There are currently not enough of those items in stock!");

    }

    public OutOfStockException(int quantity) {
        super("There are currently not enough of those items in stock! -- Available quantity: " + quantity);
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

}
