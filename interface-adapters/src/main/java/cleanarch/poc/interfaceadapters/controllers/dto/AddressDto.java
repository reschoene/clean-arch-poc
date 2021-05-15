package cleanarch.poc.interfaceadapters.controllers.dto;

import cleanarch.poc.domainentities.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
