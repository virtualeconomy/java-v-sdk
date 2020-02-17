package v.systems;

import org.bitcoinj.core.Base58;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.whispersystems.curve25519.Curve25519;
import v.systems.entity.BalanceDetail;
import v.systems.entity.Result;
import v.systems.entity.SlotInfo;
import v.systems.error.KeyError;
import v.systems.error.VException;
import v.systems.transaction.*;
import v.systems.type.NetworkType;

import java.util.List;

import static org.junit.Assert.*;

public class AccountTest {

    private static final Curve25519 cipher = Curve25519.getInstance(Curve25519.BEST);
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
    public void createAccount() throws KeyError {
        String seed = "captain slight hurdle bless engage gallery senior wisdom uniform young pretty first glad jar claw";
        Account acc = new Account(NetworkType.Testnet, seed, 0);
        Assert.assertEquals("Private Key Generation is wrong", "5uS81kZNHFd1seHaoVoc7bqTRaAUEbtW2e84HrsHUHij", acc.getPrivateKey());
        Assert.assertEquals("Public Key Generation is wrong", "3NaHDL4hig5A9Dh3gLbycWnfxpPTBe4WgeAG5AgeXPbL", acc.getPublicKey());
        Assert.assertEquals("Address Generation is wrong", "AU9fRxUciwuG6JvdnPBPb8BJXvfDn8oxwtn", acc.getAddress());
    }

    @Test
    public void createAccountWithPrivateKey() throws KeyError {
        Account acc = new Account(NetworkType.Mainnet, "6xW5vo8vcG153QEJ7Rnam1x6QLJugrbyTjYN2txWEBVR");
        Assert.assertEquals("Public Key Generation is wrong", "EJqSddw7JRvTkNTPkddWCmboa7rbQD8f4LAqngfhb6Hw", acc.getPublicKey());
        Assert.assertEquals("Address Generation is wrong", "ARJGXkS7nxK3TaYMQxvWs7WahwR2NJMQFBW", acc.getAddress());

        Account acc2 = new Account(NetworkType.Mainnet, "4pc9Koxm3XPVEAx7Vp9TcdRgbtdsWUFye5Ng78D9Lkxh");
        Assert.assertEquals("Public Key Generation is wrong", "HQGZrkdzZiGkD9RwBhDXiZzNqRJiiWqvViJSxcyGnPhV", acc2.getPublicKey());
        Assert.assertEquals("Address Generation is wrong", "AR4U6sFGSzH8XCHHjMU8Y9PAU9j8khXxy5B", acc2.getAddress());

        Account acc3 = new Account(NetworkType.Testnet, "3ATdWjpXAfdhd6fCjGk8w7iQhezNz6DDNSELDa9B5KVC");
        Assert.assertEquals("Public Key Generation is wrong", "2Mnu6jZxedY4eJ75Xt27YwSjrpug5X5f5N31GnW6rfPG", acc3.getPublicKey());
        Assert.assertEquals("Address Generation is wrong", "AUC89jnZnmGeNp3gLBcBofNC3e4u3DV4fxp", acc3.getAddress());

        Account acc4 = new Account(NetworkType.Testnet, "G8mgPMSKCsxXxQRTU3npQyQW72sy3g8rMiTEKwsEHaSU");
        Assert.assertEquals("Public Key Generation is wrong", "3iBXXgXjwyXWZqyprCaQv9q66L4UzLji2Gv9sgX6nNoN", acc4.getPublicKey());
        Assert.assertEquals("Address Generation is wrong", "ATwncwd63jAqF6B2NH8Zs2giMHgzXPSBtBk", acc4.getAddress());
    }

    @Test
    public void getSignature() throws VException {
        Long amount = (long)(1.5 * Blockchain.V_UNITY);
        PaymentTransaction tx = TransactionFactory.buildPaymentTx("AU83FKKzTYCue5ZQPweCzJ68dQE4HtdMv5U", amount);
        byte[] context = tx.toBytes();
        String signature = acc.getSignature(context);
        acc.verifySignature(context, signature);
        byte[] signatureBytes = Base58.decode(signature);
        byte[] publicKeyBytes = Base58.decode(acc.getPublicKey());
        boolean isValid = cipher.verifySignature(publicKeyBytes, context, signatureBytes);
        Assert.assertTrue(isValid);
    }

