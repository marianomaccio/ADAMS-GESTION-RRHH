package com.gestion.rrhh.presentacion;

import com.gestion.rrhh.manejador.ManejadorEmpleado;
public class Inicializador {
    public static void main(String[] args) {
        ManejadorEmpleado manejador = ManejadorEmpleado.getManejador();
    }
}