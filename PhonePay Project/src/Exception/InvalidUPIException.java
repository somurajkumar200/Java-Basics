package Exception;
public class InvalidUPIException extends Exception {

    public InvalidUPIException() {
        super("Invalid UPI ID format");
    }

    public InvalidUPIException(String message) {
        super(message);
    }
}