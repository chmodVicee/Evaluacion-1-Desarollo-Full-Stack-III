package com.SmartLogix.Repository;

import com.SmartLogix.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductCodeAndCodigoAlmacen (String productoCodigo, String almacenCodigo);
}
