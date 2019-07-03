package v.systems.entity;

public class BalanceDetail {
    private String address;
    private Long regular;
    private Long mintingAverage;
    private Long available;
    private Long effective;
    private Long height;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRegular() {
        return regular;
    }

    public void setRegular(Long regular) {
        this.regular = regular;
    }

    public Long getMintingAverage() {
        return mintingAverage;
    }

    public void setMintingAverage(Long mintingAverage) {
        this.mintingAverage = mintingAverage;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getEffective() {
        return effective;
    }

    public void setEffective(Long effective) {
        this.effective = effective;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
