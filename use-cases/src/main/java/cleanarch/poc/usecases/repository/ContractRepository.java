package cleanarch.poc.usecases.repository;

import cleanarch.poc.domainentities.model.BankAccount;
import cleanarch.poc.domainentities.model.Contract;
import cleanarch.poc.domainentities.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ContractRepository {
    long create(Contract contract);
    Optional<Contract> getById(long id);
    List<Contract> getByCustomer(Customer customer);
    List<Contract> getByAccount(BankAccount bankAccount);
    void update(Contract contract);
    void delete(Contract contract);
}
