package cleanarch.poc.domain.model;

public interface AccountTransferService {
    String transfer(double amount, BankAccount from, BankAccount to);
}
