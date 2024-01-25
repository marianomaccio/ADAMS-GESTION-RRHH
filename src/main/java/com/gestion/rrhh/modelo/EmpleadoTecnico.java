package com.gestion.rrhh.modelo;



import java.math.BigDecimal;
import java.util.List;

import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;


public class EmpleadoTecnico extends Empleado{

    private List<String> cualificaciones;


	public EmpleadoTecnico(String nif, String nombre, String apellido, String puesto, double salario, List<String> cualificaciones){
        super(TipoEmpleado.Tecnico, nif, nombre, apellido, puesto, salario);
        this.cualificaciones=cualificaciones;
    }

    public List<String> getCualificaciones() {
		return cualificaciones;
	}

	public void setCualificaciones(List<String> cualificaciones) {
		this.cualificaciones = cualificaciones;
	}

	@Override
	public String getDetalleNomina() {
		return super.getNomina().obtenerDetalle();
	}

	@Override
	public double getSalarioNeto() {
		return Math.round(super.getNomina().getSalarioNeto() * 100.0) / 100.0;
	}

	@Override
	public String toString(){
		return "NIF: " + getNif() +
				"\nNombre: " + getNombre() +
				"\nApellido: " +getApellido() +
				"\nPuesto: " + getPuesto() +
				"\nSalario Neto:€ " + getSalarioNeto() +
				"\nCualificaciones: " + getCualificaciones() +
				"\nNomina Actual: " + getDetalleNomina();
	}
}
