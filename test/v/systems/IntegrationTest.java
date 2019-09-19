package v.systems;

import org.bitcoinj.core.Base58;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import v.systems.contract.ContractType;
import v.systems.contract.TokenBalance;
import v.systems.transaction.*;
import v.systems.type.NetworkType;

public class IntegrationTest {

    private Blockchain chain;
    private Account acc;
    private String recipient;

    @Before
    public void setUp() {
        chain = new Blockchain(TestConfig.NETWORK, TestConfig.NODE_ADDRESS);
        acc = new Account(TestConfig.NETWORK, TestConfig.SEED, TestConfig.ACCOUNT_INDEX);
        if (TestConfig.NETWORK == NetworkType.Testnet) {
            recipient = "AU83FKKzTYCue5ZQPweCzJ68dQE4HtdMv5U";
        } else {
            recipient = "AR9fSggvCcE2ixUA9s2Q6a8gbaccX1Jgtxk";
        }
    }

    @Test
    public void payment() throws Exception {
        Long balance = acc.getBalance(chain);
        if (balance < 2 * Blockchain.V_UNITY) {
            System.out.println("Skip payment test. The test account should have more than 2 VSYS. ");
            return;
        }
        Long amount = (long)(0.1 * Blockchain.V_UNITY);
        String attachment = Base58.encode("This payment is created by SDK".getBytes());
        PaymentTransaction tx = TransactionFactory.buildPaymentTx(recipient, amount, attachment);
        Transaction result = acc.sendTransaction(chain, tx);
        String txId =result.getId();
        Thread.sleep(TestConfig.BLOCK_WAIT_TIME);
        result = chain.getTransactionById(txId);
        Assert.assertEquals(txId, result.getId());
        Assert.assertEquals(tx.getType(), result.getType());
    }

    @Test
    public void lease() throws Exception {
        Long balance = acc.getBalance(chain);
        if (balance < 2 * Blockchain.V_UNITY) {
            System.out.println("Skip lease test. The test account should have more than 2 VSYS. ");
            return;
        }
        Long amount = (long)(0.9 * Blockchain.V_UNITY);
        LeaseTransaction tx = TransactionFactory.buildLeaseTx(recipient, amount);
        Transaction result;
        result = acc.sendTransaction(chain, tx);
        String txId =result.getId();
        Thread.sleep(TestConfig.BLOCK_WAIT_TIME);
        result = chain.getTransactionById(txId);
        Assert.assertEquals(txId, result.getId());
        Assert.assertEquals(tx.getType(), result.getType());
        LeaseCancelTransaction cancelTx = TransactionFactory.buildCancelLeasingTx(txId);
        result = acc.sendTransaction(chain, cancelTx);
        txId =result.getId();
        Thread.sleep(TestConfig.BLOCK_WAIT_TIME);
        result = chain.getTransactionById(txId);
        Assert.assertEquals(txId, result.getId());
        Assert.assertEquals(cancelTx.getType(), result.getType());
    }

    @Test
    public void registerToken() throws Exception {
        Long balance = acc.getBalance(chain);
        if (balance < 100 * Blockchain.V_UNITY) {
            System.out.println("Skip create token test. The test account should have more than 100 VSYS. ");
            return;
        }
        Long unity = 100000000L;
        RegisterContractTransaction tx = TransactionFactory.buildRegisterTokenTx(
                ContractType.Token,
                21000000 * unity,
                unity,
                "This Token is created by SDK",
                "This Contract is created by SDK");
        Transaction result = acc.sendTransaction(chain, tx);
        String txId =result.getId();
        Thread.sleep(TestConfig.BLOCK_WAIT_TIME);
        result = chain.getTransactionById(txId);
        Assert.assertEquals(txId, result.getId());
        Assert.assertEquals(tx.getType(), result.getType());
    }

    @Test
    public void sendToken() throws Exception {
        Long vBalance = acc.getBalance(chain);
        if (vBalance < 2 * Blockchain.V_UNITY) {
            System.out.println("Skip send token test. The test account should have more than 2 VSYS. ");
            return;
        }
        String tokenId = "TWscu6rbRF2PEsnY1bRky4aKxxKTzn69WMFLFdLxK";
        Long amount = 1000L;
        TokenBalance balance = chain.getTokenBalance(acc.getAddress(), tokenId);
        if (balance.getBalance() < amount) {
            System.out.println("Skip send token test. Insufficient Token");
            return;
        }
        String attachment = Base58.encode("Test Send Token By SDK".getBytes());
        ContractType type = chain.getContractTypeByTokenId(tokenId);
        ExecuteContractFunctionTransaction tx = TransactionFactory.buildSendTokenTx(tokenId, type,recipient, amount, attachment);
        Transaction result = acc.sendTransaction(chain, tx);
        String txId =result.getId();
        Thread.sleep(TestConfig.BLOCK_WAIT_TIME);
        result = chain.getTransactionById(txId);
        Assert.assertEquals(txId, result.getId());
        Assert.assertEquals(tx.getType(), result.getType());
        TokenBalance balance2 = chain.getTokenBalance(acc.getAddress(), tokenId);
        Assert.assertEquals(balance2.getBalance().longValue(), balance.getBalance() - amount);
    }
}