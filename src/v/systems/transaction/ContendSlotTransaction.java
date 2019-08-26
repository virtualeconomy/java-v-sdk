package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import v.systems.Blockchain;
import v.systems.type.NetworkType;
import v.systems.type.TransactionType;

public class ContendSlotTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "slotId", "fee", "feeScale", "timestamp"};
    public static final Long MIN_EFFECTIVE_BALANCE = 1000000 * Blockchain.V_UNITY;
    @Getter
    @Setter
    private Integer slotId;

    public ContendSlotTransaction() {
        type = TransactionType.ContendSlot.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) {
        JsonObject json = super.toAPIRequestJson(publicKey, signature).getAsJsonObject();
        json.addProperty("slotId", this.getSlotId());
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey, NetworkType type) {
        JsonObject json = super.toColdSignJson(publicKey, type, 1).getAsJsonObject();
        json.addProperty("slotId", this.getSlotId());
        return json;
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }
}
