package io.pivotal.kotlindemo.java.clients;

import java.util.Objects;

public class ClientOverview {
    private final Client client;
    private final int numberOfAccounts;

    public ClientOverview(Client client, int numberOfAccounts) {
        this.client = client;
        this.numberOfAccounts = numberOfAccounts;
    }

    public Client getClient() {
        return client;
    }

    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOverview that = (ClientOverview) o;
        return numberOfAccounts == that.numberOfAccounts &&
            Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, numberOfAccounts);
    }
}
