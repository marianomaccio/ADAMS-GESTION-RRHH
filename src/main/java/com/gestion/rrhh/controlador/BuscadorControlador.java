package com.gestion.rrhh.controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.gestion.rrhh.modelo.Empleado;
import com.gestion.rrhh.servicio.BuscarEmpleadoServicio;

@ManagedBean
@SessionScoped
public class BuscadorControlador {
	
	private BuscarEmpleadoServicio buscarEmpleadoServicio;
	
	private List<Empleado> resultados;
	
	private String nombre;
	
	private String primerApellido;
	
	private String nif;
	
	private String puesto;
	
	public void buscar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		buscarEmpleadoServicio = new BuscarEmpleadoServicio();
        
        try {
        	resultados = buscarEmpleadoServicio.buscar(nombre, primerApellido, nif, puesto);
	        if(resultados.size() > 0) {
	        	if(facesContext != null) {
					ExternalContext externalContext = facesContext.getExternalContext();
					externalContext.getFlash().put("resultados", resultados);
					externalContext.redirect("resultados.xhtml");
				}else{
	        		for(Empleado empleado : resultados)
	        			System.out.println(empleado.toString()+"\n");
				}
		        
		        
	        }else {
				if(facesContext != null) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda:", "No se han encontrado resultados");
					facesContext.addMessage("busquedaForm:mensajes", message);
				}else{
					System.out.println("No se han encontrado resultados");
				}
	        }
        } catch (Exception e) {
           e.printStackTrace();
        }
        	
        limpiar();
    }
		
	

	private void limpiar() {
		setNif(null);
		setNombre(null);
		setPrimerApellido(null);
		setPuesto(null);
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public List<Empleado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Empleado> resultados) {
		this.resultados = resultados;
	}
	
	
	

}
