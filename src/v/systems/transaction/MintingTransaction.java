package v.systems.transaction;

import lombok.Data;

@Data
public class MintingTransaction extends BasicTransaction {
    protected String recipient;
    protected Long amount;
}
