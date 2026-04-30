package com.SmartLogix.BFF;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bff")
public class BffController {

    private final BffService bffService;

    public BffController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam String tipo,
                                           @RequestParam String codigoProducto,
                                           @RequestParam Integer cantidad) {
        String response = bffService.createOrderFromFrontend(tipo, codigoProducto, cantidad);
        return ResponseEntity.ok(response);
    }
}
