package cleanarch.poc.external.services.spring.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cleanarch.poc.domain.model.BankAccount;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BankAccount")
public class BankAccountEntity implements ConvertableEntity<BankAccountEntity, BankAccount>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity owner;

    private double balance;

    @Override
    public void loadFromModel(BankAccount bankAccount){
        if (bankAccount != null){
            id = bankAccount.getNumber();
            balance = bankAccount.getBalance();
        }
    }

    @Override
    public BankAccount toModel(){
        return BankAccount.builder()
                .owner((getOwner() != null ? getOwner().toModel(): null))
                .balance(getBalance())
                .number(getId())
                .build();
    }
}
