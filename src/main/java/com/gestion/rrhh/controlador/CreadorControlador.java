package com.gestion.rrhh.controlador;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.gestion.rrhh.modelo.Empleado;
import com.gestion.rrhh.modelo.EmpleadoTecnico;
import com.gestion.rrhh.servicio.CrearEmpleadoServicio;
import com.gestion.rrhh.modelo.EmpleadoEjecutivo;




@ManagedBean
@SessionScoped
public class CreadorControlador {
	
	private CrearEmpleadoServicio creadorServicio = new CrearEmpleadoServicio();
	
    private List<Empleado> empleado = new ArrayList<>();
    
    private String nombre;

	private String primerApellido;
	
	private String nif;
	
	private double salario;
	
	private String puesto;
	
	private String cualificaciones;
	
	private String tipoEmpleado;
	
	private boolean mostrarCualificaciones = false;

	private List<String> cualif = new ArrayList<>();
	
	public boolean agregar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		for(Empleado empl : empleado){
			if(empl.getNif().equalsIgnoreCase(nif)){
				if(facesContext != null){
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Agregado:", "Ya se ha agregado un Empleado con este Nif: " + nif);
					facesContext.addMessage("crearForm:mensajes", message);
				}else{
					System.out.println("Ya se ha agregado un Empleado con este Nif: " + nif);
				}
				return false;
			}
		}
		if(validarEmpleado()){
			if ("Tecnico".equals(tipoEmpleado)) {
				agregarCualficacion();
				empleado.add(new EmpleadoTecnico(nif.toUpperCase(), nombre, primerApellido, puesto, salario, cualif));
				limpiarCampos();
	        }else if("Ejecutivo".equals(tipoEmpleado)) {
				empleado.add(new EmpleadoEjecutivo(nif.toUpperCase(), nombre, primerApellido, puesto, salario));
				limpiarCampos();
	        }
			if(facesContext != null){
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Agregado:", "Empleado agregado.");
				facesContext.addMessage("crearForm:mensajes", message);
			}else{
				System.out.println("Empleado agregado.");
			}
			return true;

		}
		return false;

        
    }

    public void crear() throws URISyntaxException, IOException {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			agregar();
		}
    	if(!empleado.isEmpty()){
			if (creadorServicio.crear(empleado)) {
				empleado.clear();
				limpiarCampos();

			}
		}else{
			if(facesContext != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error:", "No se ha agregado ningun empleado.");
				facesContext.addMessage("crearForm:mensajes", message);
			}else{
				System.out.println("No se ha agregado ningun empleado.");
			}
		}
    }

    public void agregarCualficacion(){
		if(!cualificaciones.isEmpty())
			cualif.add(cualificaciones);
		cualificaciones = null;
	}

    public void handleTipoEmpleadoChange() {
        setMostrarCualificaciones("Tecnico".equals(tipoEmpleado));
    }

    private boolean validarEmpleado(){

		FacesContext facesContext = FacesContext.getCurrentInstance();
		boolean resultado = true;
		if(tipoEmpleado.isEmpty()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Tipo Empleado Obligatorio:", "Debe rellenar el Tipo de Empleado.");
			facesContext.addMessage("crearForm:mensajes", message);
			resultado = false;
		}
    	if(nombre.isEmpty()) {
    		if(facesContext != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre Obligatorio:", "Debe rellenar el nombre.");
				facesContext.addMessage("crearForm:mensajes", message);
			}else{
    			System.out.println("Debe rellenar el nombre.");
			}
            resultado = false;
    	}
    	if(primerApellido.isEmpty()) {
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Apellido Obligatorio:", "Debe rellenar el apellido.");
            facesContext.addMessage("crearForm:mensajes", message);
            resultado = false;
    	}
    	if(nif.isEmpty()) {
			if(facesContext != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "NIF Obligatorio:", "Debe rellenar el nif.");
				facesContext.addMessage("crearForm:mensajes", message);
			}else{
				System.out.println("Debe rellenar el nif.");
			}
            resultado = false;
    	}else if(!validarNif()) {
			if(facesContext != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "NIF Incorrecto:", "El Nif no es valido.");
				facesContext.addMessage("crearForm:mensajes", message);
			}else{
				System.out.println("El Nif no es valido.");
			}
            resultado = false;
    	}
    	if(puesto.isEmpty()) {
			if(facesContext != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Puesto Obligatorio:", "Debe rellenar el puesto.");
				facesContext.addMessage("crearForm:mensajes", message);
			}else{
				System.out.println("Debe rellenar el puesto.");
			}
            resultado = false;
    	}

    	return resultado;

    }

    private boolean validarNif() throws RuntimeException{
        String patronNIF = "^[0-9XYZ][0-9]{7,8}[TRWAGMYFPDXBNJZSQVHLCKE]$";

        Pattern patron = Pattern.compile(patronNIF);

        Matcher matcher = patron.matcher(nif);

        return matcher.matches();
    }

	private void limpiarCampos() {
        nombre = null;
        primerApellido = null;
        nif = null;
        salario = 0.0;
        puesto = null;
        cualificaciones = null;
        tipoEmpleado = null;
        setMostrarCualificaciones(false);
    }
    
    public List<Empleado> getEmpleado() {
		return empleado;
	}

	public void limpiarListaEmpleado() {
		empleado = new ArrayList<>();
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleado = empleado;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getCualificaciones() {
		return cualificaciones;
	}

	public void setCualificaciones(String cualificaciones) {
		this.cualificaciones = cualificaciones;
	}

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public boolean isMostrarCualificaciones() {
		return mostrarCualificaciones;
	}

	public void setMostrarCualificaciones(boolean mostrarCualificaciones) {
		this.mostrarCualificaciones = mostrarCualificaciones;
	}

    
}
