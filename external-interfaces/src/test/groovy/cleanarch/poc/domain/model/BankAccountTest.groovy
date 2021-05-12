package cleanarch.poc.domain.model

import cleanarch.poc.domain.entities.exception.DepositException
import cleanarch.poc.domain.entities.exception.WithdrawException
import cleanarch.poc.domain.entities.model.BankAccount
import spock.lang.Specification

class BankAccountTest extends Specification{
    def 'withdraw returns account balance minus given value'() {
        given: 'an account with balance greater than withdraw value'
            final def bankAccount = new BankAccount()
            bankAccount.setBalance(50)
        when: 'an withdraw operation is executed'
            def res = bankAccount.withdraw(20)
        then: 'balance was correct'
            res == 30
    }

    def 'withdraw throws WithdrawException when balance is less than given value'() {
        given: 'an account with balance smaller than withdraw value'
            final def bankAccount = new BankAccount()
            bankAccount.setBalance(50)
        when: 'an withdraw operation is executed'
            def res = bankAccount.withdraw(60)
        then: 'An exception is correctly thrown'
            final WithdrawException exception = thrown()
            exception.message == 'Current balance is less than given amount'
    }

    def 'deposit returns account balance plus given value'() {
        given: 'an account with balance 20'
            final def bankAccount = new BankAccount()
            bankAccount.setBalance(20)
        when: 'an deposit operation is executed'
            def res = bankAccount.deposit(20)
        then: 'balance was correct'
            res == 40
    }

    def 'deposit throws DepositException when given value is not positive'() {
        given: 'an account with balance 20'
            final def bankAccount = new BankAccount()
            bankAccount.setBalance(20)
        when: 'an withdraw operation is executed'
            bankAccount.deposit(0)
        then: 'An exception is correctly thrown'
            final DepositException exception = thrown()
            exception.message == 'Deposit value must be positive'
    }
}
