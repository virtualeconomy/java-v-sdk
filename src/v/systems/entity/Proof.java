package v.systems.entity;

import lombok.Data;

@Data
public class Proof {
    private String proofType;
    private String publicKey;
    private String signature;
    private String address;
}
