package cleanarch.poc.infrastructure.spring.config;

import cleanarch.poc.infrastructure.spring.jpa.repository.BankAccountJPARepositoryImpl;
import cleanarch.poc.infrastructure.spring.jpa.repository.ContractJPARepositoryImpl;
import cleanarch.poc.infrastructure.spring.jpa.repository.CustomerJPARepositoryImpl;
import cleanarch.poc.infrastructure.spring.jpa.repository.springdata.BankAccountSpringDataRepository;
import cleanarch.poc.infrastructure.spring.jpa.repository.springdata.ContractSpringDataRepository;
import cleanarch.poc.infrastructure.spring.jpa.repository.springdata.CustomerSpringDataRepository;
import cleanarch.poc.interfaceadapters.controllers.AccountManagementControllerAdapter;
import cleanarch.poc.interfaceadapters.controllers.CustomerEnrollmentControllerAdapter;
import cleanarch.poc.interfaceadapters.controllers.FundsTransferControllerAdapter;
import cleanarch.poc.interfaceadapters.repositories.BankAccountRepositoryAdapter;
import cleanarch.poc.interfaceadapters.repositories.ContractJPARepositoryAdapter;
import cleanarch.poc.interfaceadapters.repositories.CustomerJPARepositoryAdapter;
import cleanarch.poc.interfaceadapters.repositories.jpa.BankAccountJPARepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.ContractJPARepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.CustomerJPARepository;
import cleanarch.poc.usecases.AccountManagementUseCase;
import cleanarch.poc.usecases.CustomerEnrollmentUseCase;
import cleanarch.poc.usecases.FundsTransferUseCase;
import cleanarch.poc.usecases.repository.BankAccountRepository;
import cleanarch.poc.usecases.repository.ContractRepository;
import cleanarch.poc.usecases.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("cleanarch.poc.interfaceadapters.repositories.jpa.entities")
@RequiredArgsConstructor
public class BeanConfig {
    private final BankAccountSpringDataRepository bankAccountSpringDataRepository;
    private final CustomerSpringDataRepository customerSpringDataRepository;
    private final ContractSpringDataRepository contractSpringDataRepository;

    @Bean
    public FundsTransferControllerAdapter getFundsTransferControllerAdapter(){
        return new FundsTransferControllerAdapter(getFundsTransferUseCase());
    }

    @Bean
    public AccountManagementControllerAdapter getAccountManagementControllerAdapter(){
        return new AccountManagementControllerAdapter(getAccountManagementUseCase());
    }

    @Bean
    public CustomerEnrollmentControllerAdapter getCustomerEnrollmentControllerAdapter(){
        return new CustomerEnrollmentControllerAdapter(getCustomerEnrollmentUseCase());
    }

    @Bean
    public BankAccountRepository getBankAccountRepository(){
        return new BankAccountRepositoryAdapter(getBankAccountJPARepository());
    }

    @Bean
    public BankAccountJPARepository getBankAccountJPARepository(){
        return new BankAccountJPARepositoryImpl(bankAccountSpringDataRepository);
    }

    @Bean
    public CustomerRepository getCustomerRepository(){
        return new CustomerJPARepositoryAdapter(getCustomerJPARepository());
    }

    @Bean
    public CustomerJPARepository getCustomerJPARepository(){
        return new CustomerJPARepositoryImpl(customerSpringDataRepository);
    }

    @Bean
    public ContractRepository getContractRepository(){
        return new ContractJPARepositoryAdapter(getContractJPARepository());
    }

    @Bean
    public ContractJPARepository getContractJPARepository(){
        return new ContractJPARepositoryImpl(contractSpringDataRepository);
    }

    @Bean
    public FundsTransferUseCase getFundsTransferUseCase(){
        return new FundsTransferUseCase(getBankAccountRepository());
    }

    @Bean
    public AccountManagementUseCase getAccountManagementUseCase(){
        return new AccountManagementUseCase(getBankAccountRepository(), getContractRepository());
    }

    @Bean
    public CustomerEnrollmentUseCase getCustomerEnrollmentUseCase(){
        return new CustomerEnrollmentUseCase(getContractRepository(), getCustomerRepository(), getBankAccountRepository());
    }
}
