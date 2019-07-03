package v.systems.error;

import com.google.gson.JsonSyntaxException;
import v.systems.utils.JsonHelper;

public class VException extends Exception {
    private Integer error;
    private String message;

    public VException(String message, int errorCode) {
        super(message);
        this.message = message;
        error = errorCode;
    }

    public VException(String message) {
        super(message);
        this.message = message;
        error = 0;
    }

    public Integer getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static VException fromJson(String json) {
        VException result;
        try {
            result = JsonHelper.getGsonInstance().fromJson(json, VException.class);
        } catch (JsonSyntaxException ex) {
            result = new VException(json);
        }
        return result;
    }
}