    @Test
    public void getBalance() throws Exception {
        Long balance = acc.getBalance(chain);
        Assert.assertTrue(balance >= 0);
        BalanceDetail detail = acc.getBalanceDetail(chain);
        Assert.assertNotNull(detail);
        Assert.assertTrue(detail.getHeight() > 0);
        Assert.assertNotNull(detail.getRegular());
        Assert.assertNotNull(detail.getAvailable());
        Assert.assertNotNull(detail.getEffective());
        Assert.assertNotNull(detail.getMintingAverage());
        Assert.assertTrue(detail.getRegular() >= 0);
        Assert.assertTrue(detail.getAvailable() >= 0);
        Assert.assertTrue(detail.getEffective() >= 0);
        Assert.assertTrue(detail.getMintingAverage() >= 0);
    }

    @Test
    public void getTransactionHistory() throws Exception {
        List<Transaction> list = acc.getTransactionHistory(chain, 10);
        Assert.assertTrue(list.size() <= 10);
        for (Transaction tx : list) {
            if (tx instanceof UnknownTransaction) {
                Assert.fail("Cannot parse tx type for id: "+ tx.getId());
            }
        }
    }

    @Test
    public void getPrivateKey() throws Exception {
        String seed = "black vote metal exhaust stairs curtain tennis top surround carry bulk giant speed razor curious";
        Account acc = new Account(NetworkType.Testnet, seed, 0);
        Assert.assertEquals("Private Key Generation is wrong", "8Yoy9QL2sqggrM22VvHcRmAVzRr5h23FuDeFohyAU27B", acc.getPrivateKey());
        Account acc2 = new Account(NetworkType.Mainnet, seed, 1);
        Assert.assertEquals("Private Key Generation is wrong", "CtDEyCa5gWxX4XsRzENaSyLR2LDF3QnDXQNSEDZfgsx6", acc2.getPrivateKey());
    }

    @Test
    public void getPublicKey() throws Exception {
        String seed = "black vote metal exhaust stairs curtain tennis top surround carry bulk giant speed razor curious";
        Account acc = new Account(NetworkType.Testnet, seed, 0);
        Assert.assertEquals("Public Key Generation is wrong", "2cLDxAPJNWGGWAyHUFEnyoznhkf4QCEkcQrL5g2oEBCY", acc.getPublicKey());
        Account acc2 = new Account(NetworkType.Mainnet, seed, 1);
        Assert.assertEquals("Public Key Generation is wrong", "4iE3oZsGDmR36wDYukKSGM4V6dPrFT9GiqUv2TBt3nUX", acc2.getPublicKey());
    }

    @Test
    public void getAddress() throws Exception {
        String seed = "black vote metal exhaust stairs curtain tennis top surround carry bulk giant speed razor curious";
        Account acc = new Account(NetworkType.Testnet, seed, 0);
        Assert.assertEquals("Address Generation is wrong", "ATwEV7r2zuHvNKtRBtUbqEQpptK7GDXRBPx", acc.getAddress());
        Account acc2 = new Account(NetworkType.Mainnet, seed, 1);
        Assert.assertEquals("Address Generation is wrong", "AR5EhMHz7YmxC1mrXQ818s6kP81mQvpNuDJ", acc2.getAddress());
    }

    @Test
    public void checkAddress() {
        NetworkType t = NetworkType.Testnet;
        NetworkType m = NetworkType.Mainnet;
        Assert.assertTrue(Account.checkAddress(m, "AR4Exapc1LnZ5nYkGwPvmpD7QZtEsdRQoGk"));
        Assert.assertTrue(Account.checkAddress(t, "AU6K3cTDvrtBVcXmYjk6XVGeegu8vg8B8wL"));
        Assert.assertFalse(Account.checkAddress(m, "invalid address"));
        Assert.assertFalse(Account.checkAddress(t, "AR4Exapc1LnZ5nYkGwPvmpD7QZtEsdRQoGk"));
        Assert.assertFalse(Account.checkAddress(m, "AU6K3cTDvrtBVcXmYjk6XVGeegu8vg8B8wL"));
    }

