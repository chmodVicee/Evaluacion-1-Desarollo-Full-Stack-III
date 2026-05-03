# Smartlogixs - Plataforma Inteligente para la Gestión Logística de eCommerce

*Smartlogix* necesita desarrollar una solución basada en microservicios, la cual debe tener dos capas: **Frontend** moderno, flexible y **Backend** escalable y seguro.

Permitiendo que las PYMEs puedan gestionar sus operaciones logísticas con una mayor eficiencia y reducir los costos operativos.
Es por esto que la solución debe contemplar 3 módulos principales:
- **Gestión de Inventario**: Tiene que estar actualizado en tiempo real los niveles de stock, optimizando la sincronización entre las multiples bodegas y tiendas.
- **Procesamiento de Pedidos**: Automatizar la validación, aprobación y asignación de pedidos, mejorando la trazabilidad reduciendo errores.
- **Coordinación de Envíos**: Mejorar la comunicación con los transportistas y la planificación de las rutas, mejorando los tiempos de entrega.

## Requisitos Tecnicos
Diseña una arquitectura de **Microservicios escalable**, con **patrones de diseño y arquetipos arquitectónicos** los cuales permitan modularizar el sistema:
- **Definir los microservicios clave**, asegurando la separación de responsabilidades y escalabilidad.
- Diseñar una **API Gateway** que pueda gestionar la comunicación entre los microservicios y el frontend.
- Implementar patrones como **Repository Pattern** para la persistencia de datos, **Factory Method** para la creación de instancias y **Circuit Braker** para manejar fallos en la comunicación entre servicios.
- Asegurar que los microservicios sean **escalables y desacoplados**, permitiendo futuras mejoras sin afectar el funcionamiento del sistema.

## Comprobar el funcionamiento del backend + base de datos
Ejecutar la aplicación desde el IDE o desde la consola con: `./mvnw spring-boot:run`.
Una vez el backend esté ejecutandose, procedemos a ingresar a Postman para hacerle peticion POST a este y comprobar su funcionamiento:
- `POST: http://localhost:8080/api/inventory/add`

Con la siguiente estructura en formato JSON:
```
{
    "productoCodigo":"",
    "almacenCodigo":"",
    "stock":{number}
}
```
Una vez nos haya entregado un _201 Created_ haremos la comprobación de este con la petición:
- `GET: http://localhost:8080/api/inventory/all`

Este GET nos devolverá todas las ordenes que haya en el inventario.
