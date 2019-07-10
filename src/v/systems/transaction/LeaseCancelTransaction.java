package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import v.systems.type.TransactionType;
import v.systems.utils.JsonHelper;

public class LeaseCancelTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"fee", "feeScale", "timestamp", "leaseId"};
    protected String leaseId;
    protected LeaseTransaction lease;

    public LeaseCancelTransaction() {
        type = TransactionType.CancelLease.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) throws JsonSyntaxException {
        JsonObject json = super.toAPIRequestJson(publicKey, signature).getAsJsonObject();
        json.addProperty("txId", this.leaseId);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey) throws JsonSyntaxException {
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
