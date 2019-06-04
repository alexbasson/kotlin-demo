package io.pivotal.kotlindemo.kotlin.accounts

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/accounts")
class AccountsController(private val accountRepository: AccountRepository) {

    @GetMapping
    fun getAll() = ResponseEntity.ok(accountRepository.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Account> {
        try {
            return ResponseEntity.ok(accountRepository.findById(id))
        } catch (e: NoAccountFoundException) {
            return ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun create(@RequestBody createAccountRequest: CreateAccountRequest) =
        ResponseEntity(accountRepository.create(createAccountRequest), HttpStatus.CREATED)

}
