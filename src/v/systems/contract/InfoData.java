package v.systems.contract;

import lombok.Data;
import org.bitcoinj.core.Base58;
import v.systems.type.DataType;

@Data
public class InfoData {
    private String data;
    private String type;
    private String name;

    public DataEntry toDataEntry() {
        return DataEntry.builder()
                .data(Base58.decode(data))
                .type(DataType.parse(type))
                .build();
    }
}
