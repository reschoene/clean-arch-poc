package cleanarch.poc.external.interfaces.spring.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cleanarch.poc.domain.entities.model.Contract;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Contract")
public class ContractEntity implements ConvertableEntity<ContractEntity, Contract>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double maintenanceFee;

    @OneToOne
    @JoinColumn(name = "number", referencedColumnName = "id")
    private BankAccountEntity account;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private CustomerEntity customer;

    @Override
    public void loadFromModel(Contract contract){
        if (contract != null){
            id = contract.getId();
            startDate = contract.getStartDate();
            endDate = contract.getEndDate();
            maintenanceFee = contract.getMaintenanceFee();
        }
    }

    @Override
    public Contract toModel(){
        return Contract.builder()
                .id(getId())
                .customer((getCustomer() != null ? getCustomer().toModel(): null))
                .account((getAccount() != null ? getAccount().toModel(): null))
                .startDate(getStartDate())
                .endDate(getEndDate())
                .maintenanceFee(getMaintenanceFee())
                .build();
    }
}
