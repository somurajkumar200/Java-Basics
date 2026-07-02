package Exception;
public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        super("User already exists with this mobile number");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}