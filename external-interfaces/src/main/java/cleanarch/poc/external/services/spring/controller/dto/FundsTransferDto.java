package cleanarch.poc.external.services.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import cleanarch.poc.domain.model.BankAccount;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundsTransferDto {
    BankAccount from;
    BankAccount to;
    double amount;
}
