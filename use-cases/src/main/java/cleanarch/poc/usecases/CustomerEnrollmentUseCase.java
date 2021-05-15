package cleanarch.poc.usecases;

import cleanarch.poc.domainentities.model.BankAccount;
import cleanarch.poc.domainentities.model.Contract;
import cleanarch.poc.domainentities.model.Customer;
import cleanarch.poc.usecases.repository.BankAccountRepository;
import cleanarch.poc.usecases.repository.ContractRepository;
import cleanarch.poc.usecases.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerEnrollmentUseCase {
    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;

    public Optional<Contract> enrollNewCustomer(Customer customer) {
        try{
            var customerId = customerRepository.create(customer);
            customer.setId(customerId);

            BankAccount account = BankAccount.builder()
                    .balance(0)
                    .owner(customer)
                    .build();

            var accountId = bankAccountRepository.create(account);
            account.setNumber(accountId);

            Contract contract = Contract.builder()
                    .startDate(LocalDate.now())
                    .maintenanceFee(10.30)
                    .customer(customer)
                    .account(account)
                    .build();

            var id = contractRepository.create(contract);

            return contractRepository.getById(id);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Contract> unEnrollCustomer(Customer customer) {
        List<Contract> contracts = contractRepository.getByCustomer(customer);

        try {
            for (Contract c : contracts) {
                c.setEndDate(LocalDate.now());
                contractRepository.update(c);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Arrays.asList();
        }

        return contracts;
    }
}
