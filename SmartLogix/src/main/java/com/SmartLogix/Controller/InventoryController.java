package com.SmartLogix.Controller;

import com.SmartLogix.Model.Inventory; // Asegúrate de que esta ruta coincida con tu paquete real
import com.SmartLogix.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryRepository repository;

    @GetMapping("/{productoCodigo}/{almacenCodigo}")
    public ResponseEntity<Inventory> getStock(@PathVariable String productoCodigo, @PathVariable String almacenCodigo) {
        // Buscamos en la base de datos
        Optional<Inventory> inventarioEncontrado = repository.findByProductCodeAndCodigoAlmacen(productoCodigo, almacenCodigo);

        // Forma clásica y segura de retornar la respuesta
        if (inventarioEncontrado.isPresent()) {
            return ResponseEntity.ok(inventarioEncontrado.get()); // Si existe, devolvemos 200 OK con el objeto
        } else {
            return ResponseEntity.notFound().build(); // Si no existe, devolvemos 404 Not Found
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Inventory> updateStock(@RequestBody Inventory request) {
        // Buscamos si ya existe el inventario. Si no existe, creamos uno NUEVO con "new Inventory()"
        Inventory inventory = repository.findByProductCodeAndCodigoAlmacen(request.getProductoCodigo(), request.getAlmacenCodigo())
                .orElse(new Inventory()); // Usamos el @NoArgsConstructor en lugar del builder()

        // Actualizamos los datos manualmente usando los setters de Lombok
        inventory.setProductoCodigo(request.getProductoCodigo());
        inventory.setAlmacenCodigo(request.getAlmacenCodigo());
        inventory.setStock(request.getStock());

        // Guardamos y retornamos
        return ResponseEntity.ok(repository.save(inventory));
    }
}