package cleanarch.poc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Address {
    private String zipCode;
    private String street;
    private int number;
}
