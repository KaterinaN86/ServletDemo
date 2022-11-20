package example.demo.exceptions;

public class StringFormatException extends RuntimeException{
    public StringFormatException(String message) {
        super(message);
    }
    public StringFormatException(Throwable cause) {
        super(cause);
    }
}
