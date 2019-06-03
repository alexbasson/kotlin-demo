package io.pivotal.kotlindemo.kotlin.clients

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clients")
class ClientsController(private val clientRepository: ClientRepository) {

    @GetMapping
    fun getAll() = ResponseEntity.ok(clientRepository.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Client> {
        try {
            return ResponseEntity.ok(clientRepository.findById(id))
        } catch (e: NoClientFoundException) {
            return ResponseEntity.notFound().build()
        }

    }

//    @GetMapping
//    fun getByLastName(@RequestParam lastName: String) =
//        ResponseEntity.ok(clientRepository.findByLastName(lastName))
//
//    @GetMapping
//    fun getByEmail(@RequestParam email: String): ResponseEntity<Client> {
//        val client = clientRepository.findByEmail(email)
//        client ?: return ResponseEntity.notFound().build()
//        return ResponseEntity.ok(client)
//    }

    @PostMapping
    fun create(@RequestBody createClientRequest: CreateClientRequest) =
        ResponseEntity.ok(clientRepository.create(createClientRequest))

}
