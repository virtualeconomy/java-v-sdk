package v.systems.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

public interface JsonSerializable {
    JsonElement toAPIRequestJson(String publicKey, String signature) throws JsonSyntaxException;
    JsonElement toColdSignJson(String publicKey) throws JsonSyntaxException;
}
