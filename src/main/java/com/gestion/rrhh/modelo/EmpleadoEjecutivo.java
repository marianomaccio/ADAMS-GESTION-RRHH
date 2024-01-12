package com.gestion.rrhh.modelo;


import java.math.BigDecimal;

import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;


public class EmpleadoEjecutivo extends Empleado{
    public EmpleadoEjecutivo(String nif, String nombre, String apellido, String puesto, BigDecimal salario){
        super(TipoEmpleado.Ejecutivo, nif, nombre, apellido, puesto, salario);
    }
}
