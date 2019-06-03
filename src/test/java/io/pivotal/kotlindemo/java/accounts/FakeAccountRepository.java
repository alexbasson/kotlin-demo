package io.pivotal.kotlindemo.java.accounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FakeAccountRepository implements AccountRepository {

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

}
