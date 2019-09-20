package v.systems.transaction;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MintingTransaction extends BasicTransaction {
    protected String recipient;
    protected Long amount;
}
