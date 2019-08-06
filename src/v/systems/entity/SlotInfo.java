package v.systems.entity;

public class SlotInfo {
    private Integer slotId;
    private String address;
    private Long mintingAverageBalance;
    private Integer height;

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMintingAverageBalance() {
        return mintingAverageBalance;
    }

    public void setMintingAverageBalance(Long mintingAverageBalance) {
        this.mintingAverageBalance = mintingAverageBalance;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
