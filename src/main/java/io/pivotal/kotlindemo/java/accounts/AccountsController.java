package io.pivotal.kotlindemo.java.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    private final AccountRepository accountRepository;

    public AccountsController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(accountRepository.findById(id));
        } catch (NoAccountFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping
//    public ResponseEntity<List<Account>> getByClientId(@RequestParam Long clientId) {
//        return ResponseEntity.ok(accountRepository.findByClientId(clientId));
//    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody CreateAccountRequest createAccountRequest) {
        return new ResponseEntity<Account>(accountRepository.create(createAccountRequest), HttpStatus.CREATED);
    }

}
