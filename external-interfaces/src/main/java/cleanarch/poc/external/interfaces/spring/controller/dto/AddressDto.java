package cleanarch.poc.external.interfaces.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cleanarch.poc.domain.entities.model.Address;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private long id;
    private String zipCode;
    private String street;
    private int number;

    public static AddressDto fromModel(Address address){
        if (address == null)
            return null;

        return AddressDto.builder()
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .number(address.getNumber())
                .build();
    }

    public Address toModel(){
        return Address.builder()
                .zipCode(getZipCode())
                .number(getNumber())
                .street(getStreet())
                .build();
    }
}
