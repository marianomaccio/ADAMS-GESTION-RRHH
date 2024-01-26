package com.gestion.rrhh.servicio;

import java.io.*;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gestion.rrhh.modelo.Empleado;
import com.gestion.rrhh.modelo.EmpleadoTecnico;
import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;

public class CrearEmpleadoServicio {
	public boolean crear(List<Empleado> empleados) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		JSONParser parser = new JSONParser();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\datos.json";

		try (FileWriter fileWriter = new FileWriter(filePath);
			 InputStream inputStream = getClass().getClassLoader().getResourceAsStream("datos.json");) {

			Object obj;
			if (inputStream != null && inputStream.available() > 0) {
				InputStreamReader reader = new InputStreamReader(inputStream);
				obj = parser.parse(reader);
			} else {
				obj = new JSONArray();
			}

			JSONArray empleadosJson = (JSONArray) obj;
			int cantEmpleados = empleadosJson.size();
			for (Empleado empleado : empleados) {
				JSONObject object = new JSONObject();
				EmpleadoTecnico empleadoTec;
				object.put("tipoEmpleado", empleado.getTipoEmpleado());
				if (comprobarNifDuplicado(empleadosJson, empleado.getNif()))
					continue;
				object.put("nif", empleado.getNif());
				object.put("nombre", empleado.getNombre());
				object.put("apellido", empleado.getApellido());
				object.put("puesto", empleado.getPuesto());
				object.put("salario", empleado.getSalarioNeto());
				object.put("nomina", empleado.getDetalleNomina());
				if (empleado.getTipoEmpleado().equals(TipoEmpleado.Tecnico.name())) {
					empleadoTec = (EmpleadoTecnico) empleado;
					object.put("cualificaciones", empleadoTec.getCualificaciones());
				}
				empleadosJson.add(object);
			}
			if(empleadosJson.size() == cantEmpleados) {
				return false;
			}
			fileWriter.write(empleadosJson.toJSONString());
			if (facesContext != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion:", "Correcta.");
				facesContext.addMessage("crearForm:mensajes", message);
			} else {
				System.out.println("Creacion Correcta!");
			}
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error al parsear el archivo JSON: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error de entrada/salida al leer el archivo: " + e.getMessage());
		}
		return false;

	}

	private boolean comprobarNifDuplicado(JSONArray empleadosJson, String nif) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!empleadosJson.isEmpty()) {
			for (Object empleadoObj : empleadosJson) {
				JSONObject empleadoNif = (JSONObject) empleadoObj;
				if (empleadoNif.get("nif").equals(nif)) {
					if (facesContext == null) {
						System.out.println("Empleado con Nif: " + nif + " ya existe, por lo que no fue agregado");
					} else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion:", "Empleado con Nif: " + nif + " ya existe, por lo que no fue agregado");
						facesContext.addMessage("mensajesResultado", message);
					}
					return true;
				}
			}
		}
		return false;
	}

}