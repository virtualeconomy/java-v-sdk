package v.systems.error;

import com.google.gson.JsonSyntaxException;
import v.systems.utils.JsonHelper;

public class ApiError extends VException {
    private Integer error;

    public ApiError(String message, int errorCode) {
        super(message);
        this.error = errorCode;
    }

    public ApiError(String message) {
        super(message);
        this.error = 0;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }


    public static ApiError fromJson(String json) {
        ApiError result;
        try {
            result = JsonHelper.getGsonInstance().fromJson(json, ApiError.class);
        } catch (JsonSyntaxException ex) {
            result = new ApiError(json);
        }
        return result;
    }
}
