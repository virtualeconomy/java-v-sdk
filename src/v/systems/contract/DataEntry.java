package v.systems.contract;

import org.bitcoinj.core.Base58;
import sun.jvm.hotspot.debugger.Address;
import v.systems.error.SerializationError;
import v.systems.serialization.BytesSerializable;
import v.systems.type.DataType;
import v.systems.utils.BytesHelper;

import java.util.List;

public class DataEntry implements BytesSerializable {
    public final int KEY_LENGTH = 32;
    public final int ADDRESS_LENGTH = 26;

    private DataType type;
    private byte[] data;

    @Override
    public byte[] toBytes() throws SerializationError {
        if (data == null) {
            throw new SerializationError("No data in DataEntry");
        }
        if (!verifyLength()) {
            throw new SerializationError("Invalid length of DataEntry");
        }
        byte[] header = { getType().getTypeId() };
        if (getType() == DataType.ShortText) {
            byte[] len = BytesHelper.toBytes((short) getData().length);
            header = BytesHelper.concat(header, len);
        }
        return BytesHelper.concat(header, getData());
    }

    @Override
    public List<Byte> toByteList() throws SerializationError {
        return BytesHelper.toList(this.toBytes());
    }

    public boolean verifyLength() {
        if (data == null) {
            return false;
        }
        switch (type) {
            case PublicKey:
                return data.length == KEY_LENGTH;
            case Address:
            case ContractAccount:
                return data.length == ADDRESS_LENGTH;
            case Amount:
                return data.length == Long.BYTES;
            case Int32:
                return data.length == Integer.BYTES;
            case ShortText:
                return true;
            default:
                return false;
        }
    }

    public boolean isAccountType() {
        return type == DataType.Address || type == DataType.ContractAccount;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public static DataEntry publicKey(String publicKey) {
        DataEntry dataEntry = new DataEntry();
        dataEntry.setType(DataType.PublicKey);
        dataEntry.setData(Base58.decode(publicKey));
        return dataEntry;
    }

    public static DataEntry address(String address) {
        DataEntry dataEntry = new DataEntry();
        dataEntry.setType(DataType.Address);
        dataEntry.setData(Base58.decode(address));
        return dataEntry;
    }

    public static DataEntry amount(Long amount) {
        DataEntry dataEntry = new DataEntry();
        dataEntry.setType(DataType.Amount);
        dataEntry.setData(BytesHelper.toBytes(amount));
        return dataEntry;
    }

    public static DataEntry int32(Integer int32) {
        DataEntry dataEntry = new DataEntry();
        dataEntry.setType(DataType.Int32);
        dataEntry.setData(BytesHelper.toBytes(int32));
        return dataEntry;
    }

    public static DataEntry shortText(String text) {
        DataEntry dataEntry = new DataEntry();
        dataEntry.setType(DataType.ShortText);
        byte[] data = BytesHelper.toBytes(text);
        byte[] len = BytesHelper.toBytes((short)data.length);
        dataEntry.setData(BytesHelper.concat(len, data));
        return dataEntry;
    }

    public static DataEntry contractAccount(String contractAccount) {
        DataEntry dataEntry = new DataEntry();
        dataEntry.setType(DataType.ContractAccount);
        dataEntry.setData(Base58.decode(contractAccount));
        return dataEntry;
    }
}
