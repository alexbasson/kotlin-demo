package io.pivotal.kotlindemo.kotlin.clients

class FakeClientRepositoryTest: ClientRepositoryTest() {
    override fun getClientRepository() = FakeClientRepository()
}
