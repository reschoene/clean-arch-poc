package cleanarch.poc.interfaceadapters.repositories.jpa.entities;

import cleanarch.poc.domainentities.model.Address;
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
@Table(name = "Address")
public class AddressEntity implements ConvertableEntity<AddressEntity, Address> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String zipCode;
    private String street;
    private int number;

    @Override
    public void loadFromModel(Address address){
        if (address != null){
            zipCode = address.getZipCode();
            street = address.getStreet();
            number = address.getNumber();
        }
    }

    @Override
    public Address toModel(){
        return Address.builder()
                .zipCode(getZipCode())
                .number(getNumber())
                .street(getStreet())
                .build();
    }
}
