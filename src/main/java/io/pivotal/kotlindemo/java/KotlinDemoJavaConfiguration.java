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
        return new DefaultAccountRepository();
    }

    @Bean
    public ClientRepository clientRepository() {
        return new DefaultClientRepository();
    }

}
