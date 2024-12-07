package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/core")
public class CoreController {

    @GetMapping("/payment-statuses")
    public ResponseEntity<PAYMENT_STATUS[]> getPaymentStatusValues(){
        var statuses = PAYMENT_STATUS.values();//.map(Enum::name);
        return ResponseEntity.ok(statuses);
    }
}
