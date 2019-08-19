package v.systems.contract;

public class TokenBalance {
    private String address;
    private String tokenId;
    private Long balance;
    private Long unity;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getUnity() {
        return unity;
    }

    public void setUnity(Long unity) {
        this.unity = unity;
    }
}
