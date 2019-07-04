package v.systems.serialization;

import com.google.gson.JsonElement;
import v.systems.error.SerializationError;

public interface JsonSerializable {
    JsonElement toAPIRequestJson(String publicKey) throws SerializationError;
    JsonElement toColdSignJson(String publicKey) throws SerializationError;
}
