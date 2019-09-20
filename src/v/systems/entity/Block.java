package v.systems.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Block {
    private Integer version;
    private Long timestamp;
    private String reference;
    private SPOSConsensus SPOSConsensusObject;
    private String TransactionMerkleRoot;
    private ArrayList<Object> transactions = new ArrayList <Object> ();
    private String generator;
    private String signature;
    private Long fee;
    private Integer blocksize;
    private Integer height;
}
