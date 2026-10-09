package com.ejercicio.mavenproject1;

import org.springframework.stereotype.Service;

@Service
public class TarifaService {

    private static final double PORCENTAJE_RECARGO_DEFAULT = 0.15; // 15% de recargo

    /**
     * Calcula la liquidación total aplicando un porcentaje de descuento.
     */
    public double calcularLiquidacion(double tarifaBase, double porcentajeDescuento) {
        return tarifaBase * (1.0 - porcentajeDescuento);
    }

    /**
     * Calcula la liquidación con recargo por pago extemporáneo.
     */
    public double calcularRecargo(double tarifaBase) {
        return tarifaBase * (1.0 + PORCENTAJE_RECARGO_DEFAULT);
    }
}