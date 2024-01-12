package com.gestion.rrhh.modelo;



import java.math.BigDecimal;
import java.util.List;

import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;


public class EmpleadoTecnico extends Empleado{

    private List<String> cualificaciones;


	public EmpleadoTecnico(String nif, String nombre, String apellido, String puesto, BigDecimal salario, List cualificaciones){
        super(TipoEmpleado.Tecnico, nif, nombre, apellido, puesto, salario);
        this.cualificaciones=cualificaciones;
    }
	
    public List<String> getCualificaciones() {
		return cualificaciones;
	}

	public void setCualificaciones(List<String> cualificaciones) {
		this.cualificaciones = cualificaciones;
	}
}
