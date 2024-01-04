package com.gestion.rrhh.controlador;

import com.gestion.rrhh.modelo.Empleado;

import java.util.List;

public class EmpleadoControlador {
    private List<Empleado> empleado;

    private static EmpleadoControlador empleadoControlador;


    private EmpleadoControlador(){

    }
    public static EmpleadoControlador getManejador(){
        if(empleadoControlador == null){
        	empleadoControlador = new EmpleadoControlador();
        }
        return empleadoControlador;
    }
}
