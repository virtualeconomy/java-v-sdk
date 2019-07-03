package v.systems.transaction;

public class PaymentTransaction extends ProvenTransaction {
    private Long fee;
    private String recipient;
    private Long feeScale;
    private Long amount;
    private String attachment;
    private String status;
    private Long feeCharged;
    private Integer height;

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getFeeScale() {
        return feeScale;
    }

    public void setFeeScale(Long feeScale) {
        this.feeScale = feeScale;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
