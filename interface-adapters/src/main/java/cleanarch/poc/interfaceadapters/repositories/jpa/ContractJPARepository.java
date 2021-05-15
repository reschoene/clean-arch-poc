package cleanarch.poc.interfaceadapters.repositories.jpa;

import cleanarch.poc.interfaceadapters.repositories.jpa.entities.BankAccountEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.ContractEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface ContractJPARepository {
    ContractEntity save(ContractEntity contractEntity);

    Optional<ContractEntity> findById(long id);

    List<ContractEntity> findByCustomer(CustomerEntity customerEntity);

    List<ContractEntity> findByAccount(BankAccountEntity bankAccountEntity);

    void delete(ContractEntity contractEntity);
}
