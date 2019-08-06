package v.systems.type;

public enum TransactionType {
    Payment(2),
    Lease(3),
    LeaseCancel(4),
    Minting(5),
    ContendSlot(6),
    ReleaseSlot(7);

    private final byte typeId;

    private TransactionType(int id) {
        typeId = (byte) id;
    }

    public boolean equals(Integer id) {
        return id != null && id.equals(typeId);
    }

    public final byte getTypeId() {
        return typeId;
    }

    public static TransactionType parse(int id) {
        switch (id) {
            case 2:
                return Payment;
            case 3:
                return Lease;
            case 4:
                return LeaseCancel;
            case 5:
                return Minting;
        }
        return null;
    }
}
