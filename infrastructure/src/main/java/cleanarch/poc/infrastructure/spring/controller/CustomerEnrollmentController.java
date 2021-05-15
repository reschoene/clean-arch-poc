package cleanarch.poc.infrastructure.spring.controller;

import cleanarch.poc.interfaceadapters.controllers.CustomerEnrollmentControllerAdapter;
import cleanarch.poc.interfaceadapters.controllers.dto.ContractDto;
import cleanarch.poc.interfaceadapters.controllers.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer-enrollment")
public class CustomerEnrollmentController {
    private final CustomerEnrollmentControllerAdapter controllerAdapter;

    @PostMapping("/enroll")
    public ResponseEntity<ContractDto> enrollNewCustomer(@RequestBody CustomerDto customerDto){
        var contract = controllerAdapter.enrollNewCustomer(customerDto);
        return ResponseEntity.of(contract);
    }

    @PostMapping("/un-enroll")
    public ResponseEntity<List<ContractDto>> unEnrollCustomer(@RequestBody CustomerDto customerDto){
        var contractList = controllerAdapter.unEnrollCustomer(customerDto);
        return ResponseEntity.ok(contractList);
    }
}
