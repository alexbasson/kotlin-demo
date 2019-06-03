package io.pivotal.kotlindemo.kotlin.clients

interface ClientRepository {
    fun create(createClientRequest: CreateClientRequest): Client
    fun findAll(): List<Client>
    fun findById(id: Long): Client
    fun findByLastName(lastName: String): List<Client>
    fun findByEmail(email: String): Client?
}
