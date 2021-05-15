package cleanarch.poc.infrastructure.spring.jpa.repository;

import cleanarch.poc.infrastructure.spring.jpa.repository.springdata.ContractSpringDataRepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.ContractJPARepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.BankAccountEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.ContractEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ContractJPARepositoryImpl implements ContractJPARepository {
    private final ContractSpringDataRepository contractRepo;

    @Override
    public ContractEntity save(ContractEntity contractEntity) {
        return contractRepo.save(contractEntity);
    }

    @Override
    public Optional<ContractEntity> findById(long id) {
        return contractRepo.findById(id);
    }

    @Override
    public List<ContractEntity> findByCustomer(CustomerEntity customerEntity) {
        return contractRepo.findByCustomer(customerEntity);
    }

    @Override
    public List<ContractEntity> findByAccount(BankAccountEntity bankAccountEntity) {
        return contractRepo.findByAccount(bankAccountEntity);
    }

    @Override
    public void delete(ContractEntity contractEntity) {
        contractRepo.delete(contractEntity);
    }
}
