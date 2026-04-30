package com.SmartLogix.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productoCodigo;
    private Integer cantidad;
    private String estado;
    private String tipoOrden;
}
