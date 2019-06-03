package io.pivotal.kotlindemo.kotlin.clients

class DefaultClientRepositoryTest: ClientRepositoryTest() {
    override fun getClientRepository() = DefaultClientRepository()
}
