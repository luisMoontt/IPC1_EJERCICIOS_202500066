package com.ipc1_tarea9.nodes;

/**
 * @author Admin
 */
public class NodoEstudianteDoble {
    private String carnet;
    private String nombre;
    private double nota;
    
    private NodoEstudianteDoble anterior;
    private NodoEstudianteDoble siguiente;

    public NodoEstudianteDoble() {
    }

    public NodoEstudianteDoble(String carnet, String nombre, double nota) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.nota = nota;
        this.anterior = null;
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

    public NodoEstudianteDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoEstudianteDoble anterior) {
        this.anterior = anterior;
    }

    public NodoEstudianteDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoEstudianteDoble siguiente) {
        this.siguiente = siguiente;
    }
}