    @Test
    public void checkContend() throws Exception {
        SlotInfo targetSlot = this.getTargetSlot();
        if (targetSlot != null) {
            Result res = acc.checkContend(chain, targetSlot.getSlotId());
            Assert.assertTrue(res.isOk());
        } else {
            System.out.println("No enough MAB for test account. Skip contend test.");
        }
        Result res2 = acc.checkContend(chain, 0);
        Assert.assertFalse(res2.isOk());
        Account acc2 = new Account(NetworkType.Mainnet, TestConfig.SEED, 0);
        Result res3 = acc2.checkContend(chain, 2);
        Assert.assertFalse(res3.isOk());
        Result res4 = acc2.checkContend(chain, 4);
        Assert.assertFalse(res4.isOk());
    }

    @Test
    public void sendTransaction() throws Exception {
        Long balance = acc.getBalance(chain);
        if (balance < 0.2 * Blockchain.V_UNITY) {
            System.out.println("Skip send Transaction test. The test account should have more than 0.2 VSYS. ");
            return;
        }
        Long amount = (long)(0.1 * Blockchain.V_UNITY);
        PaymentTransaction tx = TransactionFactory.buildPaymentTx(recipient, amount);
        Transaction result = acc.sendTransaction(chain, tx);
        Assert.assertNotNull(result.getId());
    }

    @Ignore
    @Test
    public void contendAndReleaseSlot() throws Exception {
        if (isAddressSupernode(acc.getAddress())) {
            System.out.println("This address of test account is a supernode. Skip contend test.");
            return;
        }
        SlotInfo targetSlot = this.getTargetSlot();
        if (targetSlot == null) {
            System.out.println("No enough MAB for test account. Skip contend test.");
            return;
        }
        int slotId = targetSlot.getSlotId();
        Transaction tx = acc.contendSlot(chain, slotId);
        Assert.assertNotNull(tx.getId());
        Thread.sleep(TestConfig.BLOCK_WAIT_TIME);
        tx = chain.getTransactionById(tx.getId());
        Assert.assertTrue("Cannot find contend tx on blockchain", tx.getHeight() > 0);
        SlotInfo currentSlot = chain.getSlotInfo(slotId);
        Assert.assertEquals("Failed to become new supernode", acc.getAddress(), currentSlot.getAddress());
        Transaction tx2 = acc.releaseSlot(chain, slotId);
        Assert.assertNotNull(tx2.getId());
    }

    private SlotInfo getTargetSlot() throws Exception {
        BalanceDetail balance = acc.getBalanceDetail(chain);
        Long minEB = ContendSlotTransaction.MIN_EFFECTIVE_BALANCE + TransactionFactory.CONTEND_TX_FEE;
        SlotInfo targetSlot = null;
        List<SlotInfo> allSlots = chain.getAllSlotInfo();
        int slotGap = chain.getNetwork() == NetworkType.Mainnet ? 4 : 1;
        for (int i=0; i < 60; i+=slotGap) {
            SlotInfo slot = allSlots.get(i);
            if (slot.getMintingAverageBalance() < balance.getMintingAverage()) {
                targetSlot = slot;
                break;
            }
        }
        long txFee = TransactionFactory.CONTEND_TX_FEE;
        if (targetSlot != null && balance.getEffective() >= minEB && balance.getAvailable() >= txFee) {
            return targetSlot;
        } else {
            return null;
        }
    }

    private boolean isAddressSupernode(String address) throws Exception {
        List<SlotInfo> allSlots = chain.getAllSlotInfo();
        for (SlotInfo slot : allSlots) {
            if (slot.getAddress().equals(address)) {
                return true;
            }
        }
        return false;
    }
}