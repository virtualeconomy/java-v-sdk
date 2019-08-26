package v.systems.contract;

import lombok.Data;

import java.util.List;

@Data
public class ContractInfo {
    private String contractId;
    private String transactionId;
    private String type;
    private List<InfoData> info;

    public ContractType getContractType() {
        return ContractType.parse(type);
    }
}
