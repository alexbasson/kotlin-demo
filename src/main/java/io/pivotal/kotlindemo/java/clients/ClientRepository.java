package io.pivotal.kotlindemo.java.clients;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client create(CreateClientRequest createClientRequest);
    List<Client> findAll();
    Client findById(Long id);
    List<Client> findByLastName(String lastName);
    Optional<Client> findByEmail(String email);
}
