package v.systems.transaction;

import v.systems.entity.Proof;

import java.util.ArrayList;

public abstract class ProvenTransaction extends Transaction {
    private ArrayList<Proof> proofs;
    private Long feeCharged;
    private Long feeScale;
    private Long fee;

    public ArrayList<Proof> getProofs() {
        return proofs;
    }

    public void setProofs(ArrayList<Proof> proofs) {
        this.proofs = proofs;
    }

    public Long getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(Long feeCharged) {
        this.feeCharged = feeCharged;
    }

    public Long getFeeScale() {
        return feeScale;
    }

    public void setFeeScale(Long feeScale) {
        this.feeScale = feeScale;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
}
