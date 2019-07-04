package v.systems.transaction;

import org.bitcoinj.core.Base58;
import v.systems.entity.Proof;
import v.systems.error.SerializationError;
import v.systems.serialization.BytesSerializable;
import v.systems.serialization.JsonSerializable;
import v.systems.type.Base58Field;
import v.systems.utils.BytesHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class ProvenTransaction extends Transaction implements BytesSerializable {
    protected ArrayList<Proof> proofs;
    protected Long feeCharged;
    protected Short feeScale;
    protected Long fee;

    protected abstract String[] getSerializedFields();

    public List<Byte> toBytes() throws SerializationError {
        List<Byte> result = new ArrayList<Byte>();
        for (String fieldName : getSerializedFields()) {
            Field field = null;
            Object value = null;
            Class objClass = this.getClass();
            while (objClass != null && field == null) {
                try {
                    field = objClass.getDeclaredField(fieldName);
                    value = field.get(this);
                } catch (NoSuchFieldException e) {
                    objClass = objClass.getSuperclass();
                } catch (IllegalAccessException e) {
                    throw new SerializationError(String.format("Cannot access field '%s'", fieldName));
                }
            }
            if (field == null) {
                throw new SerializationError(String.format("Cannot find field '%s'", fieldName));
            }
            if (value == null) {
                throw new SerializationError(String.format("The value of field '%s' is null", fieldName));
            }
            byte[] bytesArray;
            if (String.class.isAssignableFrom(field.getType())) {
                Base58Field b58field = field.getAnnotation(Base58Field.class);
                if (b58field != null) {
                    bytesArray = serializeBase58(value.toString(), b58field);
                } else {
                    bytesArray = BytesHelper.toBytes(value.toString());
                }
            } else if (Long.class.isAssignableFrom(field.getType())){
                bytesArray = BytesHelper.toBytes((Long)value);
            } else if (Integer.class.isAssignableFrom(field.getType())){
                bytesArray = BytesHelper.toBytes((Integer)value);
            } else if (Short.class.isAssignableFrom(field.getType())){
                bytesArray = BytesHelper.toBytes((Short)value);
            } else if (Byte.class.isAssignableFrom(field.getType())){
                bytesArray = BytesHelper.toBytes((Byte)value);
            } else {
                throw new SerializationError("Unable to Serialized Field: " + fieldName);
            }
            for (byte b : bytesArray) {
                result.add(b);
            }
        }
        return result;
    }

    private byte[] serializeBase58(String b58String, Base58Field b58field) {
        byte[] b58decode = Base58.decode(b58String);
        if (!b58field.isFixedLength()) {
            Short len = (short)b58decode.length;
            byte[] lenBytes = BytesHelper.toBytes(len);
            return BytesHelper.concat(lenBytes, b58decode);
        } else {
            return b58decode;
        }
    }

    public ArrayList<Proof> getProofs() {
        return proofs;
    }

    public void setProofs(ArrayList<Proof> proofs) {
        this.proofs = proofs;
    }

    public Long getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(Long feeCharged) {
        this.feeCharged = feeCharged;
    }

    public Short getFeeScale() {
        return feeScale;
    }

    public void setFeeScale(Short feeScale) {
        this.feeScale = feeScale;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
}
