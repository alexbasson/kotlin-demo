package io.pivotal.kotlindemo.java.clients;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultClientRepository implements ClientRepository {
    private Map<Long, Client> clientsMap = new HashMap<>();

    public static final Client alice = new Client(1L, "Alice", "Jones", "alice.jones@email.com");
    public static final Client betty = new Client(2L, "Betty", "Smith", "betty.smith@email.com");
    public static final Client charles = new Client(3L, "Charles", "Turner", "charles.turner@email.com");
    public static final Client david = new Client(4L, "David", "Brown", "david.brown@email.com");

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

    public void initialSeed() {
        clientsMap.put(alice.getId(), alice);
        clientsMap.put(betty.getId(), betty);
        clientsMap.put(charles.getId(), charles);
        clientsMap.put(david.getId(), david);
    }
}
