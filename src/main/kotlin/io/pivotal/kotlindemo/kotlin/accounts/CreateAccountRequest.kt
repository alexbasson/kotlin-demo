package io.pivotal.kotlindemo.kotlin.accounts

data class CreateAccountRequest(
    val clientId: Long,
    val number: String,
    val fullname: String,
    val nickname: String,
    val type: AccountType
)
