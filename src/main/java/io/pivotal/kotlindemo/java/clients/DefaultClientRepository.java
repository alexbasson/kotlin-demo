package io.pivotal.kotlindemo.java.clients;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class DefaultClientRepository implements ClientRepository {
    private Map<Long, Client> clientsMap = new HashMap<>();

    @Override
    public Client create(CreateClientRequest createClientRequest) {
        Long id = (long) clientsMap.values().size() + 1;
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
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("clients.csv");
        InputStreamReader input = new InputStreamReader(inputStream);
        try {
            CSVParser csvParser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(input);
            for (CSVRecord record : csvParser) {
                Long id = parseLong(record.get("Id"));
                String firstName = record.get("First Name");
                String lastName = record.get("Last Name");
                String email = record.get("Email");

                Client client = new Client(
                    id,
                    firstName,
                    lastName,
                    email
                );

                clientsMap.put(client.getId(), client);
            }
        } catch (IOException e) {
        }
    }
}
