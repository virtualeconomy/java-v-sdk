package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.bitcoinj.core.Base58;
import v.systems.type.Base58Field;
import v.systems.type.TransactionType;
import v.systems.utils.JsonHelper;

public class PaymentTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "timestamp", "amount", "fee", "feeScale", "recipient", "attachment"};
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
        JsonObject json = new JsonObject();
        json.addProperty("timestamp", this.timestamp);
        json.addProperty("amount", this.amount);
        json.addProperty("fee", this.fee);
        json.addProperty("feeScale", this.feeScale);
        json.addProperty("recipient", this.recipient);
        json.addProperty("senderPublicKey", publicKey);
        json.addProperty("attachment", this.attachment);
        json.addProperty("signature", signature);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey) throws JsonSyntaxException {
        JsonObject json = new JsonObject();
        json.addProperty("protocol", "v.systems");
        json.addProperty("api", getColdSignAPIVersion(this.amount));
        json.addProperty("opc", "transaction");
        json.addProperty("transactionType", this.type);
        json.addProperty("senderPublicKey", publicKey);
        json.addProperty("amount", this.amount);
        json.addProperty("fee", this.fee);
        json.addProperty("feeScale", this.feeScale);
        json.addProperty("recipient", this.recipient);
        json.addProperty("timestamp", this.timestamp);
        json.addProperty("attachment", this.attachment);
        return json;
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
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
