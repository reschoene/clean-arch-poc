package cleanarch.poc.interfaceadapters.controllers.dto;

import cleanarch.poc.domainentities.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private long id;
    private String firstName;
    private String familyName;
    private String cpf;
    private String cnpj;
    private AddressDto address;

    public static CustomerDto fromModel(Customer customer){
        if (customer == null)
            return null;

        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .familyName(customer.getFamilyName())
                .cpf(customer.getCpf())
                .cnpj(customer.getCnpj())
                .address(AddressDto.fromModel(customer.getAddress()))
                .build();
    }

    public Customer toModel(){
        return Customer.builder()
                .id(getId())
                .firstName(getFirstName())
                .familyName(getFamilyName())
                .cpf(getCpf())
                .cnpj(getCnpj())
                .address((getAddress() != null ? getAddress().toModel() : null))
                .build();
    }
}
