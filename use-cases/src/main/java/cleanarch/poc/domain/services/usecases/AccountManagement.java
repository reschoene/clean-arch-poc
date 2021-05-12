package cleanarch.poc.domain.services.usecases;

import cleanarch.poc.domain.model.BankAccount;
import cleanarch.poc.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountManagement {
    void create(BankAccount account);
    Optional<BankAccount> getByNumber(long accountNumber);
    List<BankAccount> getByCustomer(Customer customer);
    void update(BankAccount bankAccount);
    void delete(BankAccount bankAccount);
}
