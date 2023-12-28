package com.gestion.rrhh.modelo;

import com.gestion.rrhh.modelo.tipoEmpleado.TipoEmpleado;
import com.gestion.rrhh.servicio.CalcularSalarioServicio;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Empleado {
    private TipoEmpleado tipoEmpleado;
    private String NIF;
    private String nombre;
    private String apellido;
    private String puesto;
    private BigDecimal salario;
    private Nomina nomina;
    private CalcularSalarioServicio servicio;

    public Empleado(TipoEmpleado tipoEmpleado, String nif, String nombre, String apellido, String puesto, BigDecimal salario) {
        try{
            this.tipoEmpleado = tipoEmpleado;
            if(!validarNif(nif))
                throw new IllegalArgumentException("NIF: " + nif + " INVALIDO!!");
            NIF = nif;
            this.nombre = nombre;
            this.apellido = apellido;
            this.puesto = puesto;
            this.salario = salario;
        }catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    private boolean validarNif(String nif) throws RuntimeException{
        String patronNIF = "^[0-9XYZ][0-9]{7,8}[TRWAGMYFPDXBNJZSQVHLCKE]$";

        // Compilar la expresión regular
        Pattern patron = Pattern.compile(patronNIF);

        // Crear un objeto Matcher
        Matcher matcher = patron.matcher(nif);

        // Verificar si el NIF coincide con el patrón
        return matcher.matches();
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }
    public String getNif() {
        return NIF;
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public CalcularSalarioServicio getServicio() {
        return servicio;
    }

    public void setServicio(CalcularSalarioServicio servicio) {
        this.servicio = servicio;
    }


}
