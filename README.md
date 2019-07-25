# java-vsystems
Java library for V Systems

## Install

  1. To use this SDK, we need Java 1.8. First of all, update the repository
     
     ```shell
     $ sudo apt-get update
     ```
     
     Install Java in your machine
     
     ```shell
     $ sudo apt-get install openjdk-8-jdk
     ```
     
     Check Java version (remove the old version Java if needed).
     
     ```shell
     $ java -version
     openjdk version "1.8.0_181"
     OpenJDK Runtime Environment (build 1.8.0_181-8u181-b13-0ubuntu0.16.04.1-b13)
     OpenJDK 64-Bit Server VM (build 25.181-b13, mixed mode)
     ```

  2. clone this project

     ```bash
     $ git clone https://github.com/virtualeconomy/java-v-sdk.git
     ```

  3. import GSON jar to your project. You can download gson.jar from [Gson Release](https://github.com/google/gson/releases). If you are using Maven, you can add dependency looks like this:
     
     ```
     <dependency>
         <groupId>com.google.code.gson</groupId>
         <artifactId>gson</artifactId>
         <version>2.8.5</version>
     </dependency>
     ```

## Usage

### Create chain object
1. For testnet chain:

    ```java
    import v.systems.Blockchain;
    import v.systems.type.NetworkType;
 
    Blockchain chain = new Blockchain(NetworkType.Testnet, "http://test.v.systems:9922");
    ```

2. For mainnet chain:

    ```java
    import v.systems.Blockchain;
    import v.systems.type.NetworkType;
 
    Blockchain chain = new Blockchain(NetworkType.Mainnet, "https://wallet.v.systems/api");
    ```
    
### Create address object
1. Create account by seed

    ```java
    import v.systems.Account;
    import v.systems.type.NetworkType;
 
    Account acc = new Account(NetworkType.Testnet, "<your seed>", 0);
    ```

2. Create account by private key

    ```java
    import v.systems.Account;
    import v.systems.type.NetworkType;
     
    Account acc = new Account(NetworkType.Testnet, "<base58 private key>");
    ```
 
3. Create account by public key

    ```java
    import v.systems.Account;
    import v.systems.type.NetworkType;
     
    Account acc = new Account(NetworkType.Testnet, "<base58 public key>", null);
    ```
    
4. Create account by address

    ```java
    import v.systems.Account;
    import v.systems.type.NetworkType;
     
    Account acc = new Account(NetworkType.Testnet, null, "<base58 address>");
    ```
    
### Send transaction
1. Send Payment transaction

    ```java
    Long amount = 1 * Blockchain.V_UNITY;  // Send 1.0 V coin
    PaymentTransaction tx = TransactionFactory.buildPaymentTx("<recipient address>", amount);
    String txId = tx.getId(); // get Tx ID offline
    
    // Usage 1: for hot wallet sending transaction
    Transaction result = acc.sendTransaction(chain, tx);
    
    // Usage 2: for cold wallet signing transaction
    String signature = acc.getSignature(tx);
    ```

2. Send Lease transaction

    ```java
    Long amount = 1 * Blockchain.V_UNITY;  // Lease 1.0 V coin
    LeaseTransaction tx = TransactionFactory.buildLeaseTx("<recipient address>", amount);
    String txId = tx.getId(); // get Tx ID offline
    
    // Usage 1: for hot wallet sending transaction
    Transaction result = acc.sendTransaction(chain, tx);
    
    // Usage 2: for cold wallet signing transaction
    String signature = acc.getSignature(tx);
    ```
