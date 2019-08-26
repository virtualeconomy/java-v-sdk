package v.systems.entity;

import lombok.Data;

@Data
public class BalanceDetail {
    private String address;
    private Long regular;
    private Long mintingAverage;
    private Long available;
    private Long effective;
    private Long height;
}
