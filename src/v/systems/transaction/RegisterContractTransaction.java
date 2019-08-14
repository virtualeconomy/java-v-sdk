package v.systems.transaction;

import v.systems.contract.Contract;
import v.systems.type.Base58Field;
import v.systems.type.SerializedWithSize;
import v.systems.type.TransactionType;

public class RegisterContractTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "contract", "initData", "description", "fee", "feeScale", "timestamp"};

    @SerializedWithSize
    protected Contract contract;
    private String contractId;
    @SerializedWithSize
    @Base58Field
    protected String initData;
    @SerializedWithSize
    protected String description;

    public RegisterContractTransaction() {
        type = TransactionType.RegisterContract.getTypeId();
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInitData() {
        return initData;
    }

    public void setInitData(String initData) {
        this.initData = initData;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
