package cleanarch.poc.usecases

import cleanarch.poc.domainentities.model.BankAccount
import cleanarch.poc.usecases.repository.BankAccountRepository
import spock.lang.Specification

class FundsTransferUseCaseTest extends Specification{
    def bankAccountRepository = Mock(BankAccountRepository)

    def "transfer successfully transfer a given value between 2 banking accounts"(){
        given:
            def from = new BankAccount(1, null, 20)
            def to = new BankAccount(2, null, 30)

            bankAccountRepository.getByNumber(1) >> Optional.of(from)
            bankAccountRepository.getByNumber(2) >> Optional.of(to)
            final def transferUseCase = new FundsTransferUseCase(bankAccountRepository)
        when:
            final def res = transferUseCase.transfer(10, from, to)
        then:
            res == ""
    }

    def "transfer returns error message when banking account from was not found"(){
        given:
            def from = new BankAccount(1, null, 20)
            def to = new BankAccount(2, null, 30)

            bankAccountRepository.getByNumber(1) >> Optional.empty()
            bankAccountRepository.getByNumber(2) >> Optional.of(to)
            final def transferUseCase = new FundsTransferUseCase(bankAccountRepository)
        when:
            final def res = transferUseCase.transfer(10, from, to)
        then:
            res == "Account from not found"
    }

    def "transfer returns error message when banking account to was not found"(){
        given:
            def from = new BankAccount(1, null, 20)
            def to = new BankAccount(2, null, 30)

            bankAccountRepository.getByNumber(1) >> Optional.of(from)
            bankAccountRepository.getByNumber(2) >> Optional.empty()
            final def transferUseCase = new FundsTransferUseCase(bankAccountRepository)
        when:
            final def res = transferUseCase.transfer(10, from, to)
        then:
            res == "Account to not found"
    }
}
