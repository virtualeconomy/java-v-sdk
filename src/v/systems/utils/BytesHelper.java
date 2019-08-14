package v.systems.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    public static byte[] toBytes(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0 ; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    public static List<Byte> toList(byte[] bytes) {
        List<Byte> list = new ArrayList<Byte>();
        for (byte b : bytes) {
            list.add(b);
        }
        return list;
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

    public static byte[] concat(byte[][] arr) {
        byte[] result = new byte[0];
        for (byte[] bs : arr) {
            result = concat(result, bs);
        }
        return result;
    }
}
