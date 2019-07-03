package v.systems.transaction;

import v.systems.entity.Proof;

import java.util.ArrayList;

public abstract class ProvenTransaction extends Transaction {
    private ArrayList<Proof> proofs;

    public ArrayList<Proof> getProofs() {
        return proofs;
    }

    public void setProofs(ArrayList<Proof> proofs) {
        this.proofs = proofs;
    }
}
