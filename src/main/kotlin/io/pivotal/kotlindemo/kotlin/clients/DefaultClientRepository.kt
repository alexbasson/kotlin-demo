package io.pivotal.kotlindemo.kotlin.clients

class DefaultClientRepository: ClientRepository {
    private val clientsMap = HashMap<Long, Client>()

    companion object {
        val alice = Client(1L, "Alice", "Jones", "alice.jones@email.com")
        val betty = Client(2L, "Betty", "Smith", "betty.smith@email.com")
        val charles = Client(3L, "Charles", "Turner", "charles.turner@email.com")
        val david = Client(4L, "David", "Brown", "david.brown@email.com")
    }

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

    fun initialSeed() {
        clientsMap.put(alice.id, alice)
        clientsMap.put(betty.id, betty)
        clientsMap.put(charles.id, charles)
        clientsMap.put(david.id, david)
    }

}
