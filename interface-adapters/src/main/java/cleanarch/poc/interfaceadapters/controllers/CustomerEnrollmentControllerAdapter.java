package cleanarch.poc.interfaceadapters.controllers;

import cleanarch.poc.interfaceadapters.controllers.dto.ContractDto;
import cleanarch.poc.interfaceadapters.controllers.dto.CustomerDto;
import cleanarch.poc.usecases.CustomerEnrollmentUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerEnrollmentControllerAdapter {
    private final CustomerEnrollmentUseCase customerEnrollmentUseCase;

    public Optional<ContractDto> enrollNewCustomer(CustomerDto customerDto){
        var contract = customerEnrollmentUseCase.enrollNewCustomer(customerDto.toModel());
        return contract.map(ContractDto::fromModel);
    }

    public List<ContractDto> unEnrollCustomer(CustomerDto customerDto){
        var contractList = customerEnrollmentUseCase.unEnrollCustomer(customerDto.toModel());
        return contractList.stream().map(ContractDto::fromModel).collect(Collectors.toList());
    }
}
