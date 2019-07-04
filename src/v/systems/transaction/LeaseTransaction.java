package v.systems.transaction;

import v.systems.type.Base58Field;
import v.systems.type.TransactionType;

public class LeaseTransaction extends ProvenTransaction {
    public final String[] SERIALIZED_FIELDS = {"recipient", "amount", "fee", "feeScale", "timestamp"};
    @Base58Field
    protected String recipient;
    protected Long amount;

    public LeaseTransaction() {
        type = TransactionType.Lease.getTypeId();
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
