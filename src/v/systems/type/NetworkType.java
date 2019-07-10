package v.systems.type;

public enum NetworkType {

    Testnet('T'),
    Mainnet('M');

    private final byte networkByte;

    private NetworkType(char s) {
        networkByte = (byte)s;
    }

    public byte toByte() {
        return this.networkByte;
    }
}
