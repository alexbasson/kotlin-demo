package io.pivotal.kotlindemo.java.clients;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ClientRepositoryTest {
    public abstract ClientRepository getClientRepository();

    private ClientRepository clientRepository;

    @Before
    public void setup() {
        clientRepository = getClientRepository();
    }

    @Test
    public void create_returnsANewClientWithAUniqueId() {
        CreateClientRequest createClientRequest1 = new CreateClientRequest(
                "Alex",
                "Basson",
                "abasson@pivotal.io"
        );

        Client client1 = clientRepository.create(createClientRequest1);

        assertThat(client1.getId()).isNotNull();
        assertThat(client1).isEqualToIgnoringGivenFields(
                new Client(
                        null,
                        createClientRequest1.getFirstName(),
                        createClientRequest1.getLastName(),
                        createClientRequest1.getEmail()
                ),
                "id"
        );

        CreateClientRequest createClientRequest2 = new CreateClientRequest(
                "David",
                "Edwards",
                "dedwards@pivotal.io"
        );

        Client client2 = clientRepository.create(createClientRequest2);

        assertThat(client2.getId()).isNotNull();
        assertThat(client2).isEqualToIgnoringGivenFields(
                new Client(
                        null,
                        createClientRequest2.getFirstName(),
                        createClientRequest2.getLastName(),
                        createClientRequest2.getEmail()
                ),
                "id"
        );

        assertThat(client1.getId()).isNotEqualTo(client2.getId());
    }

    @Test
    public void findAll_returnsAllClients() {
        CreateClientRequest createClientRequest1 = new CreateClientRequest(
                "Alex",
                "Basson",
                "abasson@pivotal.io"
        );

        Client client1 = clientRepository.create(createClientRequest1);

        CreateClientRequest createClientRequest2 = new CreateClientRequest(
                "David",
                "Edwards",
                "dedwards@pivotal.io"
        );

        Client client2 = clientRepository.create(createClientRequest2);

        assertThat(clientRepository.findAll()).containsExactlyInAnyOrder(client1, client2);
    }

    @Test
    public void findById_whenAClientWithTheGivenIdExists_returnsTheClientWithTheGivenId() {
        CreateClientRequest createClientRequest1 = new CreateClientRequest(
                "Alex",
                "Basson",
                "abasson@pivotal.io"
        );

        Client client1 = clientRepository.create(createClientRequest1);

        CreateClientRequest createClientRequest2 = new CreateClientRequest(
                "David",
                "Edwards",
                "dedwards@pivotal.io"
        );

        Client client2 = clientRepository.create(createClientRequest2);

        assertThat(clientRepository.findById(client1.getId())).isEqualTo(client1);
        assertThat(clientRepository.findById(client2.getId())).isEqualTo(client2);
    }

    @Test(expected = NoClientFoundException.class)
    public void findById_whenNoClientWithTheGivenIdExists_throwsANoClientFoundException() {
        Long nonExistantClientId = 12345L;
        clientRepository.findById(nonExistantClientId);
    }

    @Test
    public void findByLastName_returnsAPossiblyEmptyListOfClientsWithTheGivenLastName() {
        CreateClientRequest createClientRequest1 = new CreateClientRequest(
                "Alex",
                "Basson",
                "abasson@pivotal.io"
        );

        Client alexBasson = clientRepository.create(createClientRequest1);

        CreateClientRequest createClientRequest2 = new CreateClientRequest(
                "David",
                "Edwards",
                "dedwards@pivotal.io"
        );

        Client davidEdwards = clientRepository.create(createClientRequest2);

        CreateClientRequest createClientRequest3 = new CreateClientRequest(
                "Phoebe",
                "Basson",
                "abasson@pivotal.io"
        );

        Client phoebeBasson = clientRepository.create(createClientRequest3);

        assertThat(clientRepository.findByLastName("Basson")).containsExactlyInAnyOrder(
                alexBasson,
                phoebeBasson
        );
        assertThat(clientRepository.findByLastName("Edwards")).containsExactlyInAnyOrder(
                davidEdwards
        );
        assertThat(clientRepository.findByLastName("NonExistant")).isEmpty();
    }

    @Test
    public void findByEmail_whenAClientWithTheGivenEmailExists_returnsAnOptionalClientWithTheGivenEmail() {
        CreateClientRequest createClientRequest1 = new CreateClientRequest(
                "Alex",
                "Basson",
                "abasson@pivotal.io"
        );

        Client alexBasson = clientRepository.create(createClientRequest1);

        CreateClientRequest createClientRequest2 = new CreateClientRequest(
                "David",
                "Edwards",
                "dedwards@pivotal.io"
        );

        Client davidEdwards = clientRepository.create(createClientRequest2);

        assertThat(clientRepository.findByEmail(alexBasson.getEmail())).isEqualTo(Optional.of(alexBasson));
        assertThat(clientRepository.findByEmail(davidEdwards.getEmail())).isEqualTo(Optional.of(davidEdwards));
    }

    @Test
    public void findByEmail_whenNoClientWithTheGivenEmailExists_returnsAnEmptyOptional() {
        assertThat(clientRepository.findByEmail("no.such.client@email.com").isPresent()).isFalse();
    }

}
