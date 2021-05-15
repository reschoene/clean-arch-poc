package cleanarch.poc.interfaceadapters.controllers.dto;

import cleanarch.poc.domainentities.model.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundsTransferDto {
    BankAccount from;
    BankAccount to;
    double amount;
}
