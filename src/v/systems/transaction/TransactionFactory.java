package v.systems.transaction;

import org.bitcoinj.core.Base58;
import v.systems.contract.*;
import v.systems.error.SerializationError;

import java.util.Date;

public class TransactionFactory {

    public static final long DEFAULT_TX_FEE = 10000000L;
    public static final long CONTEND_TX_FEE = 50000 * 100000000L;
    public static final long REG_CONTRACT_TX_FEE = 100 * 100000000L;
    public static final long EXEC_CONTRACT_TX_FEE = 30000000L;
    public static final short DEFAULT_FEE_SCALE = 100;

    private static final short SEND_TOKEN_FUNC_INDEX = 3;
    private static final short SEND_TOKEN_FUNC_INDEX_SPLIT = 4;

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

    public static RegisterContractTransaction buildRegisterTokenTx(ContractType type, Long max, Long unity, String tokenDescription, String contractDescription) throws SerializationError {
        Contract contract = ContractFactory.createToken(type);
        FunctionData data = new FunctionData();
        data.add(DataEntry.amount(max));
        data.add(DataEntry.amount(unity));
        data.add(DataEntry.shortText(tokenDescription));
        return buildRegisterContractTx(contract, data, contractDescription);
    }

    public static RegisterContractTransaction buildRegisterContractTx(Contract contract, FunctionData data, String description) throws SerializationError {
        return buildRegisterContractTx(contract, data, description, getCurrentTime());
    }

    public static RegisterContractTransaction buildRegisterContractTx(Contract contract, FunctionData data, String description, Long timestamp) throws SerializationError {
        RegisterContractTransaction tx = new RegisterContractTransaction();
        tx.setContract(contract);
        tx.setInitData(Base58.encode(data.toBytes()));
        tx.setDescription(description);
        tx.setFee(REG_CONTRACT_TX_FEE);
        tx.setTimestamp(timestamp);
        setCommonField(tx);
        return tx;
    }

    public static ExecuteContractFunctionTransaction buildSendTokenTx(String tokenId, ContractType type, String recipient, Long amount) throws SerializationError {
        return buildSendTokenTx(tokenId, type, recipient, amount, "");
    }

    public static ExecuteContractFunctionTransaction buildSendTokenTx(String tokenId, ContractType type, String recipient, Long amount, String attachment) throws SerializationError {
        short functionIndex = type == ContractType.TokenWithSplit ? SEND_TOKEN_FUNC_INDEX_SPLIT : SEND_TOKEN_FUNC_INDEX;
        FunctionData data = new FunctionData();
        data.add(DataEntry.address(recipient));
        data.add(DataEntry.amount(amount));
        String contractId = TokenInfo.getContractId(tokenId);
        return buildExecContractFuncTx(contractId, functionIndex, data, attachment);
    }

    public static ExecuteContractFunctionTransaction buildExecContractFuncTx(String contractId, Short functionIndex, FunctionData data) throws SerializationError {
        return buildExecContractFuncTx(contractId, functionIndex, data, "");
    }

    public static ExecuteContractFunctionTransaction buildExecContractFuncTx(String contractId, Short functionIndex, FunctionData data, String attachment) throws SerializationError {
        return buildExecContractFuncTx(contractId, functionIndex, data, attachment, getCurrentTime());
    }

    public static ExecuteContractFunctionTransaction buildExecContractFuncTx(String contractId, Short functionIndex, FunctionData data, String attachment, Long timestamp) throws SerializationError {
        ExecuteContractFunctionTransaction tx = new ExecuteContractFunctionTransaction();
        tx.setContractId(contractId);
        tx.setFunctionIndex(functionIndex);
        tx.setFunctionData(Base58.encode(data.toBytes()));
        tx.setAttachment(attachment);
        tx.setFee(EXEC_CONTRACT_TX_FEE);
        tx.setTimestamp(timestamp);
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
