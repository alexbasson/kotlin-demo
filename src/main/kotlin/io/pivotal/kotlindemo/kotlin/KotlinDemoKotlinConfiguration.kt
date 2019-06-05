package io.pivotal.kotlindemo.kotlin

import io.pivotal.kotlindemo.kotlin.accounts.AccountRepository
import io.pivotal.kotlindemo.kotlin.accounts.DefaultAccountRepository
import io.pivotal.kotlindemo.kotlin.clients.ClientRepository
import io.pivotal.kotlindemo.kotlin.clients.DefaultClientRepository
import io.pivotal.kotlindemo.kotlin.clients.GetClientOverviews
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KotlinDemoKotlinConfiguration {

    @Bean
    fun getClientOverviews(
        clientRepository: ClientRepository,
        accountRepository: AccountRepository
    ) = GetClientOverviews(
        clientRepository,
        accountRepository
    )

    @Bean
    fun accountRepository(): AccountRepository {
        val accountRepository = DefaultAccountRepository()
        accountRepository.initialSeed()
        return accountRepository
    }

    @Bean
    fun clientRepository(): ClientRepository {
        val clientRepository = DefaultClientRepository()
        clientRepository.initialSeed()
        return clientRepository
    }

}
