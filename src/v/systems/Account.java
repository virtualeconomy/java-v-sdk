package v.systems;

import com.google.gson.JsonElement;
import org.bitcoinj.core.Base58;
import org.whispersystems.curve25519.Curve25519;
import org.whispersystems.curve25519.java.curve_sigs;
import v.systems.entity.BalanceDetail;
import v.systems.error.ApiError;
import v.systems.error.KeyError;
import v.systems.error.SerializationError;
import v.systems.serialization.BytesSerializable;
import v.systems.transaction.ProvenTransaction;
import v.systems.type.NetworkType;
import v.systems.type.TransactionType;
import v.systems.utils.Hash;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Account {
    private static final Curve25519 cipher = Curve25519.getInstance(Curve25519.BEST);
    private static final byte ADDR_VERSION = 5;

    private byte[] privateKey;
    private byte[] publicKey;
    private byte[] address;
    private NetworkType network;

    public Account(NetworkType network, String seed, Integer nonce) {
        this.network = network;
        if (nonce != null) {
            seed = nonce.toString() + seed;
        }
        byte[] seedBytes = seed.getBytes(StandardCharsets.UTF_8);
        byte[] accountSeed = Hash.secureHash(seedBytes, 0, seedBytes.length);
        byte[] hashedSeed = Hash.sha256(accountSeed, 0, accountSeed.length);
        privateKey = Arrays.copyOf(hashedSeed, 32);
        privateKey[0] &= 248;
        privateKey[31] &= 127;
        privateKey[31] |= 64;
        publicKey = new byte[32];
        curve_sigs.curve25519_keygen(publicKey, privateKey);
        address = getAddress(publicKey, network.toByte());
    }

    public Account(NetworkType network, String base58PrivateKey) {
        this.network = network;
        privateKey = Base58.decode(base58PrivateKey);
        publicKey = new byte[32];
        curve_sigs.curve25519_keygen(publicKey, privateKey);
        address = getAddress(publicKey, network.toByte());
    }

    public Account(NetworkType network, String base58PublicKey, String base58Address) {
        this.network = network;
        if (base58PublicKey != null) {
            publicKey = Base58.decode(base58PublicKey);
            address = getAddress(publicKey, network.toByte());
        } else {
            address = Base58.decode(base58Address);
        }
    }

    public ProvenTransaction sendTransaction(Blockchain chain, ProvenTransaction tx)
            throws SerializationError, KeyError, IOException, ApiError {
        TransactionType txType = TransactionType.parse(tx.getType());
        String signature = getSignature(tx);
        JsonElement json = tx.toAPIRequestJson(this.getPublicKey(), signature);
        return chain.sendTransaction(txType, json.toString());
    }

    public String getSignature(BytesSerializable tx) throws SerializationError, KeyError {
        return getSignature(tx.toBytes());
    }

    public String getSignature(byte[] bytes) throws KeyError {
        if (privateKey == null) {
            throw new KeyError("Cannot sign the context. No private key in account.");
        }
        return Base58.encode(cipher.calculateSignature(this.privateKey, bytes));
    }

    public Long getBalance(Blockchain chain) throws KeyError, IOException, ApiError {
        return chain.getBalance(this.getAddress());
    }

    public BalanceDetail getBalanceDetail(Blockchain chain) throws KeyError, IOException, ApiError {
        return chain.getBalanceDetail(this.getAddress());
    }

    public String getPrivateKey() throws KeyError {
        if (privateKey == null) {
            throw new KeyError("No private key in account.");
        }
        return Base58.encode(privateKey);
    }

    public String getPublicKey() throws KeyError {
        if (publicKey == null) {
            throw new KeyError("No public key in account.");
        }
        return Base58.encode(publicKey);
    }

    public String getAddress() throws KeyError {
        if (address == null) {
            if (publicKey == null) {
                throw new KeyError("No public key in account.");
            }
            address = getAddress(publicKey, network.toByte());
        }
        return Base58.encode(address);
    }

    public static String getAddress(String publicKey, byte networkByte) {
        return Base58.encode(getAddress(Base58.decode(publicKey), networkByte));
    }

    public static byte[] getAddress(byte[] publicKey, byte networkByte) {
        ByteBuffer buf = ByteBuffer.allocate(26);
        byte[] hash = Hash.secureHash(publicKey, 0, publicKey.length);
        buf.put(ADDR_VERSION).put(networkByte).put(hash, 0, 20);
        byte[] checksum = Hash.secureHash(buf.array(), 0, 22);
        buf.put(checksum, 0, 4);
        return buf.array();
    }
}
