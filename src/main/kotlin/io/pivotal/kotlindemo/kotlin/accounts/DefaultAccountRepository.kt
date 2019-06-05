package io.pivotal.kotlindemo.kotlin.accounts

import org.apache.commons.csv.CSVFormat
import java.io.IOException
import java.io.InputStreamReader

class DefaultAccountRepository : AccountRepository {
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
        val classLoader = javaClass.classLoader
        val inputStream = classLoader.getResourceAsStream("accounts.csv")
        val input = InputStreamReader(inputStream!!)
        try {
            val csvParser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(input)
            for (record in csvParser) {
                val id = record.get("Id").toLong()

                accountsMap[id] = Account(
                    id = id,
                    clientId = record.get("Client Id").toLong(),
                    number = record.get("Number"),
                    fullname = record.get("Full Name"),
                    nickname = record.get("Nickname"),
                    type = AccountType.valueOf(record.get("Type"))
                )
            }
        } catch (e: IOException) {
        }
    }

}
