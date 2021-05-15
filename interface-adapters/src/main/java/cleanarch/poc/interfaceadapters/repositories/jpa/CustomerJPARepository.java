package cleanarch.poc.interfaceadapters.repositories.jpa;

import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerJPARepository {
    CustomerEntity save(CustomerEntity customerEntity);

    List<CustomerEntity> findByCpf(String cpf);

    List<CustomerEntity> findByCnpj(String cnpj);

    Optional<CustomerEntity> findById(long id);

    void delete(CustomerEntity customerEntity);
}
