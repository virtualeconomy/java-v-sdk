package v.systems.contract;

import java.util.List;

public class ContractInfo {
    private String contractId;
    private String transactionId;
    private String type;
    private List<InfoData> info;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<InfoData> getInfo() {
        return info;
    }

    public void setInfo(List<InfoData> info) {
        this.info = info;
    }
}
