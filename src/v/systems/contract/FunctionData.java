package v.systems.contract;

import v.systems.error.SerializationError;
import v.systems.serialization.BytesSerializable;
import v.systems.utils.BytesHelper;

import java.util.ArrayList;
import java.util.List;

public class FunctionData extends ArrayList<DataEntry> implements BytesSerializable {
    @Override
    public byte[] toBytes() throws SerializationError {
        byte[] result = BytesHelper.toBytes((short)this.size());
        for (DataEntry item : this) {
            byte[] bytes = item.toBytes();
            result = BytesHelper.concat(result, bytes);
        }
        return result;
    }

    @Override
    public List<Byte> toByteList() throws SerializationError {
        return BytesHelper.toList(this.toBytes());
    }
}
