package io.pivotal.kotlindemo.kotlin.clients

data class CreateClientRequest(
    val firstName: String,
    val lastName: String,
    val email: String
)
