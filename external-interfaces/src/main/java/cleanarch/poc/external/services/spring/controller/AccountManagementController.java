package cleanarch.poc.external.services.spring.controller;

import lombok.RequiredArgsConstructor;
import cleanarch.poc.domain.services.usecases.AccountManagement;
import cleanarch.poc.external.services.spring.controller.dto.BankAccountDto;
import cleanarch.poc.external.services.spring.controller.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountManagementController {
    private final AccountManagement accountManagement;

    @GetMapping("/{account-number}")
    public ResponseEntity<BankAccountDto> getByAccountNumber(@PathVariable("account-number") long accountNumber){
        var opt = accountManagement.getByNumber(accountNumber);
        return ResponseEntity.of(opt.map(BankAccountDto::fromModel));
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDto>> getByCustomer(@RequestBody CustomerDto customerDto){
        var opt = accountManagement.getByCustomer(customerDto.toModel());
        return ResponseEntity.ok(opt.stream().map(BankAccountDto::fromModel).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<BankAccountDto> create(@RequestBody BankAccountDto bankAccountDto){
        accountManagement.create(bankAccountDto.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<BankAccountDto> update(@RequestBody BankAccountDto bankAccountDto){
        accountManagement.update(bankAccountDto.toModel());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<BankAccountDto> delete(@RequestBody BankAccountDto bankAccountDto){
        accountManagement.delete(bankAccountDto.toModel());
        return ResponseEntity.ok().build();
    }
}
