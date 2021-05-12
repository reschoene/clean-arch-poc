package cleanarch.poc.external.interfaces.spring.controller;

import lombok.RequiredArgsConstructor;
import cleanarch.poc.usecases.usecases.CustomerEnrollment;
import cleanarch.poc.external.interfaces.spring.controller.dto.ContractDto;
import cleanarch.poc.external.interfaces.spring.controller.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer-enrollment")
public class CustomerEnrollmentController {
    private final CustomerEnrollment customerEnrollment;

    @PostMapping("/enroll")
    public ResponseEntity<ContractDto> enrollNewCustomer(@RequestBody CustomerDto customerDto){
        var contract = customerEnrollment.enrollNewCustomer(customerDto.toModel());
        if (contract != null)
            return ResponseEntity.ok(ContractDto.fromModel(contract));
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/un-enroll")
    public ResponseEntity<List<ContractDto>> unEnrollCustomer(@RequestBody CustomerDto customerDto){
        var contractList = customerEnrollment.unEnrollCustomer(customerDto.toModel());

        if (contractList == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(contractList.stream().map(ContractDto::fromModel).collect(Collectors.toList()));
    }
}
