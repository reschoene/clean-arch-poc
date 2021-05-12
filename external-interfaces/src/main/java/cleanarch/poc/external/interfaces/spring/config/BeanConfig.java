package cleanarch.poc.external.interfaces.spring.config;

import cleanarch.poc.external.interfaces.spring.jpa.repository.BankAccountJPARepository;
import cleanarch.poc.external.interfaces.spring.jpa.repository.ContractJPARepository;
import cleanarch.poc.external.interfaces.spring.jpa.repository.CustomerJPARepository;
import cleanarch.poc.external.interfaces.spring.jpa.repository.springdata.BankAccountSpringJPA;
import cleanarch.poc.external.interfaces.spring.jpa.repository.springdata.ContractSpringJPA;
import cleanarch.poc.external.interfaces.spring.jpa.repository.springdata.CustomerSpringJPA;
import lombok.RequiredArgsConstructor;
import cleanarch.poc.application.services.AccountManagementService;
import cleanarch.poc.application.services.CustomerEnrollmentService;
import cleanarch.poc.domain.entities.model.AccountTransferService;
import cleanarch.poc.usecases.FundsTransferService;
import cleanarch.poc.usecases.repository.BankAccountRepository;
import cleanarch.poc.usecases.repository.ContractRepository;
import cleanarch.poc.usecases.repository.CustomerRepository;
import cleanarch.poc.usecases.usecases.AccountManagement;
import cleanarch.poc.usecases.usecases.CustomerEnrollment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final BankAccountSpringJPA bankAccountSpringJPA;
    private final CustomerSpringJPA customerSpringJPA;
    private final ContractSpringJPA contractSpringJPA;

    @Bean
    public AccountTransferService getAccountTransferService(){
        return new FundsTransferService(getBankAccountRepository());
    }

    @Bean
    public BankAccountRepository getBankAccountRepository(){
        return new BankAccountJPARepository(bankAccountSpringJPA);
    }

    @Bean
    public CustomerRepository getCustomerRepository(){
        return new CustomerJPARepository(customerSpringJPA);
    }

    @Bean
    public ContractRepository getContractRepository(){
        return new ContractJPARepository(contractSpringJPA);
    }

    @Bean
    public AccountManagement getAccountManagement(){
        return new AccountManagementService(getBankAccountRepository(), getContractRepository());
    }

    @Bean
    public CustomerEnrollment getCustomerEnrollment(){
        return new CustomerEnrollmentService(getContractRepository(), getCustomerRepository(), getBankAccountRepository());
    }
}
