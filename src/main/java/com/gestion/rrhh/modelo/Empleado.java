package com.gestion.rrhh.modelo;



import java.math.BigDecimal;
import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;


public abstract class Empleado {

    private TipoEmpleado tipoEmpleado;

    private String nif;

    private String nombre;

    private String apellido;

    private String puesto;

    private double salarioBruto;

    private Nomina nomina;

    public Empleado(TipoEmpleado tipoEmpleado, String nif, String nombre, String apellido, String puesto, double salarioBruto) {
        try{
            this.tipoEmpleado = tipoEmpleado;
            this.nif = nif;
            this.nombre = nombre;
            this.apellido = apellido;
            this.puesto = puesto;
            this.salarioBruto = salarioBruto;
            nomina=new Nomina(salarioBruto);
        }catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    
    public Empleado() {};

    public String getTipoEmpleado() {
        return tipoEmpleado.name();
    }
    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public abstract String getDetalleNomina();

    public abstract double getSalarioNeto();



}
