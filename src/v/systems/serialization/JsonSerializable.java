package v.systems.serialization;

import com.google.gson.JsonElement;

public interface JsonSerializable {
    JsonElement toAPIRequestJson(String publicKey, String signature);
    JsonElement toColdSignJson(String publicKey);
}
