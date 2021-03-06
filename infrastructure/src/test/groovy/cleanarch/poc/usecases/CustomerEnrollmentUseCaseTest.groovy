package cleanarch.poc.usecases

import cleanarch.poc.domainentities.model.BankAccount
import cleanarch.poc.domainentities.model.Contract
import cleanarch.poc.domainentities.model.Customer
import cleanarch.poc.usecases.repository.BankAccountRepository
import cleanarch.poc.usecases.repository.ContractRepository
import cleanarch.poc.usecases.repository.CustomerRepository
import spock.lang.Specification

import java.time.LocalDate

class CustomerEnrollmentUseCaseTest extends Specification{
    def bankAccountRepository = Mock(BankAccountRepository)
    def contractRepository = Mock(ContractRepository)
    def customerRepository = Mock(CustomerRepository)
    def customerEnrollmentUseCase = new CustomerEnrollmentUseCase(contractRepository, customerRepository, bankAccountRepository)

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
            def contract = customerEnrollmentUseCase.enrollNewCustomer(new Customer())
        then:
            contract
            contract.get().getId() == CONTRACT_ID
            contract.get().getCustomer().getId() == CUSTOMER_ID
            contract.get().getAccount().getNumber() == ACCOUNT_ID
    }

    def "unEnrollCustomer set customers contracts final date and returns its contracts"(){
        given:
            def contractList = List.of(Contract.builder().id(1).build())
            contractRepository.getByCustomer(_ as Customer) >> contractList
        when:
            def retContractList = customerEnrollmentUseCase.unEnrollCustomer(new Customer())
        then:
            retContractList.get(0).getEndDate() == LocalDate.now()
            noExceptionThrown()
    }
}
