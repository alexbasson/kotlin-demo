package io.pivotal.kotlindemo.kotlin.accounts

data class Account(
    val id: Long,
    val clientId: Long,
    val number: String,
    val fullname: String,
    val nickname: String,
    val type: AccountType
)
