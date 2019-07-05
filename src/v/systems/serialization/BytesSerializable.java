package v.systems.serialization;

import v.systems.error.SerializationError;

import java.util.List;

public interface BytesSerializable {
    List<Byte> toBytes() throws SerializationError;
}
