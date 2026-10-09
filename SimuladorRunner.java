package com.ejercicio.mavenproject1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.Locale;

@Component
public class SimuladorRunner implements CommandLineRunner {

    private final TarifaService tarifaService;
    private final double tarifaBase;
    private final double descuentoConfigurado;

    // Inyección limpia a través del constructor
    public SimuladorRunner(
            TarifaService tarifaService,
            @Value("${simulador.tarifa.base:150000.0}") double tarifaBase,
            @Value("${simulador.descuento.promedio:0.10}") double descuentoConfigurado) {
        this.tarifaService = tarifaService;
        this.tarifaBase = tarifaBase;
        this.descuentoConfigurado = descuentoConfigurado;
    }

    @Override
    public void run(String... args) {
        double valorProntoPago = tarifaService.calcularLiquidacion(tarifaBase, descuentoConfigurado);
        double valorConRecargo = tarifaService.calcularRecargo(tarifaBase);

        // Formato para mostrar los valores en formato de moneda ($)
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        System.out.println("\n==================================================");
        System.out.println("  SIMULADOR DE TARIFAS Y LIQUIDACIÓN INSTITUCIONAL ");
        System.out.println("==================================================");
        System.out.println(" Tarifa Base:           " + formatoMoneda.format(tarifaBase));
        System.out.println(" Descuento Aplicado:    " + (descuentoConfigurado * 100) + "%");
        System.out.println(" Recargo Extemporáneo:  15%");
        System.out.println("--------------------------------------------------");
        System.out.println(" Total Pronto Pago:     " + formatoMoneda.format(valorProntoPago));
        System.out.println(" Total Extemporáneo:    " + formatoMoneda.format(valorConRecargo));
        System.out.println("==================================================\n");
    }
}