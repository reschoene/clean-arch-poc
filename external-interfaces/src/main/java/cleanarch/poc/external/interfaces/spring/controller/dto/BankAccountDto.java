package cleanarch.poc.external.interfaces.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cleanarch.poc.domain.entities.model.BankAccount;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private long number;
    private CustomerDto owner;
    private double balance;

    public static BankAccountDto fromModel(BankAccount bankAccount){
        if (bankAccount == null)
            return null;

        return BankAccountDto.builder()
                .number(bankAccount.getNumber())
                .owner(CustomerDto.fromModel(bankAccount.getOwner()))
                .balance(bankAccount.getBalance())
                .build();
    }

    public BankAccount toModel(){
        return BankAccount.builder()
                .owner((getOwner() != null ? getOwner().toModel() : null))
                .balance(getBalance())
                .number(getNumber())
                .build();
    }
}
