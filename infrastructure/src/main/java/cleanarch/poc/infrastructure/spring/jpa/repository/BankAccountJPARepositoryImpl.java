package cleanarch.poc.infrastructure.spring.jpa.repository;

import cleanarch.poc.infrastructure.spring.jpa.repository.springdata.BankAccountSpringDataRepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.BankAccountJPARepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.BankAccountEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BankAccountJPARepositoryImpl implements BankAccountJPARepository {
    private final BankAccountSpringDataRepository jpaRepo;

    @Override
    public BankAccountEntity save(BankAccountEntity bankAccountEntity) {
        return jpaRepo.save(bankAccountEntity);
    }

    @Override
    public List<BankAccountEntity> findByOwner(CustomerEntity customerEntity) {
        return jpaRepo.findByOwner(customerEntity);
    }

    @Override
    public Optional<BankAccountEntity> findById(long number) {
        return jpaRepo.findById(number);
    }

    @Override
    public void delete(BankAccountEntity bankAccountEntity) {
        jpaRepo.delete(bankAccountEntity);
    }
}
