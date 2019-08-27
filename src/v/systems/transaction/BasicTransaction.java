package v.systems.transaction;

import lombok.Data;

@Data
public abstract class BasicTransaction implements Transaction {

    protected String id;
    protected Byte type;
    protected Long timestamp;
    protected Integer height;
    protected String status;

}
