package cleanarch.poc.external.services.spring.jpa.repository;

import cleanarch.poc.external.services.spring.jpa.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import cleanarch.poc.domain.model.BankAccount;
import cleanarch.poc.domain.model.Contract;
import cleanarch.poc.domain.model.Customer;
import cleanarch.poc.domain.services.repository.ContractRepository;
import cleanarch.poc.external.services.spring.jpa.entity.BankAccountEntity;
import cleanarch.poc.external.services.spring.jpa.entity.ContractEntity;
import cleanarch.poc.external.services.spring.jpa.entity.CustomerEntity;
import cleanarch.poc.external.services.spring.jpa.repository.springdata.ContractSpringJPA;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ContractJPARepository implements ContractRepository {
    private final ContractSpringJPA jpaRepo;

    @Override
    public long create(Contract contract) {
        var addressEntity = new AddressEntity();
        var customerEntity = new CustomerEntity();
        var bankAccountEntity = new BankAccountEntity();
        var contractEntity = new ContractEntity();

        if(contract.getCustomer() != null)
            addressEntity.loadFromModel(contract.getCustomer().getAddress());

        customerEntity.loadFromModel(contract.getCustomer());
        bankAccountEntity.loadFromModel(contract.getAccount());
        contractEntity.loadFromModel(contract);

        customerEntity.setAddress(addressEntity);
        bankAccountEntity.setOwner(customerEntity);
        contractEntity.setAccount(bankAccountEntity);
        contractEntity.setCustomer(customerEntity);

        contractEntity.setId(0);
        return jpaRepo.save(contractEntity).getId();
    }

    @Override
    public Optional<Contract> getById(long id) {
        return jpaRepo.findById(id).map(ContractEntity::toModel);
    }

    @Override
    public List<Contract> getByCustomer(Customer customer) {
        var addressEntity = new AddressEntity();
        var customerEntity = new CustomerEntity();

        addressEntity.loadFromModel(customer.getAddress());
        customerEntity.loadFromModel(customer);

        customerEntity.setAddress(addressEntity);

        return jpaRepo.findByCustomer(customerEntity)
                .stream()
                .map(ContractEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Contract> getByAccount(BankAccount bankAccount) {
        var addressEntity = new AddressEntity();
        var customerEntity = new CustomerEntity();
        var bankAccountEntity = new BankAccountEntity();

        if(bankAccount.getOwner() != null)
            addressEntity.loadFromModel(bankAccount.getOwner().getAddress());

        customerEntity.loadFromModel(bankAccount.getOwner());
        bankAccountEntity.loadFromModel(bankAccount);

        customerEntity.setAddress(addressEntity);
        bankAccountEntity.setOwner(customerEntity);

        return jpaRepo.findByAccount(bankAccountEntity)
                .stream()
                .map(ContractEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Contract contract) {
        var optEntity = jpaRepo.findById(contract.getId());
        if(optEntity.isPresent()){
            var entity = optEntity.get();
            entity.setStartDate(contract.getStartDate());
            entity.setEndDate(contract.getEndDate());
            entity.setMaintenanceFee(contract.getMaintenanceFee());
            jpaRepo.save(entity);
        }
    }

    @Override
    public void delete(Contract contract) {
        var optEntity = jpaRepo.findById(contract.getId());
        optEntity.ifPresent(jpaRepo::delete);
    }
}
