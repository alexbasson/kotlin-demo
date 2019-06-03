package io.pivotal.kotlindemo.java.accounts;

public class DefaultAccountRepositoryTest extends AccountRepositoryTest {

    @Override
    public AccountRepository getAccountRepository() {
        return new DefaultAccountRepository();
    }

}
