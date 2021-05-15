package cleanarch.poc.interfaceadapters.repositories.jpa;

import cleanarch.poc.interfaceadapters.repositories.jpa.entities.BankAccountEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface BankAccountJPARepository {
    BankAccountEntity save(cleanarch.poc.interfaceadapters.repositories.jpa.entities.BankAccountEntity bankAccountEntity);

    List<BankAccountEntity> findByOwner(CustomerEntity customerEntity);

    Optional<BankAccountEntity> findById(long number);

    void delete(BankAccountEntity bankAccountEntity);
}
