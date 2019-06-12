package io.pivotal.kotlindemo.java.accounts;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class DefaultAccountRepository implements AccountRepository {

    private Map<Long, Account> accountMap = new HashMap<>();

    @Override
    public Account create(CreateAccountRequest createAccountRequest) {
        Long id = (long) accountMap.values().size() + 1;
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
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("accounts.csv");
        InputStreamReader input = new InputStreamReader(inputStream);
        try {
            CSVParser csvParser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(input);
            for (CSVRecord record : csvParser) {
                Long id = parseLong(record.get("Id"));
                Long clientId = parseLong(record.get("Client Id"));
                String number = record.get("Number");
                String fullname = record.get("Full Name");
                String nickname = record.get("Nickname");
                AccountType type = AccountType.valueOf(record.get("Type"));

                Account account = new Account(
                    id,
                    clientId,
                    number,
                    fullname,
                    nickname,
                    type
                );
                accountMap.put(account.getId(), account);
            }
        } catch (IOException e) {
        }
    }

}
