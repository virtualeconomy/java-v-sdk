package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bitcoinj.core.Base58;
import v.systems.type.Base58Field;
import v.systems.type.SerializedWithSize;
import v.systems.type.TransactionType;

public class PaymentTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "timestamp", "amount", "fee", "feeScale", "recipient", "attachment"};
    @Base58Field
    protected String recipient;
    protected Long amount;
    @Base58Field
    @SerializedWithSize
    protected String attachment;

    public PaymentTransaction() {
        type = TransactionType.Payment.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) {
        JsonObject json = super.toAPIRequestJson(publicKey, signature).getAsJsonObject();
        json.addProperty("amount", this.amount);
        json.addProperty("recipient", this.recipient);
        json.addProperty("attachment", this.attachment);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey) {
        int api = getColdSignAPIVersion(this.amount);
        JsonObject json = super.toColdSignJson(publicKey, api).getAsJsonObject();
        json.addProperty("amount", this.amount);
        json.addProperty("recipient", this.recipient);
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
