package v.systems.contract;

import lombok.Data;
import org.bitcoinj.core.Base58;
import v.systems.error.SerializationError;
import v.systems.serialization.BytesSerializable;
import v.systems.utils.BytesHelper;

import java.util.List;

@Data
public class ContractTextual implements BytesSerializable {
    private String triggers;
    private String descriptors;
    private String stateVariables;

    @Override
    public byte[] toBytes() throws SerializationError {
        try {
            byte[] triggerBytes = Base58.decode(triggers);
            byte[] descriptorBytes = Base58.decode(descriptors);
            byte[] stateVarBytes = Base58.decode(stateVariables);
            byte[][] result = new byte[][]{
                    BytesHelper.toBytes((short)3),
                    BytesHelper.toBytes((short)triggerBytes.length),
                    triggerBytes,
                    BytesHelper.toBytes((short)descriptorBytes.length),
                    descriptorBytes,
                    BytesHelper.toBytes((short)stateVarBytes.length),
                    stateVarBytes
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
}
