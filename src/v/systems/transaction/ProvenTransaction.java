package v.systems.transaction;

import v.systems.entity.Proof;
import v.systems.serialization.JsonSerializable;

import java.util.ArrayList;

public abstract class ProvenTransaction extends BytesSerializableTransaction implements JsonSerializable {
    protected ArrayList<Proof> proofs;
    protected Long feeCharged;
    protected Short feeScale;
    protected Long fee;

    // According to "Cold and Hot Wallet Interaction Specification 2.0"
    // https://github.com/virtualeconomy/rfcs/blob/master/text/0003-wallet-interaction-specification-2.md
    public static int getColdSignAPIVersion(Long amount) {
        if (amount % 100 == 0) {
            return 1;
        }
        return amount > 9007199254740991L ? 2 : 1;
    }

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

    public Short getFeeScale() {
        return feeScale;
    }

    public void setFeeScale(Short feeScale) {
        this.feeScale = feeScale;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
}
