package com.gestion.rrhh.manejador;

import com.gestion.rrhh.modelo.Empleado;

import java.util.List;

public class ManejadorEmpleado {
    private List<Empleado> empleado;

    private static ManejadorEmpleado manejadorEmpleado;


    private ManejadorEmpleado(){

    }
    public static ManejadorEmpleado getManejador(){
        if(manejadorEmpleado == null){
            manejadorEmpleado = new ManejadorEmpleado();
        }
        return manejadorEmpleado;
    }
}
