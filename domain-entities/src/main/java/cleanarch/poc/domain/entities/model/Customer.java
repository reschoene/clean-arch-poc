package cleanarch.poc.domain.entities.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Customer {
    private long id;
    private String firstName;
    private String familyName;
    private String cpf;
    private String cnpj;
    private Address address;
}
