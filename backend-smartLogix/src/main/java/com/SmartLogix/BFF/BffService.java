package com.SmartLogix.BFF;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BffService {

    private final RestTemplate restTemplate;

    public BffService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String ORDER_SERVICE_URL = "http://localhost:8080/api/orders";

    @CircuitBreaker(name = "orderServiceBreaker", fallbackMethod = "orderFallBack")
    public String createOrderFromFrontend(String tipo, String codigoProducto, Integer cantidad){
        String url = String.format("%s?tipo=%s&productoCodigo=%s&cantidad=%d",
                ORDER_SERVICE_URL, tipo, codigoProducto, cantidad);
        return restTemplate.postForObject(url, null, String.class);
    }

    public String orderFallBack (String tipo, String productoCodigo, Integer cantidad, Throwable throwable) {
        return "{ \"error\": \"El servicio de pedidos esta inactivo. Intente en otra ocacion\"}";
    }
}
