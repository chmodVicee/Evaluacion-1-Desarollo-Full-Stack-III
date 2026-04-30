package com.SmartLogix.Repository;

import com.SmartLogix.Model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
