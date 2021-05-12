package cleanarch.poc.usecases.repository;

import cleanarch.poc.domain.entities.model.BankAccount;
import cleanarch.poc.domain.entities.model.Customer;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {
    long create(BankAccount account);
    Optional<BankAccount> getByNumber(long accountNumber);
    List<BankAccount> getByCustomer(Customer customer);
    void update(BankAccount bankAccount);
    void delete(BankAccount bankAccount);
}
