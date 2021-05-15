package cleanarch.poc.usecases;

import lombok.RequiredArgsConstructor;
import cleanarch.poc.domainentities.exception.DepositException;
import cleanarch.poc.domainentities.exception.WithdrawException;
import cleanarch.poc.domainentities.model.BankAccount;
import cleanarch.poc.usecases.repository.BankAccountRepository;

@RequiredArgsConstructor
public class FundsTransferUseCase {
    private final BankAccountRepository bankAccountRepository;

    public String transfer(double amount, BankAccount from, BankAccount to) {
        String result = "";

        try {
            from = bankAccountRepository.getByNumber(from.getNumber()).orElseThrow(() -> new WithdrawException("Account from not found"));
            to = bankAccountRepository.getByNumber(to.getNumber()).orElseThrow(() -> new DepositException("Account to not found"));

            from.withdraw(amount);
            to.deposit(amount);

            bankAccountRepository.update(from);
            bankAccountRepository.update(to);

        } catch (WithdrawException | DepositException e) {
            result = e.getMessage();
        }

        return result;
    }
}
