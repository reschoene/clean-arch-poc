package cleanarch.poc.infrastructure.spring.jpa.repository.springdata;

import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSpringDataRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByCpf(String cpf);
    List<CustomerEntity> findByCnpj(String cnpj);
}
