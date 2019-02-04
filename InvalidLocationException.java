package slidingpuzzle;

public class InvalidLocationException extends Exception {
    private String message;

    public InvalidLocationException(String message) {
        this.message = message;
    }
}