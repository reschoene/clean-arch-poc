package cleanarch.poc.external.services.spring.jpa.repository.springdata;

import cleanarch.poc.external.services.spring.jpa.entity.BankAccountEntity;
import cleanarch.poc.external.services.spring.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountSpringJPA extends JpaRepository<BankAccountEntity, Long> {
    List<BankAccountEntity> findByOwner(CustomerEntity owner);
}
