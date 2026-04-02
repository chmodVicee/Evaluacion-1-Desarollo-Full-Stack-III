package com.SmartLogix.Factory;

import com.SmartLogix.Model.Orden;

public interface OrdenFactory {
    Orden createOrden (String tipo, String productoCodigo, Integer cantidad);
}
