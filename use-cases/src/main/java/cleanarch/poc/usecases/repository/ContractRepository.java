package cleanarch.poc.usecases.repository;

import cleanarch.poc.domain.entities.model.BankAccount;
import cleanarch.poc.domain.entities.model.Contract;
import cleanarch.poc.domain.entities.model.Customer;

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
