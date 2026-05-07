package com.SmartLogix.Controller;

import com.SmartLogix.Model.Inventory;
import com.SmartLogix.Service.InventoryService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @Test
    void testGetStock_Found() {
        Inventory mockInventory = new Inventory(1L, "PROD-1", 150, "ALM-1");
        Mockito.when(inventoryService.getStock("PROD-1", "ALM-1")).thenReturn(mockInventory);

        ResponseEntity<Inventory> response = inventoryController.getStock("PROD-1", "ALM-1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("PROD-1", response.getBody().getProductoCodigo());
    }

    @Test
    void testGetAllStock() {
        Inventory inv1 = new Inventory(1L, "P1", 10, "A1");
        Inventory inv2 = new Inventory(2L, "P2", 20, "A2");
        Mockito.when(inventoryService.getAllStock()).thenReturn(Arrays.asList(inv1, inv2));

        ResponseEntity<List<Inventory>> response = inventoryController.getAllStock();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testAddProduct() {
        Inventory newInventory = new Inventory(null, "PROD-3", 50, "ALM-3");
        Inventory savedInventory = new Inventory(3L, "PROD-3", 50, "ALM-3");
        Mockito.when(inventoryService.addProduct(Mockito.any(Inventory.class))).thenReturn(savedInventory);

        ResponseEntity<Inventory> response = inventoryController.addProduct(newInventory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3L, response.getBody().getId());
    }
}