package io.pivotal.kotlindemo.java.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    private final ClientRepository clientRepository;

    public ClientsController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clientRepository.findById(id));
        } catch (NoClientFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping
//    public ResponseEntity<List<Client>> getByLastName(@RequestParam String lastName) {
//        return ResponseEntity.ok(clientRepository.findByLastName(lastName));
//    }
//
//    @GetMapping
//    public ResponseEntity<Client> getByEmail(@RequestParam String email) {
//        Optional<Client> clientOptional = clientRepository.findByEmail(email);
//        if (clientOptional.isPresent()) {
//            return ResponseEntity.ok(clientOptional.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody CreateClientRequest createClientRequest) {
        return ResponseEntity.ok(clientRepository.create(createClientRequest));
    }

}
