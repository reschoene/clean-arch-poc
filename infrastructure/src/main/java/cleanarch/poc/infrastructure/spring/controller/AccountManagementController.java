package cleanarch.poc.infrastructure.spring.controller;

import cleanarch.poc.interfaceadapters.controllers.AccountManagementControllerAdapter;
import cleanarch.poc.interfaceadapters.controllers.dto.BankAccountDto;
import cleanarch.poc.interfaceadapters.controllers.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountManagementController {
    private final AccountManagementControllerAdapter controllerAdapter;

    @GetMapping("/{account-number}")
    public ResponseEntity<BankAccountDto> getByAccountNumber(@PathVariable("account-number") long accountNumber){
        return ResponseEntity.of(controllerAdapter.getByAccountNumber(accountNumber));
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDto>> getByCustomer(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(controllerAdapter.getByCustomer(customerDto));
    }

    @PostMapping
    public ResponseEntity<BankAccountDto> create(@RequestBody BankAccountDto bankAccountDto){
        controllerAdapter.create(bankAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<BankAccountDto> update(@RequestBody BankAccountDto bankAccountDto){
        controllerAdapter.update(bankAccountDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<BankAccountDto> delete(@RequestBody BankAccountDto bankAccountDto){
        controllerAdapter.delete(bankAccountDto);
        return ResponseEntity.ok().build();
    }
}
