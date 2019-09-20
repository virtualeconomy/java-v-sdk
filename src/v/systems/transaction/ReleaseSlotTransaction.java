package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import v.systems.type.NetworkType;
import v.systems.type.TransactionType;

public class ReleaseSlotTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "slotId", "fee", "feeScale", "timestamp"};
    @Getter
    @Setter
    private Integer slotId;

    public ReleaseSlotTransaction() {
        type = TransactionType.ReleaseSlot.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) {
        JsonObject json = super.toAPIRequestJson(publicKey, signature).getAsJsonObject();
        json.addProperty("slotId", slotId);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey, NetworkType type) {
        JsonObject json = super.toColdSignJson(publicKey, type, 1).getAsJsonObject();
        json.addProperty("slotId", slotId);
        return json;
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }
}
