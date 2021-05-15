package cleanarch.poc.infrastructure.spring.controller;

import cleanarch.poc.interfaceadapters.controllers.FundsTransferControllerAdapter;
import cleanarch.poc.interfaceadapters.controllers.dto.FundsTransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funds-transfer")
public class FundsTransferController {
    private final FundsTransferControllerAdapter controllerAdapter;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody FundsTransferDto fundsTransferDto){
        String res = controllerAdapter.transfer(fundsTransferDto);
        if ("".equals(res))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(res);
    }
}
