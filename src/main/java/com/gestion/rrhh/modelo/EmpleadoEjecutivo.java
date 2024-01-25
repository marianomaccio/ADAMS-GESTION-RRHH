package com.gestion.rrhh.modelo;


import java.math.BigDecimal;
import java.util.Random;

import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;


public class EmpleadoEjecutivo extends Empleado{

    private double comisionesAnuales = new Random().nextInt(1851) + 150;

    public EmpleadoEjecutivo(String nif, String nombre, String apellido, String puesto, double salario){
        super(TipoEmpleado.Ejecutivo, nif, nombre, apellido, puesto, salario);

    }

    public double getComisionesAnuales() {
        return comisionesAnuales;
    }

    public void setComisionesAnuales(double comisionesAnuales) {
        this.comisionesAnuales = comisionesAnuales;
    }

    @Override
    public String getDetalleNomina() {
        return super.getNomina().obtenerDetalle() + "\\ Comisiones Anuales: " + comisionesAnuales + " €";
    }

    @Override
    public double getSalarioNeto() {
        return Math.round((super.getNomina().getSalarioNeto()+ comisionesAnuales / 12) * 100.0) / 100.0;
    }
}
