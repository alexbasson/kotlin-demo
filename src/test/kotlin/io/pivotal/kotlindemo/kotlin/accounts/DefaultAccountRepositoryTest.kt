package io.pivotal.kotlindemo.kotlin.accounts

class DefaultAccountRepositoryTest: AccountRepositoryTest() {
    override fun getAccountRepository() = DefaultAccountRepository()
}
