package io.pivotal.kotlindemo.java.clients;

import io.pivotal.kotlindemo.java.accounts.Account;
import io.pivotal.kotlindemo.java.accounts.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetClientOverviews {
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public GetClientOverviews(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public List<ClientOverview> execute() {
        return clientRepository
            .findAll()
            .stream()
            .map(this::getClientOverview)
            .collect(Collectors.toList());
    }

    private ClientOverview getClientOverview(Client client) {
        List<Account> accounts = accountRepository.findByClientId(client.getId());
        return new ClientOverview(
            client,
            accounts.size()
        );
    }
}
