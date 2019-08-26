package v.systems.entity;

import lombok.Data;

@Data
public class SlotInfo {
    private Integer slotId;
    private String address;
    private Long mintingAverageBalance;
    private Integer height;
}
