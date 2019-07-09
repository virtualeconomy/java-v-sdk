package v.systems.serialization;

import v.systems.error.SerializationError;

import java.util.List;

public interface BytesSerializable {
    byte[] toBytes() throws SerializationError;
    List<Byte> toByteList() throws SerializationError;
}
