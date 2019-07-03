package v.systems.serialization;

public interface JsonSerializable {
    String toAPIRequestJson();
    String toColdSignJson();
}
