package cleanarch.poc.interfaceadapters.repositories;

import cleanarch.poc.domainentities.model.BankAccount;
import cleanarch.poc.domainentities.model.Customer;
import cleanarch.poc.interfaceadapters.repositories.jpa.BankAccountJPARepository;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.AddressEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.BankAccountEntity;
import cleanarch.poc.interfaceadapters.repositories.jpa.entities.CustomerEntity;
import cleanarch.poc.usecases.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BankAccountRepositoryAdapter implements BankAccountRepository {
    private final BankAccountJPARepository jpaRepo;

    @Override
    public long create(BankAccount account) {
        var addressEntity = new AddressEntity();
        var customerEntity = new CustomerEntity();
        var bankAccountEntity = new BankAccountEntity();

        if(account.getOwner() != null)
            addressEntity.loadFromModel(account.getOwner().getAddress());

        customerEntity.loadFromModel(account.getOwner());
        bankAccountEntity.loadFromModel(account);

        customerEntity.setAddress(addressEntity);
        bankAccountEntity.setOwner(customerEntity);

        bankAccountEntity.setId(0);

        return jpaRepo.save(bankAccountEntity).getId();
    }

    @Override
    public Optional<BankAccount> getByNumber(long accountNumber) {
        Optional<BankAccountEntity> entity = jpaRepo.findById(accountNumber);
        return entity.map(BankAccountEntity::toModel);
    }

    @Override
    public List<BankAccount> getByCustomer(Customer customer) {
        var addressEntity = new AddressEntity();
        var customerEntity = new CustomerEntity();

        addressEntity.loadFromModel(customer.getAddress());
        customerEntity.loadFromModel(customer);

        customerEntity.setAddress(addressEntity);

        return jpaRepo.findByOwner(customerEntity)
                .stream()
                .map(BankAccountEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BankAccount bankAccount) {
        var optEntity = jpaRepo.findById(bankAccount.getNumber());
        if(optEntity.isPresent()){
            var entity = optEntity.get();
            entity.setId(bankAccount.getNumber());
            entity.setBalance(bankAccount.getBalance());
            jpaRepo.save(entity);
        }
    }

    @Override
    public void delete(BankAccount bankAccount) {
        var optEntity = jpaRepo.findById(bankAccount.getNumber());
        optEntity.ifPresent(jpaRepo::delete);
    }
}
