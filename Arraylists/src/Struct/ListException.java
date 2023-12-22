package Struct;

public class ListException extends Exception {
    public ListException() {
        super();
    }

    public ListException(String message) {
        super(message);
    }

    public ListException(String message, Throwable cause) {
        super(message, cause);
    }
}