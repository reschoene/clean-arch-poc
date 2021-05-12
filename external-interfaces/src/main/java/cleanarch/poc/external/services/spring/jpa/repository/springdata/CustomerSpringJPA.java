package cleanarch.poc.external.services.spring.jpa.repository.springdata;

import cleanarch.poc.external.services.spring.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSpringJPA extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByCpf(String cpf);
    List<CustomerEntity> findByCnpj(String cnpj);
}
