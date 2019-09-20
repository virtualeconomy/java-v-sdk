package v.systems.entity;

import lombok.Getter;
import lombok.Setter;

public class Result {
    private boolean ok;
    @Getter
    @Setter
    private String message;

    public static Result success() {
        return new Result(true, "Success");
    }

    public static Result fail(String errorMessage) {
        return new Result(false, errorMessage);
    }

    public Result(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }
}
