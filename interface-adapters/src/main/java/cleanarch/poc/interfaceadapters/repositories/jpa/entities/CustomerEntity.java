package cleanarch.poc.interfaceadapters.repositories.jpa.entities;

import cleanarch.poc.domainentities.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
public class CustomerEntity implements ConvertableEntity<CustomerEntity, Customer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String familyName;
    private String cpf;
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private AddressEntity address;

    @Override
    public void loadFromModel(Customer customer){
        if (customer != null){
            id = customer.getId();
            firstName = customer.getFirstName();
            familyName = customer.getFamilyName();
            cpf = customer.getCpf();
            cnpj = customer.getCnpj();
        }
    }

    @Override
    public Customer toModel(){
        return Customer.builder()
                .id(getId())
                .firstName(getFirstName())
                .familyName(getFamilyName())
                .cpf(getCpf())
                .cnpj(getCnpj())
                .address((getAddress() != null ? getAddress().toModel(): null))
                .build();
    }
}
