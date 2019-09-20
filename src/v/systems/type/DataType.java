package v.systems.type;

public enum DataType {
    PublicKey(1),
    Address(2),
    Amount(3),
    Int32(4),
    ShortText(5),
    ContractAccount(6);

    private final byte typeId;

    private DataType(int id) {
        typeId = (byte) id;
    }

    public boolean equals(Integer id) {
        return id != null && id.equals((int)typeId);
    }

    public final byte getTypeId() {
        return typeId;
    }

    public static DataType parse(int id) {
        switch (id) {
            case 1:
                return PublicKey;
            case 2:
                return Address;
            case 3:
                return Amount;
            case 4:
                return Int32;
            case 5:
                return ShortText;
            case 6:
                return ContractAccount;
        }
        return null;
    }

    public static DataType parse(String type) {
        switch (type) {
            case "PublicKey":
                return PublicKey;
            case "Address":
                return Address;
            case "Amount":
                return Amount;
            case "Int32":
                return Int32;
            case "ShortText":
                return ShortText;
            case "ContractAccount":
                return ContractAccount;
        }
        return null;
    }
}
