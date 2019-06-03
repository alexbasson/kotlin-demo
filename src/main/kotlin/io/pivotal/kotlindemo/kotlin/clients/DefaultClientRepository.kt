package io.pivotal.kotlindemo.kotlin.clients

class DefaultClientRepository: ClientRepository {
    private val clientsMap = HashMap<Long, Client>()

    override fun create(createClientRequest: CreateClientRequest): Client {
        val id = clientsMap.values.size.toLong()
        val client = Client(
            id = id,
            firstName = createClientRequest.firstName,
            lastName = createClientRequest.lastName,
            email = createClientRequest.email
        )
        clientsMap.put(id, client)
        return client
    }

    override fun findAll(): List<Client> {
        return clientsMap.values.toList()
    }

    override fun findById(id: Long): Client {
        return clientsMap.get(id) ?: throw NoClientFoundException()
    }

    override fun findByLastName(lastName: String): List<Client> {
        return clientsMap.values.filter { it.lastName == lastName }
    }

    override fun findByEmail(email: String): Client? {
        return clientsMap.values.find { it.email == email }
    }

}
