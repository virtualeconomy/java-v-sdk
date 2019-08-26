package v.systems.contract;

public class ContractFactory {

    public static Contract createToken(ContractType type) {
        switch (type) {
            case Token:
                return createTokenWithoutSplit();
            case TokenWithSplit:
                return createTokenWithSplit();
        }
        return null;
    }

    private static Contract createTokenWithSplit() {
        Contract c = new Contract();
        c.setLanguageCode("vdds");
        c.setLanguageVersion(1);
        c.getTriggers().add("111111CktRzdj615GhYiN5qtRjzxwE8jypDwGbQV9iXn8NK3o");
        c.getStateVariables().add("13");
        c.getStateVariables().add("5T");
        String[] descriptors = {
                "1111112EP7Gb96dj5VLAcfpEDiaTeapNEfczB",
                "1bbn7XmN81WxPGPpdCtU38xMGvgaUbnrASoSQf",
                "12CCZE4Xi2itkMk8zE87pKF1mN8U311U2Bq99jV",
                "1QWywjfJS2CDokeThm2PVEKPzag9nouQ72jYj",
                "1N9hmWGg5UN8zHMrtrxMWFND6pUMLhAGg",
                "131h1vYVUznedmBCAvcPqzW6Ewx5xvXF4fB",
                "13R2cuenmhy573wnHtSch5h2jSJQ3hS6h1B",
                "13pNDtm64QxxY3tNu8tVZiwUAPB8TP9cVs7",
                "1VXrvftSE5dDWxAQwUHSpM3jdx2FR",
                "1Z6ifdCDh5xNbucPnydpgg2nbfS3R",
                "1Cyp7C43k4foxpiwcrr33L3mCEKxLsoe",
                "13zAHzf98UyzPAVrFiE8sQLcUX6EcSK"
        };
        for (String d : descriptors) {
            c.getDescriptors().add(d);
        }
        c.getTextual().setTriggers("124VnyFU9tQUn4Z19KBbV8aAQF4aCgWrQWrLL1yK5RpWY2sU74P8GU6wJ6dwyuFHP3Xt5Kmpm");
        c.getTextual().setDescriptors("1RypGiL5eNbDESxn2SVM8HrLF6udvXV6YmwvFsp4fLJfRcr7nQuVFMvXn6KmWJeq8c53tdrutZcsQA2zyHb8Wj1tQUjGmitP6kLzcnpQXEq7AUZpMT6j7LCrhJvs3oLCCr7SSpz3h4iJJJg9WuL7Acbsw1x2AK4tRSZWXyrnLgqWhgqbTdfmxFGHjD58XrScBibJ9AUwEWCAeAna3NFofSZaSDxFJAK2adrrHhJdktQCQobMJMmC164HtJKF569naoMREkncYedQwXWk4uyPzGTUKsfXFwLaR77wv8gtNEjqwvGtpdFJELyJ3RC2F7exhqiiVxTaoGrAanuv1bianVbKqPAygPaGrhA1H3JmQWksNhg6q7dtPvBuqWDqDs4DkhV35JhNFeiER18o49pxX8zR1n1jvis6QrU2cD1Cn3yXwSZaW8TXWMKZ7ULRo1UcJykQvQCLq3EBVfzf6iULhuRagTnJ3Sq4tFSxgnNPhATLDreQpEe1BA3SfRWKRskLFjXV5aMeYxgFLfqYEFJ37BaRVyFZDSUgrKLMnNzrZZG2P81t7MhT6GpDApLZkNtjdGRMQGFsRN2azGruQReFnXeB3mScaxgfhGxcu9B");
        c.getTextual().setStateVariables("1FKqt4aNuTwK15xVSfjkwT");
        return c;
    }

    private static Contract createTokenWithoutSplit() {
        Contract c = new Contract();
        c.setLanguageCode("vdds");
        c.setLanguageVersion(1);
        c.getTriggers().add("111111CktRzdj615GhYiN5qtRjzxwE8jypDwGbQV9iXn8NK3o");
        c.getStateVariables().add("13");
        c.getStateVariables().add("5T");
        String[] descriptors = {
                "1111112EP7Gb96dj5VLAcfpEDiaTeapNEfczB",
                "1bbn7XmN81WxPGPpdCtU38xMGvgaUbnrASoSQf",
                "12CCZE4Xi2itkMk8zE87pKF1mN8U311U2Bq99jV",
                "1Gs2aNxFuEfcSh7o39ePD12mowSKpWNzk",
                "12cMQwSCCHcBkwk45uC4XWNpTKSq2DHfELq",
                "131h1vYVUzncEEWeCs2HNbZkwoo96zCWrgq",
                "13R2cuenmhnVfETEp7UAFEpCNkfsWfv2fYm",
                "1Ry1Biaem5J4RziS5xw4upapCQhJX",
                "1VXrvftSE5dDWxAQwUHSn9ZsA876X",
                "1BnLCNdV5VfEm8o1GzFc3xDrnvRPNeVE",
                "13iRGT52donXq4d7amiaviW9eeMxz8o"
        };
        for (String d : descriptors) {
            c.getDescriptors().add(d);
        }
        c.getTextual().setTriggers("124VnyFU9tQUn4Z19KBbV8aAQF4aCgWrQWrLL1yK5RpWY2sU74P8GU6wJ6dwyuFHP3Xt5Kmpm");
        c.getTextual().setDescriptors("1JTuFtDUkasyJs9FCrgJ3CRDU8J9sh5oJbG9LqVpdBbEmDwjM1hbDnmhcBBdWHgaLDDiExdgnRmQDJwY3yfpm441KsPQpiPf9JJzNH8fLNoYGT5S8P8Qki4nsP9DmJ6qDLj4ktW2fJeEosWaGqULwcpCFiBFeQiA6DN8tJE6nEvz75ku4U66mMbtJGi2g9pJyHRoxT67Fzmnvpa4UhXyB6J3GgyM6od68o3vzXd2kzCGnXGK3jXNUTBKXwWQTiRq8oQ2XEKp3vNXSrYktjYbSLh7g4Zq6XrkwxwSpv2m3AehXwLbZ8wA7CYaKcgXngVdc2rgMMwMUygXnAxrcZNKRFyP7cjZ6213poGqHe7gkqBEpr93uQPRm5PCW3gQVr37KTafHpUfr1JSbmT4GjwEADP2L1KGmnVJJxCsnLgR2QnhiopaHcni2rYPe2TVStJ2kHmJwNBQ7YBWMqu45TSWySfehcwqXeKktJhrWrPzSKr1ca8Q9PXptKQVWaFn2pfqXWrdfRci8LLZD7FgPdp7Q4wr7PpEwMBkA7tUdw2PNYiHFLYaFduFx1ya1o");
        c.getTextual().setStateVariables("1FKqt4aNuTwK15xVSfjkwT");
        return c;
    }
}
