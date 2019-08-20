package v.systems.entity;

public class Result {
    private boolean ok;
    private String message;

    public static Result success() {
        return new Result(true, "Success");
    }

    public static Result fail(String errorMessage) {
        return new Result(false, errorMessage);
    }

    public Result(boolean ok, String message) {
        this.ok = ok;
        this.setMessage(message);
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
