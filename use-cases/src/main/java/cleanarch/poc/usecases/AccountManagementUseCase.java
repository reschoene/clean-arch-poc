package cleanarch.poc.usecases;

import cleanarch.poc.domainentities.model.BankAccount;
import cleanarch.poc.domainentities.model.Contract;
import cleanarch.poc.domainentities.model.Customer;
import cleanarch.poc.usecases.repository.BankAccountRepository;
import cleanarch.poc.usecases.repository.ContractRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AccountManagementUseCase {
    private final BankAccountRepository bankAccountRepository;
    private final ContractRepository contractRepository;

    public void create(BankAccount account) {
        bankAccountRepository.create(account);
    }

    public Optional<BankAccount> getByNumber(long accountNumber) {
        return bankAccountRepository.getByNumber(accountNumber);
    }

    public List<BankAccount> getByCustomer(Customer customer) {
        return bankAccountRepository.getByCustomer(customer);
    }

    public void update(BankAccount bankAccount) {
        bankAccountRepository.update(bankAccount);
    }

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
