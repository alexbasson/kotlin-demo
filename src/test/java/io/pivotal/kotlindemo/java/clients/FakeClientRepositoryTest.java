package io.pivotal.kotlindemo.java.clients;

public class FakeClientRepositoryTest extends ClientRepositoryTest {

    @Override
    public ClientRepository getClientRepository() {
        return new FakeClientRepository();
    }

}
