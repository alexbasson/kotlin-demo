package io.pivotal.kotlindemo.kotlin.accounts

interface AccountRepository {
    fun create(createAccountRequest: CreateAccountRequest): Account
    fun findAll(): List<Account>
    fun findById(id: Long): Account
    fun findByClientId(clientId: Long): List<Account>
}
