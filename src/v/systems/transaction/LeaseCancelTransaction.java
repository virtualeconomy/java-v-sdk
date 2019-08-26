package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import v.systems.type.Base58Field;
import v.systems.type.NetworkType;
import v.systems.type.TransactionType;

public class LeaseCancelTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "fee", "feeScale", "timestamp", "leaseId"};
    @Getter
    @Setter
    @Base58Field
    protected String leaseId;
    @Getter
    @Setter
    protected LeaseTransaction lease;

    public LeaseCancelTransaction() {
        type = TransactionType.LeaseCancel.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) {
        JsonObject json = super.toAPIRequestJson(publicKey, signature).getAsJsonObject();
        json.addProperty("txId", this.leaseId);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey, NetworkType type) {
        JsonObject json = super.toColdSignJson(publicKey, type).getAsJsonObject();
        json.addProperty("txId", this.leaseId);
        return json;
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }
}
