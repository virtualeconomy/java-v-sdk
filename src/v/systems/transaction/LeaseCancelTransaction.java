package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import v.systems.type.TransactionType;
import v.systems.utils.JsonHelper;

public class LeaseCancelTransaction extends ProvenTransaction {
    public final String[] SERIALIZED_FIELDS = {"fee", "feeScale", "timestamp", "leaseId"};
    protected String leaseId;
    protected LeaseTransaction lease;

    public LeaseCancelTransaction() {
        type = TransactionType.CancelLease.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) throws JsonSyntaxException {
        String jsonTemplate = "{" +
                "  \"timestamp\": %d," +
                "  \"txId\": %d," +
                "  \"fee\": %d," +
                "  \"feeScale\": %d," +
                "  \"senderPublicKey\": \"%s\"," +
                "  \"signature\": \"%s\"" +
                "}";
        String json = String.format(
                jsonTemplate,
                this.timestamp,
                this.leaseId,
                this.fee,
                this.feeScale,
                publicKey,
                signature);
        return JsonHelper.getParserInstance().parse(json);
    }

    @Override
    public JsonElement toColdSignJson(String publicKey) throws JsonSyntaxException {
        String jsonTemplate = "{" +
                "  \"protocol\":\"v.systems\"," +
                "  \"api\":1," +
                "  \"opc\":\"transaction\"," +
                "  \"transactionType\":%d," +
                "  \"senderPublicKey\":\"%s\"," +
                "  \"txId\":%d," +
                "  \"fee\":%d," +
                "  \"feeScale\":%d," +
                "  \"timestamp\":%d" +
                "}";
        String json = String.format(
                jsonTemplate,
                this.type,
                publicKey,
                this.leaseId,
                this.fee,
                this.feeScale,
                this.timestamp);
        return JsonHelper.getParserInstance().parse(json);
    }

    @Override
    protected String[] getSerializedFields() {
        return SERIALIZED_FIELDS;
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
