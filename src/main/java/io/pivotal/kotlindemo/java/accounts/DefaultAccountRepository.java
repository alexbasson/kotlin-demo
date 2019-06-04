package io.pivotal.kotlindemo.java.accounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.pivotal.kotlindemo.java.clients.DefaultClientRepository.*;

public class DefaultAccountRepository implements AccountRepository {

    private Map<Long, Account> accountMap = new HashMap<>();

    @Override
    public Account create(CreateAccountRequest createAccountRequest) {
        Long id = (long) accountMap.values().size();
        Account account = new Account(
                id,
                createAccountRequest.getClientId(),
                createAccountRequest.getNumber(),
                createAccountRequest.getFullname(),
                createAccountRequest.getNickname(),
                createAccountRequest.getType());

        accountMap.put(id, account);

        return account;
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accountMap.values());
    }

    @Override
    public Account findById(Long id) {
        Account account = accountMap.get(id);
        if (account == null) {
            throw new NoAccountFoundException();
        }
        return account;
    }

    @Override
    public List<Account> findByClientId(Long id) {
        return accountMap
                .values()
                .stream()
                .filter(account -> account.getClientId().equals(id))
                .collect(Collectors.toList());
    }

    public void initialSeed() {
        create(new CreateAccountRequest(
                alice.getId(),
                "A12345",
                "Low-interest Checking",
                "My Checking Account",
                AccountType.CHECKING
        ));

        create(new CreateAccountRequest(
                alice.getId(),
                "A23456",
                "High-interest Savings",
                "My Savings Account",
                AccountType.SAVINGS
        ));

        create(new CreateAccountRequest(
                betty.getId(),
                "B34567",
                "Low-interest Savings",
                "My Great Savings!",
                AccountType.SAVINGS
        ));

        create(new CreateAccountRequest(
                betty.getId(),
                "B45678",
                "Low-interest Checking",
                "Checking Account",
                AccountType.CHECKING
        ));

        create(new CreateAccountRequest(
                betty.getId(),
                "B56789",
                "Credit",
                "Home Equity loan",
                AccountType.CREDIT
        ));

        create(new CreateAccountRequest(
                charles.getId(),
                "C87654",
                "Investment",
                "mutual funds and stocks",
                AccountType.INVESTMENT
        ));

        create(new CreateAccountRequest(
                charles.getId(),
                "C98765",
                "Low-Interest Checking",
                "checking",
                AccountType.CHECKING
        ));

        create(new CreateAccountRequest(
                charles.getId(),
                "C65432",
                "Credit",
                "mortgage",
                AccountType.CREDIT
        ));

        create(new CreateAccountRequest(
                david.getId(),
                "D54321",
                "High-interest Savings",
                "My savings account",
                AccountType.SAVINGS
        ));

        create(new CreateAccountRequest(
                david.getId(),
                "D65432",
                "Credit",
                "My mortgage",
                AccountType.CREDIT
        ));

        create(new CreateAccountRequest(
                david.getId(),
                "D76543",
                "Investment",
                "My investment account",
                AccountType.INVESTMENT
        ));
    }

}
