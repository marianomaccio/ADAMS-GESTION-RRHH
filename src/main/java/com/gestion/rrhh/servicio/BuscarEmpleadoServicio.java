package com.gestion.rrhh.servicio;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.gestion.rrhh.modelo.Empleado;
import com.gestion.rrhh.modelo.EmpleadoEjecutivo;
import com.gestion.rrhh.modelo.EmpleadoTecnico;
import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;



public class BuscarEmpleadoServicio {


	public List<Empleado> buscar(String nombre, String primerApellido, String nif, String puesto) throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(!nif.isEmpty()) {
			if(!validarNif(nif)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error:", "NIF incorrecto");
                facesContext.addMessage("busquedaForm:mensajes", message);
			}else {
				return busquedaConNif(nif);
				
			}
		}else{
			return busquedaSinNif(nombre, primerApellido, puesto);
		}
		return null;
       
	}
	private List<Empleado> busquedaConNif(String nif) {
	    JSONParser parser = new JSONParser();
	    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("datos.json")) {
	        if (inputStream != null) {
	            InputStreamReader reader = new InputStreamReader(inputStream);
	            Object obj = parser.parse(reader);
	            JSONArray empleados = (JSONArray) obj;
	            for (Object empleadoObj : empleados) {
	                JSONObject empleadoJson = (JSONObject) empleadoObj;
	                if (nif.trim().equalsIgnoreCase((String)empleadoJson.get("nif"))) {
	                    if(TipoEmpleado.Ejecutivo.name().equals(empleadoJson.get("tipoEmpleado"))){
	                    	return List.of(new EmpleadoEjecutivo((String)empleadoJson.get("nif"), (String)empleadoJson.get("nombre"), (String)empleadoJson.get("apellido"), (String)empleadoJson.get("puesto"), new BigDecimal((Double)empleadoJson.get("salario"))));
	                    }
	                    if(TipoEmpleado.Tecnico.name().equals(empleadoJson.get("tipoEmpleado"))){
	                    	return List.of(new EmpleadoTecnico((String)empleadoJson.get("nif"), (String)empleadoJson.get("nombre"), (String)empleadoJson.get("apellido"), (String)empleadoJson.get("puesto"), new BigDecimal((Double)empleadoJson.get("salario")), (JSONArray)empleadoJson.get("cualificaciones")));
	                    	
	                    }
	                }
	            }
	        }
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	    }
	    return new ArrayList<Empleado>();
	}
	private List<Empleado> busquedaSinNif(String nombre, String primerApellido, String puesto) {
		List<Empleado> lista = new  ArrayList<>();
	    JSONParser parser = new JSONParser();
	    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("datos.json")) {
	        if (inputStream != null) {
	            InputStreamReader reader = new InputStreamReader(inputStream);
	            Object obj = parser.parse(reader);
	            JSONArray empleados = (JSONArray) obj;
	            for (Object empleadoObj : empleados) {
	                JSONObject empleadoJson = (JSONObject) empleadoObj;

	                if ((nombre.isEmpty() || nombre.trim().equalsIgnoreCase(((String)empleadoJson.get("nombre")).trim()))
	                        && (primerApellido.isEmpty() || primerApellido.trim().equalsIgnoreCase(((String)empleadoJson.get("apellido")).trim()))
	                        && (puesto.isEmpty() || puesto.trim().equalsIgnoreCase(((String)empleadoJson.get("puesto")).trim()))){
	                	if(TipoEmpleado.Ejecutivo.name().equals(empleadoJson.get("tipoEmpleado"))){
	                    	lista.add(new EmpleadoEjecutivo((String)empleadoJson.get("nif"), (String)empleadoJson.get("nombre"), (String)empleadoJson.get("apellido"), (String)empleadoJson.get("puesto"), new BigDecimal((Double)empleadoJson.get("salario"))));
	                    	
	                    }
	                    if(TipoEmpleado.Tecnico.name().equals(empleadoJson.get("tipoEmpleado"))){
	                    	lista.add( new EmpleadoTecnico((String)empleadoJson.get("nif"), (String)empleadoJson.get("nombre"), (String)empleadoJson.get("apellido"), (String)empleadoJson.get("puesto"), new BigDecimal((Double)empleadoJson.get("salario")), (JSONArray)empleadoJson.get("cualificaciones")));
	                    	
	                    }
	                }
	            }
	        }
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}
	private boolean validarNif(String nif) throws RuntimeException{
        String patronNIF = "^[0-9XYZ][0-9]{7,8}[TRWAGMYFPDXBNJZSQVHLCKE]$";

        Pattern patron = Pattern.compile(patronNIF);

        Matcher matcher = patron.matcher(nif);

        return matcher.matches();
    }
}
