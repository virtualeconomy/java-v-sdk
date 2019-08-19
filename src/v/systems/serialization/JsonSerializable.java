package v.systems.serialization;

import com.google.gson.JsonElement;
import v.systems.type.NetworkType;

public interface JsonSerializable {
    JsonElement toAPIRequestJson(String publicKey, String signature);
    JsonElement toColdSignJson(String publicKey, NetworkType type);
}
