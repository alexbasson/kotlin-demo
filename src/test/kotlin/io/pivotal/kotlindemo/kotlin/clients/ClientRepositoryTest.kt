package io.pivotal.kotlindemo.kotlin.clients

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

abstract class ClientRepositoryTest {
    abstract fun getClientRepository(): ClientRepository

    private lateinit var clientRepository: ClientRepository

    @Before
    fun setup() {
        clientRepository = getClientRepository()
    }

    @Test
    fun `create() returns a new client with a unique id`() {
        val createClientRequest1 = buildCreateClientRequest()

        val client1 = clientRepository.create(createClientRequest1)

        val createClientRequest2 = buildCreateClientRequest()

        assertThat(client1).isEqualToIgnoringGivenFields(
            buildClient(
                firstName = createClientRequest1.firstName,
                lastName = createClientRequest1.lastName,
                email = createClientRequest1.email
            ),
            "id"
        )

        val client2 = clientRepository.create(createClientRequest2)

        assertThat(client2).isEqualToIgnoringGivenFields(
            buildClient(
                firstName = createClientRequest2.firstName,
                lastName = createClientRequest2.lastName,
                email = createClientRequest2.email
            ),
            "id"
        )

        assertThat(client1.id).isNotEqualTo(client2.id)
    }

    @Test
    fun `findAll() returns all clients`() {
        val client1 = clientRepository.create(buildCreateClientRequest())
        val client2 = clientRepository.create(buildCreateClientRequest())

        assertThat(clientRepository.findAll()).containsExactlyInAnyOrder(client1, client2)
    }

    @Test
    fun `findById(), when a client with the given id exists, returns the client with the given id`() {
        val client1 = clientRepository.create(buildCreateClientRequest())
        val client2 = clientRepository.create(buildCreateClientRequest())

        assertThat(clientRepository.findById(client1.id)).isEqualTo(client1)
        assertThat(clientRepository.findById(client2.id)).isEqualTo(client2)
    }

    @Test(expected = NoClientFoundException::class)
    fun `findById(), when no client with the given id exists, throws a NoClientFoundException`() {
        val nonExistantClientId = 12345L
        clientRepository.findById(nonExistantClientId)
    }

    @Test
    fun `findByLastName() returns a possibly empty list of clients with the given last name`() {
        val alexBasson = clientRepository.create(buildCreateClientRequest(lastName = "Basson"))
        val phoebeBasson = clientRepository.create(buildCreateClientRequest(lastName = "Basson"))
        val davidEdwards = clientRepository.create(buildCreateClientRequest(lastName = "Edwards"))

        assertThat(clientRepository.findByLastName("Basson")).containsExactlyInAnyOrder(
            alexBasson,
            phoebeBasson
        )
        assertThat(clientRepository.findByLastName("Edwards")).containsExactlyInAnyOrder(
            davidEdwards
        )
        assertThat(clientRepository.findByLastName("NonExistant")).isEmpty()
    }

    @Test
    fun `findByEmail(), when a client with the given email exists, returns the client with given email`() {
        val alexBasson = clientRepository.create(buildCreateClientRequest(email = "abasson@pivotal.io"))
        val davidEdwards = clientRepository.create(buildCreateClientRequest(email = "dedwards@pivotal.io"))

        assertThat(clientRepository.findByEmail(alexBasson.email)).isEqualTo(alexBasson)
        assertThat(clientRepository.findByEmail(davidEdwards.email)).isEqualTo(davidEdwards)
    }

    @Test
    fun `findByEmail(), when no client with the given email exists, returns null`() {
        assertThat(clientRepository.findByEmail("no.such.client@email.com")).isNull()
    }

}
