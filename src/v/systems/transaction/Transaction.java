package v.systems.transaction;

public abstract class Transaction {
    protected String id;
    protected Byte type;
    protected Long timestamp;
    protected Integer height;
    protected String status;

    // According to "Cold and Hot Wallet Interaction Specification 2.0"
    // https://github.com/virtualeconomy/rfcs/blob/master/text/0003-wallet-interaction-specification-2.md
    public static int getColdSignAPIVersion(Long amount) {
        if (amount % 100 == 0) {
            return 1;
        }
        return amount > 9007199254740991L ? 2 : 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte transactionType) {
        this.type = transactionType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
