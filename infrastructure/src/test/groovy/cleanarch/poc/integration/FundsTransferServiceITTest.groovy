package cleanarch.poc.integration

import cleanarch.poc.domainentities.model.BankAccount
import cleanarch.poc.domainentities.model.Customer
import cleanarch.poc.infrastructure.spring.SpringBootAppLauncher
import cleanarch.poc.interfaceadapters.controllers.dto.FundsTransferDto
import cleanarch.poc.usecases.repository.BankAccountRepository
import cleanarch.poc.usecases.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.transaction.Transactional

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@AutoConfigureMockMvc
@SpringBootTest(classes = [SpringBootAppLauncher.class])
class FundsTransferServiceITTest extends Specification {
    def objectMapper = new ObjectMapper()

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private BankAccountRepository bankAccountRepository

    @Autowired
    private CustomerRepository customerRepository

    @Transactional
    def "POST /funds-transfer transfer a value between to accounts"(){
        given: 'two valid accounts on database'
            def customer = Customer.builder()
                    .firstName("teste")
                    .build()
            customer.setId(customerRepository.create(customer))

            def from = BankAccount.builder()
                    .balance(50)
                    .owner(customer)
                    .build()

            def to = BankAccount.builder()
                    .balance(100)
                    .build()

            from.setNumber(bankAccountRepository.create(from))
            to.setNumber(bankAccountRepository.create(to))

            def fundsTransferDto = new FundsTransferDto(from, to, 30)
        when: "controller receives a POST request to /funds-transfer endpoint"
            def resActions = mockMvc.perform(post("/funds-transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(fundsTransferDto)))
            def response = resActions.andReturn().response
        then: "returns a OK response (200)"
            response.status == OK.value()
    }

    @Transactional
    def "POST /funds-transfer cannot transfer a value when account does not exist"(){
        given: 'two accounts not present on database'
            def from = BankAccount.builder()
                    .balance(50)
                    .build()

            def to = BankAccount.builder()
                    .balance(100)
                    .build()

            def fundsTransferDto = new FundsTransferDto(from, to, 30)
        when: "controller receives a POST request to /funds-transfer endpoint"
            def resActions = mockMvc.perform(post("/funds-transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(fundsTransferDto)))
            def response = resActions.andReturn().response
        then: "returns a BAD_REQUEST response (400)"
            response.status == BAD_REQUEST.value()
            response.contentAsString == 'Account from not found'
    }

    @Transactional
    def "POST /funds-transfer cannot transfer a value bigger than its account origin"(){
        given: 'two valid accounts on database'
            def customer = Customer.builder()
                    .firstName("teste")
                    .build()
            customer.setId(customerRepository.create(customer))

            def from = BankAccount.builder()
                    .balance(50)
                    .owner(customer)
                    .build()

            def to = BankAccount.builder()
                    .balance(100)
                    .owner(customer)
                    .build()

            from.setNumber(bankAccountRepository.create(from))
            to.setNumber(bankAccountRepository.create(to))

            def fundsTransferDto = new FundsTransferDto(from, to, 80)
        when: "controller receives a POST request to /funds-transfer endpoint"
            def resActions = mockMvc.perform(post("/funds-transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(fundsTransferDto)))
            def response = resActions.andReturn().response
        then: "returns a BAD_REQUEST response (400)"
            response.status == BAD_REQUEST.value()
            response.contentAsString == 'Current balance is less than given amount'
    }
}
