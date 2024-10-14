package tp1.exceptions;

public class OffWorldException extends GameModelException {
    public OffWorldException(String message) {
        super(message);
    }

    public OffWorldException(String message, Throwable cause) {
        super(message, cause);
    }
}
