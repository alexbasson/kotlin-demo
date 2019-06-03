package io.pivotal.kotlindemo.java.clients;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultClientRepository implements ClientRepository {
    private Map<Long, Client> clientsMap = new HashMap<>();

    @Override
    public Client create(CreateClientRequest createClientRequest) {
        Long id = (long) clientsMap.values().size();
        Client client = new Client(
                id,
                createClientRequest.getFirstName(),
                createClientRequest.getLastName(),
                createClientRequest.getEmail()
        );
        clientsMap.put(id, client);
        return client;
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clientsMap.values());
    }

    @Override
    public Client findById(Long id) {
        Client client = clientsMap.get(id);
        if (client == null) {
            throw new NoClientFoundException();
        }
        return client;
    }

    @Override
    public List<Client> findByLastName(String lastName) {
        return clientsMap.values()
                .stream()
                .filter(client -> client.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientsMap.values().stream().filter(client -> client.getEmail().equals(email)).findFirst();
    }
}
