package com.gestion.rrhh.modelo;

import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;

import java.math.BigDecimal;
import java.util.List;

public class EmpleadoTecnico extends Empleado{

    private List<String> cualificaciones;

    public EmpleadoTecnico(String nif, String nombre, String apellido, String puesto, BigDecimal salario, List cualificaciones){
        super(TipoEmpleado.Tecnico, nif, nombre, apellido, puesto, salario);
        this.cualificaciones=cualificaciones;
    }
}
