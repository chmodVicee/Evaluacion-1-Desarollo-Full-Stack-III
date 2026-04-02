package com.SmartLogix.Controller;

import com.SmartLogix.Factory.OrdenFactory;
import com.SmartLogix.Model.Orden;
import com.SmartLogix.Repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenRepository repository;
    private final OrdenFactory ordenFactory;

    @PostMapping
    public ResponseEntity<Orden> procesoOrden(@RequestParam String tipo,
                                              @RequestParam String productoCodigo,
                                              @RequestParam Integer cantidad){
        Orden newOrden = ordenFactory.createOrden(tipo, productoCodigo, cantidad);

        return ResponseEntity.ok(repository.save(newOrden));
    }
}
