package io.pivotal.kotlindemo.java.clients;

import io.pivotal.kotlindemo.java.accounts.Account;
import io.pivotal.kotlindemo.java.accounts.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    private final GetClientOverviews getClientOverviews;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public ClientsController(
        GetClientOverviews getClientOverviews,
        ClientRepository clientRepository,
        AccountRepository accountRepository
    ) {
        this.getClientOverviews = getClientOverviews;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClientOverview>> getAll() {
        return ResponseEntity.ok(getClientOverviews.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clientRepository.findById(id));
        } catch (NoClientFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clientId}/accounts")
    public ResponseEntity<List<Account>> getAccountsForClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(accountRepository.findByClientId(clientId));
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
