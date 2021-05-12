package cleanarch.poc.domain.services

import cleanarch.poc.domain.model.BankAccount
import cleanarch.poc.domain.services.repository.BankAccountRepository
import spock.lang.Specification

class FundsTransferServiceTest extends Specification{
    def bankAccountRepository = Mock(BankAccountRepository)

    def "transfer successfully transfer a given value between 2 banking accounts"(){
        given:
            def from = new BankAccount(1, null, 20)
            def to = new BankAccount(2, null, 30)

            bankAccountRepository.getByNumber(1) >> Optional.of(from)
            bankAccountRepository.getByNumber(2) >> Optional.of(to)
            final def transferService = new FundsTransferService(bankAccountRepository)
        when:
            final def res = transferService.transfer(10, from, to)
        then:
            res == ""
    }

    def "transfer returns error message when banking account from was not found"(){
        given:
            def from = new BankAccount(1, null, 20)
            def to = new BankAccount(2, null, 30)

            bankAccountRepository.getByNumber(1) >> Optional.empty()
            bankAccountRepository.getByNumber(2) >> Optional.of(to)
            final def transferService = new FundsTransferService(bankAccountRepository)
        when:
            final def res = transferService.transfer(10, from, to)
        then:
            res == "Account from not found"
    }

    def "transfer returns error message when banking account to was not found"(){
        given:
            def from = new BankAccount(1, null, 20)
            def to = new BankAccount(2, null, 30)

            bankAccountRepository.getByNumber(1) >> Optional.of(from)
            bankAccountRepository.getByNumber(2) >> Optional.empty()
            final def transferService = new FundsTransferService(bankAccountRepository)
        when:
            final def res = transferService.transfer(10, from, to)
        then:
            res == "Account to not found"
    }
}
