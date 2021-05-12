package cleanarch.poc.domain.services;

import lombok.RequiredArgsConstructor;
import cleanarch.poc.domain.exception.DepositException;
import cleanarch.poc.domain.exception.WithdrawException;
import cleanarch.poc.domain.model.AccountTransferService;
import cleanarch.poc.domain.model.BankAccount;
import cleanarch.poc.domain.services.repository.BankAccountRepository;

@RequiredArgsConstructor
public class FundsTransferService implements AccountTransferService {
    private final BankAccountRepository bankAccountRepository;

    @Override
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
