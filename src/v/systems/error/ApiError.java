package v.systems.error;

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
        return fromJson(json, ApiError.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ApiError> T fromJson(String json, Class<T> classType) {
        T result;
        try {
            result = JsonHelper.getGsonInstance().fromJson(json, classType);
        } catch (Exception ex) {
            result = (T) new ApiError(json);
        }
        return result;
    }
}
