package cleanarch.poc.interfaceadapters.repositories;

import cleanarch.poc.domainentities.model.Customer;
import cleanarch.poc.interfaceadapters.repositories.jpa.CustomerJPARepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.AddressEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;
import cleanarch.poc.usecases.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerJPARepositoryAdapter implements CustomerRepository {
    private final CustomerJPARepository jpaRepo;

    @Override
    public long create(Customer customer) {
        var addressEntity = new AddressEntity();
        var customerEntity = new CustomerEntity();

        addressEntity.loadFromModel(customer.getAddress());
        customerEntity.loadFromModel(customer);

        customerEntity.setAddress(addressEntity);

        customerEntity.setId(0);
        return jpaRepo.save(customerEntity).getId();
    }

    @Override
    public Optional<Customer> getByCpf(String cpf) {
        return jpaRepo.findByCpf(cpf)
                .stream()
                .map(CustomerEntity::toModel)
                .findFirst();
    }

    @Override
    public Optional<Customer> getByCnpj(String cnpj) {
        return jpaRepo.findByCnpj(cnpj)
                .stream()
                .map(CustomerEntity::toModel)
                .findFirst();
    }

    @Override
    public void update(Customer customer) {
        var optEntity = jpaRepo.findById(customer.getId());
        if(optEntity.isPresent()){
            var entity = optEntity.get();
            entity.setCnpj(customer.getCnpj());
            entity.setCpf(customer.getCpf());
            entity.setFirstName(customer.getFirstName());
            entity.setFamilyName(customer.getFamilyName());
            jpaRepo.save(entity);
        }
    }

    @Override
    public void delete(Customer customer) {
        var optEntity = jpaRepo.findById(customer.getId());
        optEntity.ifPresent(jpaRepo::delete);
    }
}
