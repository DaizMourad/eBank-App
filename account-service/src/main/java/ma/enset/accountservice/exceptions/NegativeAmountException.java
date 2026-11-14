package ma.enset.accountservice.exceptions;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException(String message) {
        super(message);
    }
}
