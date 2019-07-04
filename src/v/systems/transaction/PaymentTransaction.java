package v.systems.transaction;

import org.bitcoinj.core.Base58;
import v.systems.type.Base58Field;
import v.systems.type.TransactionType;

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
