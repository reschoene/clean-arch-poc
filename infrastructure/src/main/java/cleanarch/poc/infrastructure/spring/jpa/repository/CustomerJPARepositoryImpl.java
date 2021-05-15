package cleanarch.poc.infrastructure.spring.jpa.repository;

import cleanarch.poc.infrastructure.spring.jpa.repository.springdata.CustomerSpringDataRepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.CustomerJPARepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerJPARepositoryImpl implements CustomerJPARepository {
    private final CustomerSpringDataRepository customerRepo;

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        return customerRepo.save(customerEntity);
    }

    @Override
    public List<CustomerEntity> findByCpf(String cpf) {
        return customerRepo.findByCpf(cpf);
    }

    @Override
    public List<CustomerEntity> findByCnpj(String cnpj) {
        return customerRepo.findByCnpj(cnpj);
    }

    @Override
    public Optional<CustomerEntity> findById(long id) {
        return customerRepo.findById(id);
    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        customerRepo.delete(customerEntity);
    }
}
