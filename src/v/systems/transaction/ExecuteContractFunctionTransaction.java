package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.bitcoinj.core.Base58;
import v.systems.Account;
import v.systems.error.SerializationError;
import v.systems.type.Base58Field;
import v.systems.type.NetworkType;
import v.systems.type.SerializedWithSize;
import v.systems.type.TransactionType;

public class ExecuteContractFunctionTransaction extends ProvenTransaction {
    public final String[] BYTE_SERIALIZED_FIELDS = {"type", "contractId", "functionIndex", "functionData", "attachment", "fee", "feeScale", "timestamp"};

    @Getter
    @Setter
    @Base58Field
    protected String contractId;
    @Getter
    @Setter
    protected Short functionIndex;
    @Getter
    @Setter
    @SerializedWithSize
    @Base58Field
    protected String functionData;
    @Getter
    @Setter
    @SerializedWithSize
    @Base58Field
    protected String attachment;

    public ExecuteContractFunctionTransaction() {
        type = TransactionType.ExecuteContractFunction.getTypeId();
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) {
        JsonObject json = super.toAPIRequestJson(publicKey, signature).getAsJsonObject();
        json.addProperty("contractId", contractId);
        json.addProperty("functionIndex", functionIndex);
        json.addProperty("functionData", functionData);
        json.addProperty("attachment",attachment);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey, NetworkType type) {
        JsonObject json = super.toColdSignJson(publicKey, type, 3).getAsJsonObject();
        json.addProperty("contractId", contractId);
        json.addProperty("functionId", functionIndex);
        json.addProperty("function", functionData);
        json.addProperty("attachment",attachment);
        return json;
    }

    @Override
    protected String[] getByteSerializedFields() {
        return BYTE_SERIALIZED_FIELDS;
    }

    public void setAttachmentWithPlainText(String plainText) {
        this.attachment = Base58.encode(plainText.getBytes());
    }
}
