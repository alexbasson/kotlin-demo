package io.pivotal.kotlindemo.java.accounts;

import java.util.List;

public interface AccountRepository {
    Account create(CreateAccountRequest createAccountRequest);
    List<Account> findAll();
    Account findById(Long id);
    List<Account> findByClientId(Long id);
}
