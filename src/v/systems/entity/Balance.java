package v.systems.entity;

import lombok.Data;

@Data
public class Balance {
    private String address;
    private Integer confirmations;
    private Long balance;
}