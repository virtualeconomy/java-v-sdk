package v.systems;

import org.bitcoinj.core.Base58;
import org.junit.Assert;
import org.junit.Test;
import v.systems.contract.Contract;
import v.systems.contract.ContractFactory;
import v.systems.contract.ContractType;
import v.systems.contract.TokenInfo;
import v.systems.error.SerializationError;
import v.systems.transaction.PaymentTransaction;
import v.systems.transaction.TransactionFactory;
import v.systems.utils.BytesHelper;

import java.util.List;

public class SerializationTest {

    @Test
    public void toBytes() throws SerializationError {
        PaymentTransaction tx = TransactionFactory.buildPaymentTx("AU6GsBinGPqW8zUuvmjgwpBNLfyyTU3p83Q",1000000000L, "HXRC", 1547722056762119200L);
        int[] expectInt = {0x02, 0x15, 0x7a, 0x9d, 0x02, 0xac, 0x57, 0xd4, 0x20, 0x00, 0x00, 0x00, 0x00, 0x3b, 0x9a, 0xca, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x98, 0x96, 0x80, 0x00, 0x64, 0x05, 0x54, 0x9c, 0x6d, 0xf7, 0xb3, 0x76, 0x77, 0x1b, 0x19, 0xff, 0x3b, 0xdb, 0x58, 0xd0, 0x4b, 0x49, 0x99, 0x91, 0x66, 0x3c, 0x47, 0x44, 0x4e, 0x42, 0x5f, 0x00, 0x03, 0x31, 0x32, 0x33};
        byte[] expect = new byte[expectInt.length];
        for (int i=0; i<expectInt.length; i++) {
            expect[i] = (byte)expectInt[i];
        }
        String expectHex = BytesHelper.toHex(expect);
        byte[] actual = tx.toBytes();
        List<Byte> actual2 = tx.toByteList();
        Assert.assertEquals("Bytes length must the same.", expect.length, actual.length);
        Assert.assertEquals("Bytes length must the same.", expect.length, actual2.size());
        Assert.assertEquals("Hex Bytes must the same.", expectHex, BytesHelper.toHex(actual));
        Assert.assertEquals("Hex Bytes must the same.", expectHex, BytesHelper.toHex(actual2));
    }

    @Test
    public void contractSerialization() throws SerializationError {
        Contract c = ContractFactory.createToken(ContractType.TokenWithSplit);
        Assert.assertNotNull(c);
        String expectBase58 = "3dPGAWbTw4srh5hmMiRUhHtcxmXXLUooKGAfnmz11j5NruyJpBzZpgvADMdZS7Mevy5MAHqFbfHYdfqaAe1JEpLWt1pJWLHZBV62zUhLGmVLXUP5UDvSs24jsBRHqZMC71ciE1uYtgydKxCoFJ3rAgsYqp7GDeTU2PXS5ygDmL6WXmbAYPS8jE4sfNUbJVwpvL1cTw4nnjnJvmLET8VmQybxFt415RemV3MFPeYZay5i5gMmyZa63bjzK1uMZAVWA9TpF5YQ1NTZjPaRPvQGYVY4kY9L4LFJvUG2bib1QaNh7wUAQnTzJfRYJoy1aegFGFZFnBGp9GugH4fHAY69vGmZQnhDw3jU45G9odFyXo3T5Ww4R5szegbjCUKdUGpXf9vY2cKEMJ7i8eCkFVG1dDFZeVov1KMjkVNV8rDBDYfcp3oSGNWQQvGSUT5iGUvDRN8phy1UpR3A9uMVebvjLnVzPx9RyqQ8HaXLM8vPhLuWLoh5hk1Zi1n9nwz55XvKDYjP6eeB55yK5vpg8xjaYDnw9bjYV7ZmS7LAsHvXfnwi8y2W6vk2hGvs4rtR1vNRZSQMPGRRSuwCRJL1yngH6uHWwm2ajWxc684jApuoLdyjZomfCtdpabSyU3kp9Lrn8zT8BVY332sJPQU6gTQi8ke9s9dBxCae4cfSQM6HhuBmFc5KKWHCVG4bm4KZRYbMtidw8ZZnjaAMtcGq7k3Se6GXaTxdS3GcuttB3VB7njypyzuqAcfCdYb9ht8Y1WuTCZ1aLsXsL6eydfk2WLJVrqYpbTk6AchV5gMAEopvc3qXvzrDCedjtNsDmA56Lh6PxrrKr8aV8Wzz8aMaQ88YsVBpE8J4cDkxzo31AojhzEGVBKLmpb3bjmsaw9VkpB6yL8ngYs8eJMSPdM289TSMaEmG4eHt1jezpHTKxkuB9cwqcvhGNLWuv8KXQkik5pRMXV67Qs2FvjpzeJ81z2hnVh1wCtsa6M6qAG1gsqLHa1AVMRzsowafC99uDexwWMBS2RqsZWZBXJcUiNVULjApSnoBREYfHYEpjJ152hnTYZCAwpZMWEkVdBQpZ3zk8gbfLxB4fWMfKgJJucbKPGp1K56u7P8MHQu9aNb9dEof2mwX8rTHjk8jSQ7kXVX4Mf1JqMRWWftkV3GmU1nqYhxRGu4FjDNAomwTr5epHpcMF6P5oiXcLWh5BFQVmGYKz129oizAyUJBsZdxr2WZEGDieLxUg8cve25g28oTuCVENST4z1ZsFAN9wTa1";
        byte[] expect = Base58.decode(expectBase58);
        String expectHex = BytesHelper.toHex(expect);
        byte[] actual = c.toBytes();
        List<Byte> actual2 = c.toByteList();
        Assert.assertEquals("Bytes length must the same.", expect.length, actual.length);
        Assert.assertEquals("Bytes length must the same.", expect.length, actual2.size());
        Assert.assertEquals("Hex Bytes must the same.", expectHex, BytesHelper.toHex(actual));
        Assert.assertEquals("Hex Bytes must the same.", expectHex, BytesHelper.toHex(actual2));
    }

