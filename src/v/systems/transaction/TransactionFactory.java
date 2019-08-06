package v.systems.transaction;

import java.util.Date;

public class TransactionFactory {

    public static final long DEFAULT_TX_FEE = 10000000L;
    public static final long CONTEND_TX_FEE = 50000 * 100000000L;
    public static final short DEFAULT_FEE_SCALE = 100;

    public static PaymentTransaction buildPaymentTx(String recipient, Long amount) {
        return buildPaymentTx(recipient, amount, "");
    }

    public static PaymentTransaction buildPaymentTx(String recipient, Long amount, String attachment) {
        return buildPaymentTx(recipient, amount, attachment, getCurrentTime());
    }

    public static PaymentTransaction buildPaymentTx(String recipient, Long amount, String attachment, Long timestamp) {
        PaymentTransaction tx = new PaymentTransaction();
        tx.setRecipient(recipient);
        tx.setAmount(amount);
        tx.setAttachment(attachment);
        tx.setTimestamp(timestamp);
        setCommonField(tx);
        return tx;
    }

    public static LeaseTransaction buildLeaseTx(String recipient, Long amount) {
        return buildLeaseTx(recipient, amount, getCurrentTime());
    }

    public static LeaseTransaction buildLeaseTx(String recipient, Long amount, Long timestamp) {
        LeaseTransaction tx = new LeaseTransaction();
        tx.setRecipient(recipient);
        tx.setAmount(amount);
        tx.setTimestamp(timestamp);
        setCommonField(tx);
        return tx;
    }

    public static LeaseCancelTransaction buildCancelLeasingTx(String leaseId) {
        return buildCancelLeasingTx(leaseId, getCurrentTime());
    }

    public static LeaseCancelTransaction buildCancelLeasingTx(String leaseId, Long timestamp) {
        LeaseCancelTransaction tx = new LeaseCancelTransaction();
        tx.setLeaseId(leaseId);
        tx.setTimestamp(timestamp);
        setCommonField(tx);
        return tx;
    }

    public static ContendSlotTransaction buildContendSlotTx(Integer slotId) {
        ContendSlotTransaction tx = new ContendSlotTransaction();
        tx.setSlotId(slotId);
        tx.setFee(CONTEND_TX_FEE);
        setCommonField(tx);
        return tx;
    }

    public static ReleaseSlotTransaction buildReleaseSlotTx(Integer slotId) {
        ReleaseSlotTransaction tx = new ReleaseSlotTransaction();
        tx.setSlotId(slotId);
        setCommonField(tx);
        return tx;
    }

    private static void setCommonField(ProvenTransaction tx) {
        if (tx.getTimestamp() == null) {
            tx.setTimestamp(getCurrentTime());
        }
        if (tx.getFee() == null) {
            tx.setFee(DEFAULT_TX_FEE);
        }
        tx.setFeeScale(DEFAULT_FEE_SCALE);
    }

    private static long getCurrentTime() {
        return new Date().getTime() * 1000000L; // to nano seconds
    }
}
