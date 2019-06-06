package io.pivotal.kotlindemo.kotlin.accounts

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

abstract class AccountRepositoryTest {
    abstract fun getAccountRepository(): AccountRepository

    private lateinit var accountRepository: AccountRepository

    @Before
    fun setup() {
        accountRepository = getAccountRepository()
    }

    @Test
    fun `create() adds accounts with unique ids`() {
        val createAccountRequest1 = buildCreateAccountRequest(
            clientId = 1L,
            number = "account 1 number",
            fullname = "account 1 full name",
            nickname = "account 1 nickname",
            type = AccountType.CREDIT
        )

        val account1 = accountRepository.create(createAccountRequest1)

        assertThat(account1).isEqualToIgnoringGivenFields(
            buildAccount(
                clientId = createAccountRequest1.clientId,
                number = createAccountRequest1.number,
                fullname = createAccountRequest1.fullname,
                nickname = createAccountRequest1.nickname,
                type = createAccountRequest1.type
            ),
            "id"
        )

        val createAccountRequest2 = buildCreateAccountRequest(
            clientId = 2L,
            number = "account 2 number",
            fullname = "account 2 full name",
            nickname = "account 2 nickname",
            type = AccountType.INVESTMENT
        )

        val account2 = accountRepository.create(createAccountRequest2)

        assertThat(account2).isEqualToIgnoringGivenFields(
            buildAccount(
                clientId = createAccountRequest2.clientId,
                number = createAccountRequest2.number,
                fullname = createAccountRequest2.fullname,
                nickname = createAccountRequest2.nickname,
                type = createAccountRequest2.type
            ),
            "id"
        )

        assertThat(account1.id).isNotEqualTo(account2.id)
    }

    @Test
    fun `findAll() returns all created accounts`() {
        val account1 = accountRepository.create(buildCreateAccountRequest())
        val account2 = accountRepository.create(buildCreateAccountRequest())

        assertThat(accountRepository.findAll()).containsExactlyInAnyOrder(account1, account2)
    }

    @Test
    fun `findById(), when the account exists, returns the account with the given id`() {
        val account1 = accountRepository.create(buildCreateAccountRequest())
        val account2 = accountRepository.create(buildCreateAccountRequest())

        assertThat(accountRepository.findById(account1.id)).isEqualTo(account1)
        assertThat(accountRepository.findById(account2.id)).isEqualTo(account2)
    }

    @Test(expected = NoAccountFoundException::class)
    fun `findById(), when the account does not exist, throws a NoAccountFoundException`() {
        val nonExistantAccountId = 12345L
        accountRepository.findById(id = nonExistantAccountId)
    }

    @Test
    fun `findByClientId() returns a (possibly empty) list of all accounts for the given client id`() {
        val account1 = accountRepository.create(buildCreateAccountRequest(clientId = 1L))
        val account2 = accountRepository.create(buildCreateAccountRequest(clientId = 1L))

        assertThat(accountRepository.findByClientId(1L)).containsExactlyInAnyOrder(account1, account2)
        assertThat(accountRepository.findByClientId(2L)).isEmpty()
    }
}
