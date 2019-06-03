package io.pivotal.kotlindemo.java.clients;

public class DefaultClientRepositoryTest extends ClientRepositoryTest {

    @Override
    public ClientRepository getClientRepository() {
        return new DefaultClientRepository();
    }

}
