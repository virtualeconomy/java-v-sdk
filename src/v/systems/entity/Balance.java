package v.systems.entity;

public class Balance {
    private String address;
    private Integer confirmations;
    private Long balance;

    public String getAddress() {
        return address;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public Long getBalance() {
        return balance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}