package cleanarch.poc.external.interfaces.spring.controller;

import lombok.RequiredArgsConstructor;
import cleanarch.poc.domain.entities.model.AccountTransferService;
import cleanarch.poc.external.interfaces.spring.controller.dto.FundsTransferDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funds-transfer")
public class FundsTransferServiceController {
    private final AccountTransferService transferService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody FundsTransferDto fundsTransferDto){
        String res = transferService.transfer(fundsTransferDto.getAmount(), fundsTransferDto.getFrom(), fundsTransferDto.getTo());
        if ("".equals(res))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(res);
    }
}
