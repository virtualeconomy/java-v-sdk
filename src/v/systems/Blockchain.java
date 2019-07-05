package v.systems;

import com.google.gson.*;
import v.systems.entity.Balance;
import v.systems.entity.BalanceDetail;
import v.systems.entity.Block;
import v.systems.error.ApiError;
import v.systems.transaction.Transaction;
import v.systems.transaction.TransactionParser;
import v.systems.type.NetworkType;
import v.systems.utils.HttpClient;
import v.systems.utils.JsonHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private NetworkType network;
    private String nodeUrl;
    private Gson gson;
    private JsonParser parser;

    public Blockchain(NetworkType network, String nodeUrl) {
        this.network = network;
        this.nodeUrl = nodeUrl;
        gson = JsonHelper.getGsonInstance();
        parser = JsonHelper.getParserInstance();
    }

    public Long getBalance(String address) throws IOException, ApiError {
        String url = String.format("%s/addresses/balance/%s", nodeUrl, address);
        Balance balance = this.callChainAPI(url, Balance.class);
        return balance.getBalance();
    }

    public BalanceDetail getBalanceDetail(String address) throws IOException, ApiError {
        String url = String.format("%s/addresses/balance/details/%s", nodeUrl, address);
        return this.callChainAPI(url, BalanceDetail.class);
    }

    public List<Transaction> getTransactionHistory(String address, int num) throws IOException, ApiError {
        String url = String.format("%s/transactions/address/%s/limit/%d", nodeUrl, address, num);
        String json = HttpClient.get(url);
        List<Transaction> result = new ArrayList<Transaction>();
        try {
            JsonElement jsonElement = parser.parse(json);
            if (!jsonElement.isJsonArray()) {
                throw new ApiError(json);
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray.size() == 0) {
                return result;
            }
            jsonElement = jsonArray.get(0);
            if (!jsonElement.isJsonArray()) {
                throw new ApiError(json);
            }
            jsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                Transaction tx = TransactionParser.parse(jsonArray.get(i));
                result.add(tx);
            }

        } catch (JsonSyntaxException ex) {
            throw ApiError.fromJson(json);
        }
        return result;
    }

    public Transaction getTransactionById(String txId) throws IOException, ApiError {
        String url = String.format("%s/transactions/info/%s", nodeUrl, txId);
        String json = HttpClient.get(url);
        try {
            return TransactionParser.parse(json);
        } catch (JsonSyntaxException ex) {
            throw ApiError.fromJson(json);
        }
    }

    public Transaction getUnconfirmedTransactionById(String txId) throws IOException, ApiError {
        String url = String.format("%s/transactions/unconfirmed/info/%s", nodeUrl, txId);
        String json = HttpClient.get(url);
        try {
            return TransactionParser.parse(json);
        } catch (JsonSyntaxException ex) {
            throw ApiError.fromJson(json);
        }
    }

    public Integer getHeight() throws IOException, ApiError {
        String url = String.format("%s/blocks/height", nodeUrl);
        String json = HttpClient.get(url);
        try {
            JsonElement jsonElement = parser.parse(json);
            if (!jsonElement.isJsonObject()) {
                throw new ApiError(json);
            }
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            JsonElement heightElement = jsonObj.get("height");
            if (heightElement == null) {
                throw ApiError.fromJson(json);
            }
            return heightElement.getAsInt();
        } catch (JsonSyntaxException ex) {
            throw ApiError.fromJson(json);
        }
    }

    public Block getLastBlock() throws IOException, ApiError {
        String url = String.format("%s/blocks/last", nodeUrl);
        return this.callChainAPI(url, Block.class);
    }
    public Block getBlockByHeight(int height) throws IOException, ApiError  {
        String url = String.format("%s/blocks/at/%d", nodeUrl, height);
        return this.callChainAPI(url, Block.class);
    }

    //TODO: implement these functions later
    // getTokenInfo(String tokenId)
    // getTokenBalance(String address, String tokenId)
    // getContractInfo(String contractId)
    // getContractContent(String contractId)

    private <T> T callChainAPI(String url, Class<T> classType) throws IOException, ApiError {
        String json = HttpClient.get(url);
        try {
            return gson.fromJson(json, classType);
        } catch (JsonSyntaxException ex) {
            throw ApiError.fromJson(json);
        }
    }

    public NetworkType getNetwork() {
        return network;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }
}
