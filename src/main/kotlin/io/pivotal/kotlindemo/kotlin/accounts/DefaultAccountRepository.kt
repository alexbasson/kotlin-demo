package io.pivotal.kotlindemo.kotlin.accounts

import io.pivotal.kotlindemo.kotlin.clients.DefaultClientRepository.Companion.alice
import io.pivotal.kotlindemo.kotlin.clients.DefaultClientRepository.Companion.betty
import io.pivotal.kotlindemo.kotlin.clients.DefaultClientRepository.Companion.charles
import io.pivotal.kotlindemo.kotlin.clients.DefaultClientRepository.Companion.david

class DefaultAccountRepository: AccountRepository {
    private val accountsMap = HashMap<Long, Account>()

    override fun create(createAccountRequest: CreateAccountRequest): Account {
        val id = accountsMap.size.toLong()
        val account = Account(
            id = id,
            clientId = createAccountRequest.clientId,
            number = createAccountRequest.number,
            fullname = createAccountRequest.fullname,
            nickname = createAccountRequest.nickname,
            type = createAccountRequest.type
        )
        accountsMap.put(id, account)
        return account
    }

    override fun findAll(): List<Account> {
        return accountsMap.values.toList()
    }

    override fun findById(id: Long): Account {
        return accountsMap[id] ?: throw NoAccountFoundException()
    }

    override fun findByClientId(clientId: Long): List<Account> {
        return accountsMap.values.filter { it -> it.clientId == clientId }
    }

    fun initialSeed() {
        create(CreateAccountRequest(
            clientId = alice.id,
            number = "A12345",
            fullname = "Low-interest Checking",
            nickname = "My Checking Account",
            type = AccountType.CHECKING
        ))

        create(CreateAccountRequest(
            clientId = alice.id,
            number = "A23456",
            fullname = "High-interest Savings",
            nickname = "My Savings Account",
            type = AccountType.SAVINGS
        ))

        create(CreateAccountRequest(
            clientId = betty.id,
            number = "B34567",
            fullname = "Low-interest Savings",
            nickname = "My Great Savings!",
            type = AccountType.SAVINGS
        ))

        create(CreateAccountRequest(
            clientId = betty.id,
            number = "B45678",
            fullname = "Low-interest Checking",
            nickname = "Checking Account",
            type = AccountType.CHECKING
        ))

        create(CreateAccountRequest(
            clientId = betty.id,
            number = "B56789",
            fullname = "Credit",
            nickname = "Home Equity loan",
            type = AccountType.CREDIT
        ))

        create(CreateAccountRequest(
            clientId = charles.id,
            number = "C87654",
            fullname = "Investment",
            nickname = "mutual funds and stocks",
            type = AccountType.INVESTMENT
        ))

        create(CreateAccountRequest(
            clientId = charles.id,
            number = "C98765",
            fullname = "Low-Interest Checking",
            nickname = "checking",
            type = AccountType.CHECKING
        ))

        create(CreateAccountRequest(
            clientId = charles.id,
            number = "C65432",
            fullname = "Credit",
            nickname = "mortgage",
            type = AccountType.CREDIT
        ))

        create(CreateAccountRequest(
            clientId = david.id,
            number = "D54321",
            fullname = "High-interest Savings",
            nickname = "My savings account",
            type = AccountType.SAVINGS
        ))

        create(CreateAccountRequest(
            clientId = david.id,
            number = "D65432",
            fullname = "Credit",
            nickname = "My mortgage",
            type = AccountType.CREDIT
        ))

        create(CreateAccountRequest(
            clientId = david.id,
            number = "D76543",
            fullname = "Investment",
            nickname = "My investment account",
            type = AccountType.INVESTMENT
        ))
    }

}
