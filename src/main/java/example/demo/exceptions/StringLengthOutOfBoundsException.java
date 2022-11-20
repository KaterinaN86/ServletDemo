package example.demo.exceptions;

public class StringLengthOutOfBoundsException extends RuntimeException{
    public StringLengthOutOfBoundsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public StringLengthOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}
