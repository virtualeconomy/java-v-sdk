package v.systems.transaction;

public class LeaseCancelTransaction extends ProvenTransaction {
    private Long fee;
    private Long feeScale;
    private String leaseId;
    private String status;
    private Long feeCharged;
    private LeaseTransaction lease;
    private Integer height;

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getFeeScale() {
        return feeScale;
    }

    public void setFeeScale(Long feeScale) {
        this.feeScale = feeScale;
    }

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(Long feeCharged) {
        this.feeCharged = feeCharged;
    }

    public LeaseTransaction getLease() {
        return lease;
    }

    public void setLease(LeaseTransaction lease) {
        this.lease = lease;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
