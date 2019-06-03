package io.pivotal.kotlindemo.java.accounts;

import io.pivotal.kotlindemo.java.clients.Client;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AccountRepositoryTest {
    public abstract AccountRepository getAccountRepository();

    private AccountRepository accountRepository;

    private static final Client client1 = new Client(
            123L,
            "client1first",
            "client1last",
            "client1@email.com"
    );

    private static final Client client2 = new Client(
            456L,
            "client2first",
            "client2last",
            "client2@email.com"
    );

    @Before
    public void setup() {
        accountRepository = getAccountRepository();
    }

    @Test
    public void create_addsAccountsWithUniqueIds() {
        CreateAccountRequest createAccountRequest1 = new CreateAccountRequest(
                client1.getId(),
                "account1 number",
                "account1 full name",
                "account1 nickname",
                AccountType.CHECKING);

        Account account1 = accountRepository.create(createAccountRequest1);

        assertThat(account1.getId()).isNotNull();
        assertThat(account1).isEqualToIgnoringGivenFields(
                new Account(
                        null,
                        createAccountRequest1.getClientId(),
                        createAccountRequest1.getNumber(),
                        createAccountRequest1.getFullname(),
                        createAccountRequest1.getNickname(),
                        createAccountRequest1.getType()
                ),
                "id"
        );

        CreateAccountRequest createAccountRequest2 = new CreateAccountRequest(
                client2.getId(),
                "account2 number",
                "account2 full name",
                "account2 nickname",
                AccountType.CHECKING);

        Account account2 = accountRepository.create(createAccountRequest2);

        assertThat(account2.getId()).isNotNull();
        assertThat(account2).isEqualToIgnoringGivenFields(
                new Account(
                        null,
                        createAccountRequest2.getClientId(),
                        createAccountRequest2.getNumber(),
                        createAccountRequest2.getFullname(),
                        createAccountRequest2.getNickname(),
                        createAccountRequest2.getType()
                ),
                "id"
        );

        assertThat(account1.getId()).isNotEqualTo(account2.getId());
    }

    @Test
    public void findAll_returnsAllCreatedAccounts() {
        CreateAccountRequest createAccountRequest1 = new CreateAccountRequest(
                client1.getId(),
                "account1 number",
                "account1 full name",
                "account1 nickname",
                AccountType.CHECKING);

        Account account1 = accountRepository.create(createAccountRequest1);

        CreateAccountRequest createAccountRequest2 = new CreateAccountRequest(
                client2.getId(),
                "account2 number",
                "account2 full name",
                "account2 nickname",
                AccountType.CHECKING);

        Account account2 = accountRepository.create(createAccountRequest2);

        assertThat(accountRepository.findAll()).containsExactlyInAnyOrder(
                account1,
                account2
        );
    }

    @Test
    public void findById_whenTheAccountExists_returnsTheAccountWithTheGivenId() {
        CreateAccountRequest createAccountRequest1 = new CreateAccountRequest(
                client1.getId(),
                "account1 number",
                "account1 full name",
                "account1 nickname",
                AccountType.CHECKING);

        Account account1 = accountRepository.create(createAccountRequest1);

        CreateAccountRequest createAccountRequest2 = new CreateAccountRequest(
                client2.getId(),
                "account2 number",
                "account2 full name",
                "account2 nickname",
                AccountType.CHECKING);

        Account account2 = accountRepository.create(createAccountRequest2);

        assertThat(accountRepository.findById(account1.getId())).isEqualTo(account1);
        assertThat(accountRepository.findById(account2.getId())).isEqualTo(account2);
    }

    @Test(expected = NoAccountFoundException.class)
    public void findById_whenTheAccountDoesNotExist_throwsNoAccountFoundException() {
        Long nonExistantAccountId = 12345L;
        accountRepository.findById(nonExistantAccountId);
    }

    @Test
    public void findByClientId_returnsAPossiblyEmptyListOfAllClientAccountsForTheGivenClientId() {
        CreateAccountRequest createAccountRequest1 = new CreateAccountRequest(
                client1.getId(),
                "account1 number",
                "account1 full name",
                "account1 nickname",
                AccountType.CHECKING);

        Account account1 = accountRepository.create(createAccountRequest1);

        CreateAccountRequest createAccountRequest2 = new CreateAccountRequest(
                client1.getId(),
                "account2 number",
                "account2 full name",
                "account2 nickname",
                AccountType.CHECKING);

        Account account2 = accountRepository.create(createAccountRequest2);

        assertThat(accountRepository.findByClientId(client1.getId())).containsExactlyInAnyOrder(
                account1,
                account2
        );

        assertThat(accountRepository.findByClientId(client2.getId())).isEmpty();
    }

}
