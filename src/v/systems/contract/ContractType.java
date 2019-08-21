package v.systems.contract;

public enum ContractType {

    Token("TokenContract"),
    TokenWithSplit("TokenContractWithSplit");

    private final String type;

    private ContractType(String type) {
        this.type = type;
    }

    public static ContractType parse(String input) {
        switch (input) {
            case "TokenContract":
                return Token;
            case "TokenContractWithSplit":
                return TokenWithSplit;
            default:
                return null;
        }
    }

    public String toString() {
        return type;
    }
}
