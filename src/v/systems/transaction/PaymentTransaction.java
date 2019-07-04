package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import org.bitcoinj.core.Base58;
import v.systems.type.Base58Field;
import v.systems.type.TransactionType;
import v.systems.utils.JsonHelper;

public class PaymentTransaction extends ProvenTransaction {
    public final String[] SERIALIZED_FIELDS = {"type", "timestamp", "amount", "fee", "feeScale", "recipient", "attachment"};
    @Base58Field
    protected String recipient;
    protected Long amount;
    @Base58Field(isFixedLength = false)
    protected String attachment;

    public PaymentTransaction() {
        type = TransactionType.Payment.getTypeId();
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
                "  \"attachment\": \"%s\"," +
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
                attachment,
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
                "  \"timestamp\":%d," +
                "  \"attachment\":\"%s\"" +
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
                this.timestamp,
                attachment);
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public void setAttachmentWithPlainText(String plainText) {
        this.attachment = Base58.encode(plainText.getBytes());
    }
}
