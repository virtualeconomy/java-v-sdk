package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import v.systems.type.Base58Field;
import v.systems.type.TransactionType;
import v.systems.utils.JsonHelper;

public class LeaseTransaction extends ProvenTransaction {
    public final String[] SERIALIZED_FIELDS = {"recipient", "amount", "fee", "feeScale", "timestamp"};
    @Base58Field
    protected String recipient;
    protected Long amount;

    public LeaseTransaction() {
        type = TransactionType.Lease.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) throws JsonSyntaxException {
        String jsonTemplate = "{" +
                "  \"timestamp\": %d," +
                "  \"amount\": %d," +
                "  \"fee\": %d," +
                "  \"feeScale\": %d," +
                "  \"recipient\": \"%s\"," +
                "  \"senderPublicKey\": \"%s\"," +
                "  \"signature\": \"%s\"" +
                "}";
        String json = String.format(
                jsonTemplate,
                this.timestamp,
                this.amount,
                this.fee,
                this.feeScale,
                this.recipient,
                publicKey,
                signature);
        return JsonHelper.getParserInstance().parse(json);
    }

    @Override
    public JsonElement toColdSignJson(String publicKey) throws JsonSyntaxException {
        String jsonTemplate = "{" +
                "  \"protocol\":\"v.systems\"," +
                "  \"api\":%d," +
                "  \"opc\":\"transaction\"," +
                "  \"transactionType\":%d," +
                "  \"senderPublicKey\":\"%s\"," +
                "  \"amount\":%d," +
                "  \"fee\":%d," +
                "  \"feeScale\":%d," +
                "  \"recipient\":\"%s\"," +
                "  \"timestamp\":%d" +
                "}";
        String json = String.format(
                jsonTemplate,
                getColdSignAPIVersion(this.amount),
                this.type,
                publicKey,
                this.amount,
                this.fee,
                this.feeScale,
                this.recipient,
                this.timestamp);
        return JsonHelper.getParserInstance().parse(json);
    }

    @Override
    protected String[] getSerializedFields() {
        return SERIALIZED_FIELDS;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
