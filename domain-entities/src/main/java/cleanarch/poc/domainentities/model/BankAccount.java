package cleanarch.poc.domainentities.model;

import cleanarch.poc.domainentities.exception.DepositException;
import cleanarch.poc.domainentities.exception.WithdrawException;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BankAccount {
    private long number;
    private Customer owner;
    private double balance;

    public double withdraw(double value) throws WithdrawException {
        if(balance < value )
            throw new WithdrawException("Current balance is less than given amount");

        balance -= value;

        return balance;
    }

    public double deposit(double value) throws DepositException {
        if (value <= 0)
            throw new DepositException("Deposit value must be positive");

        balance += value;

        return balance;
    }
}
