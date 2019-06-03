package io.pivotal.kotlindemo.kotlin.accounts

class FakeAccountRepository: AccountRepository {
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

}
