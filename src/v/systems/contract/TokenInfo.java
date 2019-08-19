package v.systems.contract;

import org.bitcoinj.core.Base58;
import v.systems.utils.BytesHelper;
import v.systems.utils.Hash;

import java.util.Arrays;

public class TokenInfo {
    private String tokenId;
    private String contractId;
    private Long max;
    private Long total;
    private Long unity;
    private String description;

    public static String getContractId(String tokenId) {
        byte[] tokenIdBytes = Base58.decode(tokenId);
        tokenIdBytes[0] = 6;
        byte[] tokenIdBytesWithoutCheckSum = Arrays.copyOfRange(tokenIdBytes, 0, tokenIdBytes.length-8);
        byte[] fullCheckSum = Hash.secureHash(tokenIdBytesWithoutCheckSum);
        byte[] checkSum = Arrays.copyOfRange(fullCheckSum, 0, 4);
        byte[] contractIdBytes = BytesHelper.concat(tokenIdBytesWithoutCheckSum, checkSum);
        return Base58.encode(contractIdBytes);
    }

    public static String getTokenId(String contractId, int tokenIndex) {
        byte[] contractIdBytes = Base58.decode(contractId);
        contractIdBytes[0] = (byte) 132;
        byte[] contractIdBytesWithoutCheckSum = Arrays.copyOfRange(contractIdBytes, 0, contractIdBytes.length-4);
        byte[] tokenIndexBytes = BytesHelper.toBytes(tokenIndex);
        contractIdBytesWithoutCheckSum = BytesHelper.concat(contractIdBytesWithoutCheckSum, tokenIndexBytes);
        byte[] fullCheckSum = Hash.secureHash(contractIdBytesWithoutCheckSum);
        byte[] checkSum = Arrays.copyOfRange(fullCheckSum, 0, 4);
        byte[] tokenIdBytes = BytesHelper.concat(contractIdBytesWithoutCheckSum, checkSum);
        return Base58.encode(tokenIdBytes);
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getUnity() {
        return unity;
    }

    public void setUnity(Long unity) {
        this.unity = unity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
