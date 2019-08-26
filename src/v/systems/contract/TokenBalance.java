package v.systems.contract;

import lombok.Data;

@Data
public class TokenBalance {
    private String address;
    private String tokenId;
    private Long balance;
    private Long unity;
}
