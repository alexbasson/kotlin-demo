package io.pivotal.kotlindemo.kotlin.clients

import java.util.*

private val random = Random()

fun buildCreateClientRequest(
    firstName: String = "some first name ${random.nextInt()}",
    lastName: String = "some last name ${random.nextInt()}",
    email: String = "some.email.${random.nextInt()}@email.com"
) = CreateClientRequest(
    firstName = firstName,
    lastName = lastName,
    email = email
)
