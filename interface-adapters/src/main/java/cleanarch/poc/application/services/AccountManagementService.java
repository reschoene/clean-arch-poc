package cleanarch.poc.application.services;

import lombok.RequiredArgsConstructor;
import cleanarch.poc.domain.model.BankAccount;
import cleanarch.poc.domain.model.Contract;
import cleanarch.poc.domain.model.Customer;
import cleanarch.poc.domain.services.repository.BankAccountRepository;
import cleanarch.poc.domain.services.repository.ContractRepository;
import cleanarch.poc.domain.services.usecases.AccountManagement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AccountManagementService implements AccountManagement {
    private final BankAccountRepository bankAccountRepository;
    private final ContractRepository contractRepository;

    @Override
    public void create(BankAccount account) {
        bankAccountRepository.create(account);
    }

    @Override
    public Optional<BankAccount> getByNumber(long accountNumber) {
        return bankAccountRepository.getByNumber(accountNumber);
    }

    @Override
    public List<BankAccount> getByCustomer(Customer customer) {
        return bankAccountRepository.getByCustomer(customer);
    }

    @Override
    public void update(BankAccount bankAccount) {
        bankAccountRepository.update(bankAccount);
    }

    @Override
    public void delete(BankAccount bankAccount) {
        List<Contract> contracts = contractRepository.getByAccount(bankAccount);

        try {
            for (Contract c : contracts) {
                c.setEndDate(LocalDate.now());
                contractRepository.update(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
