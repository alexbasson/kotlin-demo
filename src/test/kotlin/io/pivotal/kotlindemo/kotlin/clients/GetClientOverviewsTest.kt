package io.pivotal.kotlindemo.kotlin.clients

import io.pivotal.kotlindemo.kotlin.accounts.Account
import io.pivotal.kotlindemo.kotlin.accounts.AccountRepository
import io.pivotal.kotlindemo.kotlin.accounts.FakeAccountRepository
import io.pivotal.kotlindemo.kotlin.accounts.buildCreateAccountRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class GetClientOverviewsTest {

    private lateinit var getClientOverviews: GetClientOverviews

    private lateinit var clientRepository: ClientRepository
    private lateinit var accountRepository: AccountRepository

    private lateinit var client1: Client
    private lateinit var client1Account1: Account
    private lateinit var client1Account2: Account
    private lateinit var client1Account3: Account

    private lateinit var client2: Client
    private lateinit var client2Account1: Account
    private lateinit var client2Account2: Account

    private lateinit var client3: Client

    @Before
    fun setup() {
        clientRepository = FakeClientRepository()
        accountRepository = FakeAccountRepository()

        seedRepositories()

        getClientOverviews = GetClientOverviews(clientRepository, accountRepository)
    }

    @Test
    fun `it returns a list of client overviews with clients and the number of accounts`() {
        val clientOverviews = getClientOverviews.execute()

        assertThat(clientOverviews).hasSize(3)

        val clientOverview1 = clientOverviews.get(0)
        assertThat(clientOverview1).isEqualTo(
            ClientOverview(
                client1,
                3
            )
        )

        val clientOverview2 = clientOverviews.get(1)
        assertThat(clientOverview2).isEqualTo(
            ClientOverview(
                client2,
                2
            )
        )

        val clientOverview3 = clientOverviews.get(2)
        assertThat(clientOverview3).isEqualTo(
            ClientOverview(
                client3,
                0
            )
        )
    }

    private fun seedRepositories() {
        client1 = clientRepository.create(buildCreateClientRequest())
        client1Account1 = accountRepository.create(buildCreateAccountRequest(clientId = client1.id))
        client1Account2 = accountRepository.create(buildCreateAccountRequest(clientId = client1.id))
        client1Account3 = accountRepository.create(buildCreateAccountRequest(clientId = client1.id))

        client2 = clientRepository.create(buildCreateClientRequest())
        client2Account1 = accountRepository.create(buildCreateAccountRequest(clientId = client2.id))
        client2Account2 = accountRepository.create(buildCreateAccountRequest(clientId = client2.id))

        client3 = clientRepository.create(buildCreateClientRequest())
    }

}
