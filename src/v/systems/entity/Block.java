package v.systems.entity;

import java.util.ArrayList;

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
    private Long blocksize;
    private Integer height;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public SPOSConsensus getSPOSConsensusObject() {
        return SPOSConsensusObject;
    }

    public void setSPOSConsensusObject(SPOSConsensus SPOSConsensusObject) {
        this.SPOSConsensusObject = SPOSConsensusObject;
    }

    public String getTransactionMerkleRoot() {
        return TransactionMerkleRoot;
    }

    public void setTransactionMerkleRoot(String transactionMerkleRoot) {
        TransactionMerkleRoot = transactionMerkleRoot;
    }

    public ArrayList<Object> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Object> transactions) {
        this.transactions = transactions;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(Long blocksize) {
        this.blocksize = blocksize;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
