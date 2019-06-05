package io.pivotal.kotlindemo.java.clients;

import io.pivotal.kotlindemo.java.accounts.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetClientOverviewsTest {
    private GetClientOverviews getClientOverviews;

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    private Client client1;
    private Account client1Account1;
    private Account client1Account2;
    private Account client1Account3;

    private Client client2;
    private Account client2Account1;
    private Account client2Account2;

    private Client client3;

    @Before
    public void setup() {
        clientRepository = new FakeClientRepository();
        accountRepository = new FakeAccountRepository();

        seedRepositories();

        getClientOverviews = new GetClientOverviews(clientRepository, accountRepository);
    }

    @Test
    public void itReturnsClientOverviewsWithClientsAndTheNumberOfAccounts() {
        List<ClientOverview> clientOverviews = getClientOverviews.execute();

        assertThat(clientOverviews).hasSize(3);

        ClientOverview client1Overview = clientOverviews.get(0);
        assertThat(client1Overview).isEqualTo(
            new ClientOverview(
                client1,
                3
            )
        );

        ClientOverview client2Overview = clientOverviews.get(1);
        assertThat(client2Overview).isEqualTo(
            new ClientOverview(
                client2,
                2
            )
        );

        ClientOverview client3Overview = clientOverviews.get(2);
        assertThat(client3Overview).isEqualTo(
            new ClientOverview(
                client3,
                0
            )
        );
    }

    private void seedRepositories() {
        CreateClientRequest createClient1Request = new CreateClientRequest(
          "first1",
          "last1",
          "first1.last1@email.com"
        );
        client1 = clientRepository.create(createClient1Request);

        CreateAccountRequest createClient1AccountRequest1 = new CreateAccountRequest(
            client1.getId(),
            "client1Account1 number",
            "client1Account1 fullname",
            "client1Account1 nickname",
            AccountType.SAVINGS
        );
        client1Account1 = accountRepository.create(createClient1AccountRequest1);

        CreateAccountRequest createClient1AccountRequest2 = new CreateAccountRequest(
            client1.getId(),
            "client1Account2 number",
            "client1Account2 fullname",
            "client1Account2 nickname",
            AccountType.SAVINGS
        );
        client1Account2 = accountRepository.create(createClient1AccountRequest2);

        CreateAccountRequest createClient1AccountRequest3 = new CreateAccountRequest(
            client1.getId(),
            "client1Account3 number",
            "client1Account3 fullname",
            "client1Account3 nickname",
            AccountType.SAVINGS
        );
        client1Account3 = accountRepository.create(createClient1AccountRequest3);



        CreateClientRequest createClient2Request = new CreateClientRequest(
            "first2",
            "last2",
            "first2.last2@email.com"
        );
        client2 = clientRepository.create(createClient2Request);

        CreateAccountRequest createClient2AccountRequest1 = new CreateAccountRequest(
            client2.getId(),
            "client2Account1 number",
            "client2Account1 fullname",
            "client2Account1 nickname",
            AccountType.SAVINGS
        );
        client2Account1 = accountRepository.create(createClient2AccountRequest1);

        CreateAccountRequest createClient2AccountRequest2 = new CreateAccountRequest(
            client2.getId(),
            "client2Account2 number",
            "client2Account2 fullname",
            "client2Account2 nickname",
            AccountType.SAVINGS
        );
        client2Account2 = accountRepository.create(createClient2AccountRequest2);



        CreateClientRequest createClient3Request = new CreateClientRequest(
            "first3",
            "last3",
            "first3.last3@email.com"
        );
        client3 = clientRepository.create(createClient3Request);

    }
}
