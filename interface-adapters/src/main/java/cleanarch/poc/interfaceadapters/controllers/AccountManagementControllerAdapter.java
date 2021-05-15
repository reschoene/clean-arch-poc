package cleanarch.poc.interfaceadapters.controllers;

import cleanarch.poc.interfaceadapters.controllers.dto.BankAccountDto;
import cleanarch.poc.interfaceadapters.controllers.dto.CustomerDto;
import cleanarch.poc.usecases.AccountManagementUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccountManagementControllerAdapter {
    private final AccountManagementUseCase accountManagementUseCase;

    public Optional<BankAccountDto> getByAccountNumber(long accountNumber){
        var opt = accountManagementUseCase.getByNumber(accountNumber);
        return opt.map(BankAccountDto::fromModel);
    }

    public List<BankAccountDto> getByCustomer(CustomerDto customerDto){
        var opt = accountManagementUseCase.getByCustomer(customerDto.toModel());
        return opt.stream().map(BankAccountDto::fromModel).collect(Collectors.toList());
    }

    public void create(BankAccountDto bankAccountDto){
        accountManagementUseCase.create(bankAccountDto.toModel());
    }

    public void update(BankAccountDto bankAccountDto){
        accountManagementUseCase.update(bankAccountDto.toModel());
    }

    public void delete(BankAccountDto bankAccountDto){
        accountManagementUseCase.delete(bankAccountDto.toModel());
    }
}
