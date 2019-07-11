package v.systems.utils;

import org.bitcoinj.core.Base58;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BytesHelper {
    public static byte[] toBytes(Long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static byte[] toBytes(Integer x) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(x);
        return buffer.array();
    }

    public static byte[] toBytes(Short x) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(x);
        return buffer.array();
    }

    public static byte[] toBytes(Byte x) {
        return new byte[]{ x };
    }

    public static byte[] toBytes(String x) {
        return x.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] serializeBase58(String base58Str) {
        return Base58.decode(base58Str);
    }

    public static byte[] serializeBase58WithSize(String base58Str, int byteLengthOfSize) {
        byte[] b58decode = Base58.decode(base58Str);
        byte[] intSizeBytes = toBytes(b58decode.length);
        byte[] sizeBytes = new byte[byteLengthOfSize];
        for (int i = 1; i <= byteLengthOfSize; i++) {
            int destOffset = byteLengthOfSize - i;
            int srcOffset = Integer.BYTES - i;
            sizeBytes[destOffset] = srcOffset >= 0 ? intSizeBytes[srcOffset] : 0;
        }
        return concat(sizeBytes, b58decode);
    }

    public static byte[] toBytes(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0 ; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x ", b));
        }
        return sb.toString();
    }

    public static String toHex(List<Byte> bytes) {
        StringBuilder sb = new StringBuilder();
        for (Byte b : bytes) {
            sb.append(String.format("%02x ", b));
        }
        return sb.toString();
    }

    public static byte[] concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
