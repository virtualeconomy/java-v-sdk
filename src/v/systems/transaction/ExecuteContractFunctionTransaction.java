package v.systems.transaction;

import org.bitcoinj.core.Base58;
import v.systems.type.Base58Field;
import v.systems.type.SerializedWithSize;
import v.systems.type.TransactionType;

public class ExecuteContractFunctionTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "contractId", "functionIndex", "functionData", "attachment", "fee", "feeScale", "timestamp"};

    @Base58Field
    protected String contractId;
    protected Short functionIndex;
    @SerializedWithSize
    @Base58Field
    protected String functionData;
    @SerializedWithSize
    @Base58Field
    protected String attachment;

    public ExecuteContractFunctionTransaction() {
        type = TransactionType.ExecuteContractFunction.getTypeId();
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Short getFunctionIndex() {
        return functionIndex;
    }

    public void setFunctionIndex(Short functionIndex) {
        this.functionIndex = functionIndex;
    }

    public String getFunctionData() {
        return functionData;
    }

    public void setFunctionData(String functionData) {
        this.functionData = functionData;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public void setAttachmentWithPlainText(String plainText) {
        this.attachment = Base58.encode(plainText.getBytes());
    }
}
