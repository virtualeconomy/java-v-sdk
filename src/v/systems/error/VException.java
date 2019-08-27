package v.systems.error;

import lombok.Setter;
import lombok.Getter;

public class VException extends Exception {
    @Getter
    @Setter
    private String message;

    public VException(String message) {
        super(message);
        this.message = message;
    }

    public VException() {
        super();
        this.message = "Unexpected error occurred.";
    }
}
