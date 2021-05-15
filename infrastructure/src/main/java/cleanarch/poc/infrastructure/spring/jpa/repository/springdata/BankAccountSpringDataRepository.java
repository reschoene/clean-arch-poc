package cleanarch.poc.infrastructure.spring.jpa.repository.springdata;

import cleanarch.poc.interfaceadapters.repositories.jpa.entities.BankAccountEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountSpringDataRepository extends JpaRepository<BankAccountEntity, Long> {
    List<BankAccountEntity> findByOwner(CustomerEntity owner);
}
