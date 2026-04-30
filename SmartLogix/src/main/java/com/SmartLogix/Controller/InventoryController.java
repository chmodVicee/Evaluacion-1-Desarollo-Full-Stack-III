package com.SmartLogix.Controller;

import com.SmartLogix.Model.Inventory; // Asegúrate de que esta ruta coincida con tu paquete real
import com.SmartLogix.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryRepository repository;

    @GetMapping("/{productoCodigo}/{almacenCodigo}")
    public ResponseEntity<Inventory> getStock(@PathVariable String productoCodigo, @PathVariable String almacenCodigo) {

        Optional<Inventory> inventarioEncontrado = repository.findByProductoCodigoAndAlmacenCodigo(productoCodigo, almacenCodigo);
        if (inventarioEncontrado.isPresent()) {
            return ResponseEntity.ok(inventarioEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllStock(){
        List<Inventory> allInventory = repository.findAll();

        return ResponseEntity.ok(allInventory);
    }

    @PostMapping("/add")
    public ResponseEntity<Inventory> addProduct(@RequestBody Inventory inventory) {
        Inventory nuevo = repository.save(inventory);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PostMapping("/bulk-add")
    public ResponseEntity<List<Inventory>> addProducts(@RequestBody List<Inventory> products){
        List<Inventory> guardados = repository.saveAll(products);
        return ResponseEntity.status(201).body(guardados);
    }

    @PostMapping("/update")
    public ResponseEntity<Inventory> updateStock(@RequestBody Inventory request) {
        Inventory inventory = repository.findByProductoCodigoAndAlmacenCodigo(request.getProductoCodigo(), request.getAlmacenCodigo())
                .orElse(new Inventory());
        inventory.setProductoCodigo(request.getProductoCodigo());
        inventory.setAlmacenCodigo(request.getAlmacenCodigo());
        inventory.setStock(request.getStock());

        return ResponseEntity.ok(repository.save(inventory));
    }
}