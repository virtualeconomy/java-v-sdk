package v.systems;

import org.junit.*;
import v.systems.contract.*;
import v.systems.entity.BalanceDetail;
import v.systems.entity.Block;
import v.systems.entity.SlotInfo;
import v.systems.error.ApiError;
import v.systems.error.SerializationError;
import v.systems.transaction.*;
import v.systems.type.NetworkType;
import v.systems.utils.BytesHelper;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class BlockchainTest {

    private Blockchain defaultChain;
    private Blockchain testnetChain;

    @Before
    public void setUp() {
        defaultChain = new Blockchain(TestConfig.NETWORK, TestConfig.NODE_ADDRESS);
        testnetChain = new Blockchain(NetworkType.Testnet, "http://test.v.systems:9922");
    }

    @Test
    public void getBalance() {
        String testAddress = "AUCJE7djDBbFrgeKRZ4UMREujAeqnD6L6Ph";
        try {
            Assert.assertTrue(testnetChain.getBalance(testAddress) > 0);
        } catch (Exception e) {
            Assert.fail("Failed to get balance on Blockchain.");
        }
    }

    @Test
    public void getBalanceDetail() {
        String testAddress = "AUCJE7djDBbFrgeKRZ4UMREujAeqnD6L6Ph";
        BalanceDetail balance = null;
        try {
            balance = testnetChain.getBalanceDetail(testAddress);
        } catch (Exception e) {
            Assert.fail("Failed to get balance detail on Blockchain.");
        }
        Assert.assertNotNull(balance);
        Assert.assertTrue(balance.getHeight() > 0);
        Assert.assertTrue(balance.getMintingAverage() > 0);
        Assert.assertTrue(balance.getEffective() > 0);
        Assert.assertTrue(balance.getAvailable() > 0);
        Assert.assertTrue(balance.getRegular() > 0);
        Assert.assertEquals(testAddress, balance.getAddress());
    }

    @Test
    public void getHeight() {
        try {
            Assert.assertTrue(defaultChain.getHeight() > 0);
        } catch (Exception e) {
            Assert.fail("Failed to get height of Blockchain.");
        }
    }

    @Test
    public void getLastBlock() {
        Block block = null;
        try {
            block = defaultChain.getLastBlock();
        } catch (Exception e) {
            Assert.fail("Failed to get Last Block on Blockchain.");
        }
        Assert.assertNotNull(block);
        Assert.assertTrue(block.getHeight() > 0);
        Assert.assertNotNull(block.getTransactions());
        Assert.assertTrue(block.getTransactions().size() >= 1);
    }

    @Test
    public void getBlockByHeight() {
        int height = 100;
        Block block = null;
        try {
            block = defaultChain.getBlockByHeight(height);
        } catch (Exception e) {
            Assert.fail("Failed to get Block on Blockchain.");
        }
        Assert.assertNotNull(block);
        Assert.assertNotNull(block.getHeight());
        Assert.assertEquals(height, block.getHeight().intValue());
        Assert.assertNotNull(block.getTransactions());
        Assert.assertTrue(block.getTransactions().size() >= 1);
    }

    @Test
    public void getSlotInfo() {
        SlotInfo slot = null;
        try {
            slot = testnetChain.getSlotInfo(0);
        } catch (Exception e) {
            Assert.fail("Failed to get Slot Info on Blockchain.");
        }
        Assert.assertEquals("ATxpELPa3yhE5h4XELxtPrW9TfXPrmYE7ze", slot.getAddress());
        Assert.assertTrue(slot.getMintingAverageBalance() > 0);
    }

    @Test
    public void getAllSlotInfo() {
        List<SlotInfo> allSlots = null;
        try {
            allSlots = defaultChain.getAllSlotInfo();
        } catch (Exception e) {
            Assert.fail("Failed to get all Slot Info on Blockchain.");
        }
        for (int i=0; i < 60; i++) {
            SlotInfo slot = allSlots.get(i);
            Assert.assertNotNull(slot.getAddress());
            if (slot.getAddress().equals("None")) {
                Assert.assertEquals(0, slot.getMintingAverageBalance().intValue());
            } else {
                Assert.assertTrue(slot.getMintingAverageBalance() > 0);
            }
        }
    }

    @Test
    public void getTokenInfo() {
        String tokenId = "TWscu6rbRF2PEsnY1bRky4aKxxKTzn69WMFLFdLxK";
        TokenInfo info = null;
        try {
            info = testnetChain.getTokenInfo(tokenId);
        } catch (Exception e) {
            Assert.fail("Failed to get Token Info on Blockchain.");
        }
        Assert.assertNotNull(info);
        Assert.assertTrue(info.getMax() > 0);
        Assert.assertTrue(info.getTotal() > 0);
        Assert.assertTrue(info.getUnity() > 0);
        String contractId = info.getContractId();
        int tokenIndex = 0;
        String actual = TokenInfo.getTokenId(contractId, tokenIndex);
        Assert.assertEquals(tokenId, actual);
    }

    @Test
    public void getTokenBalance() {
        String tokenId = "TWscu6rbRF2PEsnY1bRky4aKxxKTzn69WMFLFdLxK";
        String testAddress = "AUCJE7djDBbFrgeKRZ4UMREujAeqnD6L6Ph";
        TokenBalance balance = null;
        try {
            balance = testnetChain.getTokenBalance(testAddress, tokenId);
        } catch (Exception e) {
            Assert.fail("Failed to get balance on Blockchain.");
        }
        Assert.assertTrue(balance.getBalance() > 0);
        Assert.assertTrue(balance.getUnity() > 0);
        Assert.assertEquals(testAddress, balance.getAddress());
        Assert.assertEquals(tokenId, balance.getTokenId());
    }

    @Test
    public void getContractInfo() {
        String contractId = "CEtMCm5AdjTb83ZapbfLpKXDwrd1qCLuv4C";
        ContractInfo info = null;
        try {
            info = testnetChain.getContractInfo(contractId);
        } catch (Exception e) {
            Assert.fail("Failed to get Contract Info on Blockchain.");
        }
        Assert.assertNotNull(info.getTransactionId());
        Assert.assertEquals(ContractType.TokenWithSplit, info.getContractType());
    }

    @Test
    public void getContractContent() throws SerializationError {
        String[] testContractIdList = {"CEsHcSQdNbjHv9q8sPaEGKyDCJ243s6JYoQ", "CEtMCm5AdjTb83ZapbfLpKXDwrd1qCLuv4C"};
        for (String contractId : testContractIdList) {
            int tokenIndex = 0;
            String tokenId = TokenInfo.getTokenId(contractId, tokenIndex);
            Contract actualContract = null;
            ContractType type = null;
            try {
                actualContract = testnetChain.getContractContent(contractId);
                type = testnetChain.getContractTypeByTokenId(tokenId);
            } catch (Exception e) {
                Assert.fail("Failed to get Contract Content for ID: " + contractId);
            }
            Contract expectContract = ContractFactory.createToken(type);
            Assert.assertNotNull(expectContract);
            String expectBase58 = BytesHelper.toHex(expectContract.toBytes());
            String actualBase58 = BytesHelper.toHex(actualContract.toBytes());
            Assert.assertEquals(expectBase58, actualBase58);
        }
    }

    @Ignore
    @Test
    public void getActiveLeaseTransactions() {
        String testAddress = "AUCJE7djDBbFrgeKRZ4UMREujAeqnD6L6Ph";
        List<Transaction> txList = null;
        defaultChain.setApiKey(TestConfig.API_KEY);
        try {
            txList = defaultChain.getActiveLeaseTransactions(testAddress);
        } catch (Exception e) {
            Assert.fail("Failed to get Active Lease Transactions on Blockchain.");
        }
        for (Transaction tx : txList) {
            if (!(tx instanceof LeaseTransaction) && !(tx instanceof LeaseCancelTransaction)) {
                Assert.fail("Unexpected Transaction Type found! TX ID=" + tx.getId());
            }
        }
    }

    @Test
    public void getTxId() {
        ProvenTransaction tx = TransactionFactory.buildPaymentTx("AU83FKKzTYCue5ZQPweCzJ68dQE4HtdMv5U", 100000000L,"2bNcNL6J2DuM7GUo5", 1563936223688000000L);
        String actualTxId = tx.getId();
        Assert.assertEquals("5ikN8zQGTnZdtPB2c4P4LM7hAmJPgQ1C8oTejQhNXeuj", actualTxId);
    }

    @Test
    public void getTransactionById() {
        Transaction result;
        String txId = "7czCugc1VtrM1gZjm3Zpsw8rtP5zLeSQVuAhi7ohHrvY";
        try {
            result = testnetChain.getTransactionById(txId);
            Assert.assertEquals("Success", result.getStatus());
        } catch (Exception ex) {
            Assert.fail("Failed to get Transaction by Id " + txId);
        }
        try {
            testnetChain.getTransactionById("asds");
            Assert.fail("Transaction should not in blockchain");
        } catch (ApiError ex) {
            Assert.assertEquals("Transaction is not in blockchain", ex.getMessage());
        } catch (IOException ex) {
            Assert.fail("Failed to get Transaction on Blockchain.");
        }
    }
}