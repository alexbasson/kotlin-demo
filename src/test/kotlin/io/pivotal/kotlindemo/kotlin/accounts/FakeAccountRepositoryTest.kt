package io.pivotal.kotlindemo.kotlin.accounts

class FakeAccountRepositoryTest: AccountRepositoryTest() {
    override fun getAccountRepository() = FakeAccountRepository()
}
