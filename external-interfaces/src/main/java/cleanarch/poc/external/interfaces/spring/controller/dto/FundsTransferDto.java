package cleanarch.poc.external.interfaces.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import cleanarch.poc.domain.entities.model.BankAccount;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundsTransferDto {
    BankAccount from;
    BankAccount to;
    double amount;
}
