package cleanarch.poc.services

import cleanarch.poc.application.services.AccountManagementService
import cleanarch.poc.domain.model.BankAccount
import cleanarch.poc.domain.model.Contract
import cleanarch.poc.domain.model.Customer
import cleanarch.poc.domain.services.repository.BankAccountRepository
import cleanarch.poc.domain.services.repository.ContractRepository
import spock.lang.Specification

import java.time.LocalDate

class AccountManagementServiceTest extends Specification{
    def bankAccountRepository = Mock(BankAccountRepository)
    def contractRepository = Mock(ContractRepository)
    def accountManagementService = new AccountManagementService(bankAccountRepository, contractRepository)

    def "create does not throws exception"(){
        given:
            bankAccountRepository.create(_ as BankAccount) >> 1
        when:
            accountManagementService.create(new BankAccount())
        then:
            noExceptionThrown()
    }

    def "getByNumber returns successfully a banking account"(){
        given:
            bankAccountRepository.getByNumber(1) >> Optional.of(new BankAccount(1, null, 0))
        when:
            def res = accountManagementService.getByNumber(1)
        then:
            res.isPresent()
            res.get().getNumber() == 1
    }

    def "getByCustomer returns successfully a banking account list"(){
        given:
           bankAccountRepository.getByCustomer(_ as Customer) >> List.of(new BankAccount(1, null, 0))
        when:
            def res = accountManagementService.getByCustomer(new Customer(1, "", "", "", "", null))
        then:
            res
            res.size() == 1
            res.get(0).getNumber() == 1
    }

    def "update does not throws exception"(){
        when:
            accountManagementService.update(new BankAccount())
        then:
            noExceptionThrown()
    }

    def "delete correctly set end date for contract"(){
        given:
            def contractList = List.of(Contract.builder().id(1).build())
            contractRepository.getByAccount(_ as BankAccount) >> contractList
        when:
            accountManagementService.delete(new BankAccount())
        then:
            contractList.get(0).getEndDate() == LocalDate.now()
            noExceptionThrown()
    }
}
