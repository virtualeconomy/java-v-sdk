package v.systems.contract;

import lombok.Data;
import org.bitcoinj.core.Base58;
import v.systems.error.SerializationError;
import v.systems.serialization.BytesSerializable;
import v.systems.utils.BytesHelper;

import java.util.ArrayList;
import java.util.List;

@Data
public class Contract implements BytesSerializable {
    private String languageCode;
    private Integer languageVersion;
    private List<String> triggers;
    private List<String> descriptors;
    private List<String> stateVariables;
    private ContractTextual textual;

    public Contract() {
        languageCode = "vdds";
        triggers = new ArrayList<String>();
        descriptors = new ArrayList<String>();
        stateVariables = new ArrayList<String>();
        textual = new ContractTextual();
    }

    @Override
    public byte[] toBytes() throws SerializationError {
        try {
            byte[] triggerBytes = serializeField(triggers);
            byte[] descriptorBytes = serializeField(descriptors);
            byte[] stateVarBytes = serializeField(stateVariables);
            byte[][] result = new byte[][]{
                    BytesHelper.toBytes(languageCode),
                    BytesHelper.toBytes(languageVersion),
                    BytesHelper.toBytes((short)triggerBytes.length),
                    triggerBytes,
                    BytesHelper.toBytes((short)descriptorBytes.length),
                    descriptorBytes,
                    BytesHelper.toBytes((short)stateVarBytes.length),
                    stateVarBytes,
                    textual.toBytes()
            };
            return BytesHelper.concat(result);
        } catch (Exception ex) {
            throw new SerializationError(ex.getMessage());
        }
    }

    @Override
    public List<Byte> toByteList() throws SerializationError {
        return BytesHelper.toList(this.toBytes());
    }

    private byte[] serializeField(List<String> field) {
        byte[] result = BytesHelper.toBytes((short)field.size());
        for (String item : field) {
            byte[] bytes = Base58.decode(item);
            byte[] len = BytesHelper.toBytes((short)bytes.length);
            result = BytesHelper.concat(new byte[][]{result, len, bytes});
        }
        return result;
    }
}
