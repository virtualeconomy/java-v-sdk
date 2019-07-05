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
        JsonObject json = new JsonObject();
        json.addProperty("timestamp", this.timestamp);
        json.addProperty("txId", this.leaseId);
        json.addProperty("fee", this.fee);
        json.addProperty("feeScale", this.feeScale);
        json.addProperty("senderPublicKey", publicKey);
        json.addProperty("signature", signature);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey) throws JsonSyntaxException {
        JsonObject json = new JsonObject();
        json.addProperty("protocol", "v.systems");
        json.addProperty("api", 1);
        json.addProperty("opc", "transaction");
        json.addProperty("transactionType", this.type);
        json.addProperty("senderPublicKey", publicKey);
        json.addProperty("txId", this.leaseId);
        json.addProperty("fee", this.fee);
        json.addProperty("feeScale", this.feeScale);
        json.addProperty("timestamp", this.timestamp);
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
