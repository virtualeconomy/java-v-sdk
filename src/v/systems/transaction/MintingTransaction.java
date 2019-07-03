package v.systems.transaction;

public class MintingTransaction extends Transaction {
    private String recipient;
    private float amount;
    private String status;
    private float feeCharged;
    private float height;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(float feeCharged) {
        this.feeCharged = feeCharged;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
