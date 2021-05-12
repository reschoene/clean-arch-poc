package cleanarch.poc.domain.entities.model;

public interface AccountTransferService {
    String transfer(double amount, BankAccount from, BankAccount to);
}
