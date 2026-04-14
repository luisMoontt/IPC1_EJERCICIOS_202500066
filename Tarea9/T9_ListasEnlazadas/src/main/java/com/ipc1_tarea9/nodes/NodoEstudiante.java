package com.ipc1_tarea9.nodes;

/**
 * @author Admin
 */
public class NodoEstudiante {
    private String carnet;
    private String nombre;
    private double nota;
    
    private NodoEstudiante siguiente;

    public NodoEstudiante() {
    }

    public NodoEstudiante(String carnet, String nombre, double nota) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.nota = nota;
        this.siguiente = null;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public NodoEstudiante getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoEstudiante siguiente) {
        this.siguiente = siguiente;
    }  
}
