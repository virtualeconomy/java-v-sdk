package v.systems.transaction;

public class LeaseTransaction extends ProvenTransaction {
    private String recipient;
    private Long amount;

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
