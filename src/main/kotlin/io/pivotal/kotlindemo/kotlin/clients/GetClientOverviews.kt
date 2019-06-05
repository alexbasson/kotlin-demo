package io.pivotal.kotlindemo.kotlin.clients

import io.pivotal.kotlindemo.kotlin.accounts.AccountRepository

class GetClientOverviews(
    val clientRepository: ClientRepository,
    val accountRepository: AccountRepository
) {
    fun execute(): List<ClientOverview> {
        return clientRepository
            .findAll()
            .map(this::getClientOverview)
    }

    private fun getClientOverview(client: Client): ClientOverview {
        val accounts = accountRepository.findByClientId(client.id)
        return ClientOverview(client, accounts.size)
    }
}
