package io.pivotal.kotlindemo.kotlin

import io.pivotal.kotlindemo.kotlin.accounts.AccountRepository
import io.pivotal.kotlindemo.kotlin.accounts.DefaultAccountRepository
import io.pivotal.kotlindemo.kotlin.clients.ClientRepository
import io.pivotal.kotlindemo.kotlin.clients.DefaultClientRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KotlinDemoKotlinConfiguration {

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
