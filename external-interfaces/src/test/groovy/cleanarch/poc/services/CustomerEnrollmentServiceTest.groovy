package cleanarch.poc.services

import cleanarch.poc.application.services.CustomerEnrollmentService
import cleanarch.poc.domain.entities.model.BankAccount
import cleanarch.poc.domain.entities.model.Contract
import cleanarch.poc.domain.entities.model.Customer
import cleanarch.poc.usecases.repository.BankAccountRepository
import cleanarch.poc.usecases.repository.ContractRepository
import cleanarch.poc.usecases.repository.CustomerRepository
import spock.lang.Specification

import java.time.LocalDate

class CustomerEnrollmentServiceTest extends Specification{
    def bankAccountRepository = Mock(BankAccountRepository)
    def contractRepository = Mock(ContractRepository)
    def customerRepository = Mock(CustomerRepository)
    def accountManagementService = new CustomerEnrollmentService(contractRepository, customerRepository, bankAccountRepository)

    def "enrollNewCustomer successfully enroll a new customer and returns its contract"(){
        given:
            final def CUSTOMER_ID = 1
            final def ACCOUNT_ID = 5
            final def CONTRACT_ID = 9

            customerRepository.create(_ as Customer) >> CUSTOMER_ID
            bankAccountRepository.create(_ as BankAccount) >> ACCOUNT_ID
            contractRepository.create(_ as Contract) >> CONTRACT_ID
            contractRepository.getById(CONTRACT_ID) >> Optional.of(Contract.builder()
                    .id(CONTRACT_ID)
                    .account(BankAccount.builder().number(ACCOUNT_ID).build())
                    .customer(Customer.builder().id(CUSTOMER_ID).build())
                    .build())
        when:
            def contract = accountManagementService.enrollNewCustomer(new Customer())
        then:
            contract
            contract.getId() == CONTRACT_ID
            contract.getCustomer().getId() == CUSTOMER_ID
            contract.getAccount().getNumber() == ACCOUNT_ID
    }

    def "unEnrollCustomer set customers contracts final date and returns its contracts"(){
        given:
            def contractList = List.of(Contract.builder().id(1).build())
            contractRepository.getByCustomer(_ as Customer) >> contractList
        when:
            def retContractList = accountManagementService.unEnrollCustomer(new Customer())
        then:
            retContractList.get(0).getEndDate() == LocalDate.now()
            noExceptionThrown()
    }
}
