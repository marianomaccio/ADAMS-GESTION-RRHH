package com.gestion.rrhh.modelo;

import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;

import java.math.BigDecimal;

public class EmpleadoEjecutivo extends Empleado{
    public EmpleadoEjecutivo(String nif, String nombre, String apellido, String puesto, BigDecimal salario){
        super(TipoEmpleado.Ejecutivo, nif, nombre, apellido, puesto, salario);
    }
}
