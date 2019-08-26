package v.systems.transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.bitcoinj.core.Base58;
import v.systems.entity.Proof;
import v.systems.error.SerializationError;
import v.systems.serialization.JsonSerializable;
import v.systems.type.NetworkType;
import v.systems.utils.Hash;

import java.util.ArrayList;

public abstract class ProvenTransaction extends BytesSerializableTransaction implements JsonSerializable {
    @Getter
    @Setter
    protected ArrayList<Proof> proofs;
    @Getter
    @Setter
    protected Long feeCharged;
    @Getter
    @Setter
    protected Short feeScale;
    @Getter
    @Setter
    protected Long fee;

    @Override
    public String getId() {
        if (id == null) {
            try {
                byte[] idBytes = Hash.blake2b(this.toBytes());
                id = Base58.encode(idBytes);
            } catch (SerializationError serializationError) {
                return null;
            }
        }
        return id;
    }

    @Override
    public JsonElement toAPIRequestJson(String publicKey, String signature) {
        JsonObject json = new JsonObject();
        json.addProperty("timestamp", this.timestamp);
        json.addProperty("fee", this.fee);
        json.addProperty("feeScale", this.feeScale);
        json.addProperty("senderPublicKey", publicKey);
        json.addProperty("signature", signature);
        return json;
    }

    @Override
    public JsonElement toColdSignJson(String publicKey, NetworkType type) {
        return toColdSignJson(publicKey, type,1);
    }

    public JsonElement toColdSignJson(String publicKey, NetworkType type, int ApiVersion) {
        JsonObject json = new JsonObject();
        json.addProperty("protocol", "v.systems");
        json.addProperty("api", ApiVersion);
        json.addProperty("opc", "transaction");
        json.addProperty("transactionType", this.type);
        json.addProperty("senderPublicKey", publicKey);
        json.addProperty("fee", this.fee);
        json.addProperty("feeScale", this.feeScale);
        json.addProperty("timestamp", this.timestamp);
        return json;
    }

    // According to "Cold and Hot Wallet Interaction Specification 2.0"
    // https://github.com/virtualeconomy/rfcs/blob/master/text/0003-wallet-interaction-specification-2.md
    public static int getColdSignAPIVersion(Long amount) {
        if (amount % 100 == 0) {
            return 1;
        }
        return amount > 9007199254740991L ? 2 : 1;
    }
}
