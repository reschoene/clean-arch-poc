package cleanarch.poc.interfaceadapters.controllers;

import cleanarch.poc.interfaceadapters.controllers.dto.FundsTransferDto;
import cleanarch.poc.usecases.FundsTransferUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundsTransferControllerAdapter {
    private final FundsTransferUseCase transferService;

    public String transfer(FundsTransferDto fundsTransferDto){
        return transferService.transfer(fundsTransferDto.getAmount(), fundsTransferDto.getFrom(), fundsTransferDto.getTo());
    }
}
