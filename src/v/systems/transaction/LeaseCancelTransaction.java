package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import v.systems.type.Base58Field;
import v.systems.type.TransactionType;

public class LeaseCancelTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "fee", "feeScale", "timestamp", "leaseId"};
    @Base58Field
    protected String leaseId;
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
    public JsonElement toColdSignJson(String publicKey) {
        JsonObject json = super.toColdSignJson(publicKey).getAsJsonObject();
        json.addProperty("txId", this.leaseId);
        return json;
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }

    public LeaseTransaction getLease() {
        return lease;
    }

    public void setLease(LeaseTransaction lease) {
        this.lease = lease;
    }
}
