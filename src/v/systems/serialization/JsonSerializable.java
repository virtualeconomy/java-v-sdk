package v.systems.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

public interface JsonSerializable {
    JsonElement toAPIRequestJson(String publicKey, String signature);
    JsonElement toColdSignJson(String publicKey);
}
