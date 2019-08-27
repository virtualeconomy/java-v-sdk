package v.systems.error;

import lombok.Setter;
import lombok.Getter;

public class TransactionError extends ApiError {

    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String details;

    public TransactionError(String details) {
        this("error", details);
    }

    public TransactionError(String status, String details) {
        super(details);
        this.details = details;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return details;
    }
}
