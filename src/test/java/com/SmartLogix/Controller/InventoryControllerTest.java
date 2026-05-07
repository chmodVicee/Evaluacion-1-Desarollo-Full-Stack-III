package com.SmartLogix.Controller;

import com.SmartLogix.Model.Inventory;
import com.SmartLogix.Repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

    @Mock
    private InventoryRepository repository; // Simulamos la base de datos (Mock)

    @InjectMocks
    private InventoryController inventoryController; // Inyecta el mock en el controlador real

    @Test
    void testGetStock_Found() throws Exception {
        // 1. Preparar datos de prueba (Arrange)
        Inventory mockInventory = new Inventory(1L, "PROD-1", 150, "ALM-1");
        Mockito.when(repository.findByProductoCodigoAndAlmacenCodigo("PROD-1", "ALM-1"))
                .thenReturn(Optional.of(mockInventory));

        // 2. Ejecutar (Act): Llamamos al método de Java directamente
        ResponseEntity<Inventory> response = inventoryController.getStock("PROD-1", "ALM-1");

        // 3. Validar resultados (Assert)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("PROD-1", response.getBody().getProductoCodigo());
        assertEquals(150, response.getBody().getStock());
        assertEquals("ALM-1", response.getBody().getAlmacenCodigo());
    }

    @Test
    void testGetStock_NotFound() throws Exception {
        Mockito.when(repository.findByProductoCodigoAndAlmacenCodigo("PROD-X", "ALM-X"))
                .thenReturn(Optional.empty());

        ResponseEntity<Inventory> response = inventoryController.getStock("PROD-X", "ALM-X");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllStock() throws Exception {
        Inventory inv1 = new Inventory(1L, "P1", 10, "A1");
        Inventory inv2 = new Inventory(2L, "P2", 20, "A2");
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(inv1, inv2));

        ResponseEntity<List<Inventory>> response = inventoryController.getAllStock();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("P1", response.getBody().get(0).getProductoCodigo());
        assertEquals("P2", response.getBody().get(1).getProductoCodigo());
    }

    @Test
    void testAddProduct() throws Exception {
        Inventory newInventory = new Inventory(null, "PROD-3", 50, "ALM-3");
        Inventory savedInventory = new Inventory(3L, "PROD-3", 50, "ALM-3");

        Mockito.when(repository.save(Mockito.any(Inventory.class))).thenReturn(savedInventory);

        ResponseEntity<Inventory> response = inventoryController.addProduct(newInventory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3L, response.getBody().getId());
        assertEquals("PROD-3", response.getBody().getProductoCodigo());
    }
}