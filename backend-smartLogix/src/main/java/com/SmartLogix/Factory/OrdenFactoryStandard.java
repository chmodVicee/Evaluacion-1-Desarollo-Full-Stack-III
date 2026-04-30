package com.SmartLogix.Factory;

import com.SmartLogix.Model.Orden;
import org.springframework.stereotype.Component;

@Component
public class OrdenFactoryStandard implements OrdenFactory{

    @Override
    public Orden createOrden (String tipo, String productoCodigo, Integer cantidad) {
        Orden orden = new Orden();
        orden.setProductoCodigo(productoCodigo);
        orden.setCantidad(cantidad);
        orden.setTipoOrden(tipo);

        if ("EXPRESS".equalsIgnoreCase(tipo)){
            orden.setEstado("APROVADO");
        }else{
            orden.setEstado("PENDIENTE");
        }

        return orden;
    }
}
