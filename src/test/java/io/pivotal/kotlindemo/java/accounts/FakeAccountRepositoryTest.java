package io.pivotal.kotlindemo.java.accounts;

public class FakeAccountRepositoryTest extends AccountRepositoryTest {

    @Override
    public AccountRepository getAccountRepository() {
        return new FakeAccountRepository();
    }

}
