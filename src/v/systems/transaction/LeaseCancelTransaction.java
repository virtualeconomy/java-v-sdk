package v.systems.transaction;

import v.systems.type.TransactionType;

public class LeaseCancelTransaction extends ProvenTransaction {
    public final String[] SERIALIZED_FIELDS = {"fee", "feeScale", "timestamp", "leaseId"};
    protected String leaseId;
    protected LeaseTransaction lease;

    public LeaseCancelTransaction() {
        type = TransactionType.CancelLease.getTypeId();
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