    @Test
    public void contractSerialization2() throws SerializationError {
        Contract c = ContractFactory.createToken(ContractType.Token);
        Assert.assertNotNull(c);
        String expectBase58 = "3GQnJtxDQc3zFuUwXKbrev1TL7VGxk5XNZ7kUveKK6BsneC1zTSTRjgBTdDrksHtVMv6nwy9Wy6MHRgydAJgEegDmL4yx7tdNjdnU38b8FrCzFhA1aRNxhEC3ez7JCi3a5dgVPr93hS96XmSDnHYvyiCuL6dggahs2hKXjdz4SGgyiUUP4246xnELkjhuCF4KqRncUDcZyWQA8UrfNCNSt9MRKTj89sKsV1hbcGaTcX2qqqSU841HyokLcoQSgmaP3uBBMdgSYVtovPLEFmpXFMoHWXAxQZDaEtZcHPkrhJyG6CdTgkNLUQKWtQdYzjxCc9AsUGMJvWrxWMi6RQpcqYk3aszbEyAh4r4fcszHHAJg64ovDgMNUDnWQWJerm5CjvN76J2MVN6FqQkS9YrM3FoHFTj1weiRbtuTc3mCR4iMcu2eoxcGYRmUHxKiRoZcWnWMX2mzDw31SbvHqqRbF3t44kouJznTyJM6z1ruiyQW6LfFZuV6VxsKLX3KQ46SxNsaJoUpvaXmVj2hULoGKHpwPrTVzVpzKvYQJmz19vXeZiqQ2J3tVcSFH17ahSzwRkXYJ5HP655FHqTr6Vvt8pBt8N5vixJdYtfx7igfKX4aViHgWkreAqBK3trH4VGJ36e28RJP8Xrt6NYG2icsHsoERqHik7GdjPAmXpnffDL6P7NBfyKWtp9g9C289TDGUykS8CNiW9L4sbUabdrqsdkdPRjJHzzrb2gKTf2vB56rZmreTUbJ53KsvpZht5bixZ59VbCNZaHfZyprvzzhyTAudAmhp8Nrks7SV1wTySZdmfLyw7vsNmTEi3hmuPmYqExp4PoLPUwT4TYt2doYUX1ds3CesnRSjFqMhXnLmTgYXsAXvvT2E6PWTY5nPCycQv5pozvQuw1onFtGwY9n5s2VFjxS9W6FkCiqyyZAhCXP5o44wkmD5SVqyqoL5HmgNc8SJL7uMMMDDwecy7Sh9vvt3RXirH7F7bpUv3VsaepVGCHLfDp9GMG59ZiWK9Rmzf66e8Tw4unphu7gFNZuqeBk2YjCBj3i4eXbJvBEgCRB51FATRQY9JUzdMv9Mbkaq4DW69AgdqbES8aHeoax1UDDBi3raM8WpP2cKVEqoeeCGYM2vfN6zBAh7Tu3M4NcNFJmkNtd8Mpc2Md1kxRsusVzHiYxnsZjo";
        byte[] expect = Base58.decode(expectBase58);
        String expectHex = BytesHelper.toHex(expect);
        byte[] actual = c.toBytes();
        List<Byte> actual2 = c.toByteList();
        Assert.assertEquals("Bytes length must the same.", expect.length, actual.length);
        Assert.assertEquals("Bytes length must the same.", expect.length, actual2.size());
        Assert.assertEquals("Hex Bytes must the same.", expectHex, BytesHelper.toHex(actual));
        Assert.assertEquals("Hex Bytes must the same.", expectHex, BytesHelper.toHex(actual2));
    }

    @Test
    public void contractIdToTokenId() {
        String contractId = "CEsHcSQdNbjHv9q8sPaEGKyDCJ243s6JYoQ";
        int tokenIndex = 0;
        String expect = "TWsVx4qkjs2iPTw8zrhcKq1FDe1ygzhDEwiFY4ap7";
        String actual = TokenInfo.getTokenId(contractId, tokenIndex);
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void tokenIdToContractId() {
        String tokenId = "TWus3xLmyJuHe4DPptVgrF3FBXwFYCr8LzXN66Lye";
        String expect = "CFEEpTek9ARV3ipWHWSTf7vhrwYgwY1yZfP";
        String actual = TokenInfo.getContractId(tokenId);
        Assert.assertEquals(expect, actual);
    }
}