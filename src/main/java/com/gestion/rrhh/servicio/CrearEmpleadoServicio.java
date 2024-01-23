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


	public boolean crear(List<Empleado> empleados){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		JSONParser parser = new JSONParser();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\datos.json";

		try (FileWriter fileWriter = new FileWriter(filePath);
			 InputStream inputStream = getClass().getClassLoader().getResourceAsStream("datos.json")) {

			InputStreamReader reader = new InputStreamReader(inputStream);
			Object obj = parser.parse(reader);
			JSONArray empleadosJson = (JSONArray) obj;
			if(empleadosJson.isEmpty() && empleados.size() <5) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error:", "Agregar 5 empleados minimo");
				facesContext.addMessage("crearForm:mensajes", message);
				return false;
			}
			for(Empleado empleado : empleados) {
				JSONObject object = new JSONObject();
				EmpleadoTecnico empleadoTec;
				object.put("tipoEmpleado", empleado.getTipoEmpleado());
				object.put("nif", empleado.getNif());
				object.put("nombre", empleado.getNombre());
				object.put("apellido", empleado.getApellido());
				object.put("puesto", empleado.getPuesto());
				object.put("salario", 10000.0);
				if(empleado.getTipoEmpleado().equals(TipoEmpleado.Tecnico.name())) {
					empleadoTec=(EmpleadoTecnico) empleado;
					object.put("cualificaciones", empleadoTec.getCualificaciones());
				}
				empleadosJson.add(object);
			}


			fileWriter.write(empleadosJson.toJSONString());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion:", "Correcta.");
			facesContext.addMessage("crearForm:mensajes", message);
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

}