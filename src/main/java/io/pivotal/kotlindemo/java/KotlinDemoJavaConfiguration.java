package io.pivotal.kotlindemo.java;

import io.pivotal.kotlindemo.java.accounts.AccountRepository;
import io.pivotal.kotlindemo.java.accounts.DefaultAccountRepository;
import io.pivotal.kotlindemo.java.clients.ClientRepository;
import io.pivotal.kotlindemo.java.clients.DefaultClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KotlinDemoJavaConfiguration {

    @Bean
    public AccountRepository accountRepository() {
        DefaultAccountRepository accountRepository = new DefaultAccountRepository();
        accountRepository.initialSeed();
        return accountRepository;
    }

    @Bean
    public ClientRepository clientRepository() {
        DefaultClientRepository clientRepository = new DefaultClientRepository();
        clientRepository.initialSeed();
        return clientRepository;
    }

}
