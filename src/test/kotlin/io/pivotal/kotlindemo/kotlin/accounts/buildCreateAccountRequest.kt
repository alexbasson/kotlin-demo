package io.pivotal.kotlindemo.kotlin.accounts

import java.util.*

private val random = Random()

fun buildCreateAccountRequest(
    clientId: Long = random.nextLong(),
    number: String = "some account number ${random.nextInt()}",
    fullname: String = "some account full name ${random.nextInt()}",
    nickname: String = "some account nickname ${random.nextInt()}",
    type: AccountType = AccountType.CHECKING
) = CreateAccountRequest(
    clientId = clientId,
    number = number,
    fullname = fullname,
    nickname = nickname,
    type = type
)
