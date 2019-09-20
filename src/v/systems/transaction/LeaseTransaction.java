package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import v.systems.type.Base58Field;
import v.systems.type.NetworkType;
import v.systems.type.TransactionType;

public class LeaseTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "recipient", "amount", "fee", "feeScale", "timestamp"};
    @Getter
    @Setter
    @Base58Field
    protected String recipient;
    @Getter
    @Setter
    protected Long amount;

    public LeaseTransaction() {
        type = TransactionType.Lease.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) {
        JsonObject json = super.toAPIRequestJson(publicKey, signature).getAsJsonObject();
        json.addProperty("amount", this.amount);
        json.addProperty("recipient", this.recipient);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey, NetworkType type) {
        int api = getColdSignAPIVersion(this.amount);
        JsonObject json = super.toColdSignJson(publicKey, type, api).getAsJsonObject();
        json.addProperty("amount", this.amount);
        json.addProperty("recipient", this.recipient);
        return json;
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }
}
