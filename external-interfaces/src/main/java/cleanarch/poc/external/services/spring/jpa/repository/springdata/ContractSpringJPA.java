package cleanarch.poc.external.services.spring.jpa.repository.springdata;

import cleanarch.poc.external.services.spring.jpa.entity.BankAccountEntity;
import cleanarch.poc.external.services.spring.jpa.entity.ContractEntity;
import cleanarch.poc.external.services.spring.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractSpringJPA extends JpaRepository<ContractEntity, Long> {
    List<ContractEntity> findByCustomer(CustomerEntity owner);
    List<ContractEntity> findByAccount(BankAccountEntity account);
}
