package v.systems.contract;

import org.bitcoinj.core.Base58;
import v.systems.type.DataType;

public class InfoData {
    private String data;
    private String type;
    private String name;

    public DataEntry toDataEntry() {
        DataEntry obj = new DataEntry();
        obj.setData(Base58.decode(data));
        obj.setType(DataType.parse(type));
        return obj;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
