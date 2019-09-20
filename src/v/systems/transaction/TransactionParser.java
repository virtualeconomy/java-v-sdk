package v.systems.transaction;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import v.systems.type.TransactionType;
import v.systems.utils.JsonHelper;

public class TransactionParser {

    private static Gson gson = JsonHelper.getGsonInstance();
    private static JsonParser parser = JsonHelper.getParserInstance();

    public static Transaction parse(String json) throws JsonSyntaxException {
        JsonElement e = parser.parse(json);
        return parse(e);
    }

    public static Transaction parse(JsonElement json) throws JsonSyntaxException {
        Transaction tx = gson.fromJson(json, UnknownTransaction.class);
        TransactionType txType = TransactionType.parse(tx.getType());
        if (txType == null) {
            return tx;
        }
        switch (txType) {
            case Payment:
                return gson.fromJson(json, PaymentTransaction.class);
            case Lease:
                return gson.fromJson(json, LeaseTransaction.class);
            case LeaseCancel:
                return gson.fromJson(json, LeaseCancelTransaction.class);
            case Minting:
                return gson.fromJson(json, MintingTransaction.class);
            case ContendSlot:
                return gson.fromJson(json, ContendSlotTransaction.class);
            case ReleaseSlot:
                return gson.fromJson(json, ReleaseSlotTransaction.class);
            case RegisterContract:
                return gson.fromJson(json, RegisterContractTransaction.class);
            case ExecuteContractFunction:
                return gson.fromJson(json, ExecuteContractFunctionTransaction.class);
        }
        return tx;
    }
}
