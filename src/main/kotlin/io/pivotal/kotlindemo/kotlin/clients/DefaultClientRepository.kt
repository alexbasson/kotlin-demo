package io.pivotal.kotlindemo.kotlin.clients

import org.apache.commons.csv.CSVFormat
import java.io.IOException
import java.io.InputStreamReader

class DefaultClientRepository : ClientRepository {
    private val clientsMap = HashMap<Long, Client>()

    override fun create(createClientRequest: CreateClientRequest): Client {
        val id = clientsMap.values.size.toLong() + 1
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
        val classLoader = javaClass.classLoader
        val inputStream = classLoader.getResourceAsStream("clients.csv")
        val input = InputStreamReader(inputStream!!)
        try {
            val csvParser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(input)
            for (record in csvParser) {
                val id = record.get("Id").toLong()

                clientsMap[id] = Client(
                    id = id,
                    firstName = record.get("First Name"),
                    lastName = record.get("Last Name"),
                    email = record.get("Email")
                )
            }
        } catch (e: IOException) {
        }
    }

}
