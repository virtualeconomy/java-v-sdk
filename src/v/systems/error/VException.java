package v.systems.error;

public class VException extends Exception {

    private String message;

    public VException(String message) {
        super(message);
        this.message = message;
    }

    public VException() {
        super();
        this.message = "Unexpected error occurred.";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
