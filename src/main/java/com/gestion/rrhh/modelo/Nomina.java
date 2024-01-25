package com.gestion.rrhh.modelo;

import java.math.BigDecimal;

public class Nomina {

    private double salarioBase;

    private double plusConvenio;

    private double complementos;

    public Nomina(double salarioBase){
        this.salarioBase = salarioBase;
        this.plusConvenio = 150;
        this.complementos = 250;

    }

    public double getSalarioNeto(){
        double porcentajeIRPF = 0.10 + Math.random() * 0.08;
        double salarioNeto = salarioBase - salarioBase * (porcentajeIRPF) + plusConvenio + complementos;
        return salarioNeto;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public String obtenerDetalle(){
        return "Salario Base: " + (salarioBase * 100.0) / 100.0  + " € \\ Plus Convenio: " + plusConvenio +" € \\ Complementos: " + complementos + " € ";
    }
}
