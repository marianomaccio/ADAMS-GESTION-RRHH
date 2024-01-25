package com.gestion.rrhh.inicializador;

import com.gestion.rrhh.controlador.BuscadorControlador;
import com.gestion.rrhh.controlador.CreadorControlador;
import com.gestion.rrhh.modelo.EmpleadoTecnico;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inicializador {
    private static BuscadorControlador buscador = new BuscadorControlador();
    private static CreadorControlador creador = new CreadorControlador();
    private static Scanner sc = new Scanner(System.in);
    private static int empleadoTecnico = 0;
    private static int empleadoEjecutivo = 0;
    public static void main(String[] args) {
        int opcion = 0;
        System.out.println("Bienvenid@");
        while(true){
            try{
                System.out.println("Elija las Siguientes opciones:\n   1 Crear Empleado\n   " +
                        "2 Buscar Empleado\n   3 visualizar Resumen de Empleados Existentes\n   4 Salir");
                if ((opcion = sc.nextInt()) == 4) {
                    System.out.println("Gracias por su visita, adios!");
                    break;
                } else if (opcion == 1) {
                    System.out.println("Menu Creación");
                    int cantEmpleados = 0;
                    while(true) {
                        System.out.println("Elije Tipo de Empleado:\n   1 Ejecutivo\n   2 Técnico");
                        opcion = sc.nextInt();
                        if (opcion == 1) {
                            if (crearEjecutivo(empleadoEjecutivo)) {
                                System.out.printf("Empleado Ejecutivo n°: %d agregado\n", (++empleadoEjecutivo));
                                cantEmpleados++;
                            } else {
                                System.out.println("Vuelva a intentarlo");
                            }
                        }
                        else if (opcion == 2) {
                            if (crearTecnico(empleadoTecnico)) {
                                System.out.printf("Empleado Tecnico n°: %d agregado\n", (++empleadoTecnico));
                                cantEmpleados++;
                            } else {
                                System.out.println("Vuelva a intentarlo");
                            }
                        }
                        else {
                            System.out.println("Opcion Incorrecta. Solo se puede ingresar 1 o 2, intentelo nuevamente");
                            continue;
                        }
                        if(cantEmpleados == 5){
                            creador.crear();
                            creador.limpiarListaEmpleado();
                            break;
                        }
                    }



                } else if (opcion == 2) {
                    System.out.println("Buscador Empleados, si no desea buscar por algun campo pedido ingrese \"NO\"");
                    String campo;
                    System.out.println("Ingrese Nif de empleado:");
                    campo = sc.next();
                    campo = (campo.trim().equalsIgnoreCase("no"))? "" : campo;
                    buscador.setNif(campo);
                    System.out.println("Ingrese Nombre de empleado");
                    campo = sc.next();
                    campo = (campo.trim().equalsIgnoreCase("no"))? "" : campo;
                    buscador.setNombre(campo);
                    System.out.println("Ingrese Apellido de empleado");
                    campo = sc.next();
                    campo = (campo.trim().equalsIgnoreCase("no"))? "" : campo;
                    buscador.setPrimerApellido(campo);
                    System.out.println("Ingrese Puesto de empleado");
                    campo = sc.next();
                    campo = (campo.trim().equalsIgnoreCase("no"))? "" : campo;
                    buscador.setPuesto(campo);
                    buscador.buscar();

                } else if (opcion == 3) {
                    buscador.buscar();

                } else {
                    System.out.println("Opcion Incorrecta. Solo se puede ingresar 1, 2, 3 o 4, intentelo nuevamente");
                }
            }catch (InputMismatchException | URISyntaxException | IOException e){
                System.out.println("Error: Solo se pueden ingresar Numeros!");
                sc.next();
                continue;
            }
        }

    }

    private static boolean crearTecnico(int cantEmpleadoTecnico) throws URISyntaxException, IOException {
        creador.setTipoEmpleado("Tecnico");
        System.out.println("Ingrese Nif de empleado Tecnico n° " + (cantEmpleadoTecnico+1));
        creador.setNif(sc.next());
        System.out.println("Ingrese Nombre de empleado Tecnico n° " + (cantEmpleadoTecnico+1));
        creador.setNombre(sc.next());
        System.out.println("Ingrese Apellido de empleado Tecnico n° " + (cantEmpleadoTecnico+1));
        creador.setPrimerApellido(sc.next());
        System.out.println("Ingrese Puesto de empleado Tecnico n° " + (cantEmpleadoTecnico+1));
        creador.setPuesto(sc.next());
        System.out.println("Ingrese Salario de empleado Tecnico n° " + (cantEmpleadoTecnico+1));
        creador.setSalario(sc.nextDouble());
        int cualf = 0;
        while(true){
            System.out.println("Agrege Cualificaciones n°" + (cualf+1) + " o escriba 1 para terminar de agregar cualificaciones");
            creador.setCualificaciones(sc.next());
            if(creador.getCualificaciones().equals("1")){
                creador.setCualificaciones("");
                break;
            }
            creador.agregarCualficacion();
            cualf++;
        }
        return creador.agregar();

    }

    private static boolean crearEjecutivo(int cantEmpleadoEjecutivo) throws URISyntaxException, IOException {
        creador.setTipoEmpleado("Ejecutivo");
        System.out.println("Ingrese Nif de empleado Ejecutivo n° " + (cantEmpleadoEjecutivo+1));
        creador.setNif(sc.next());
        System.out.println("Ingrese Nombre de empleado Ejecutivo n° " + (cantEmpleadoEjecutivo+1));
        creador.setNombre(sc.next());
        System.out.println("Ingrese Apellido de empleado Ejecutivo n° " + (cantEmpleadoEjecutivo+1));
        creador.setPrimerApellido(sc.next());
        System.out.println("Ingrese Puesto de empleado Ejecutivo n° " + (cantEmpleadoEjecutivo+1));
        creador.setPuesto(sc.next());
        System.out.println("Ingrese Salario de empleado Ejecutivo n° " + (cantEmpleadoEjecutivo+1));
        creador.setSalario(sc.nextDouble());
        return creador.agregar();


    }


}
