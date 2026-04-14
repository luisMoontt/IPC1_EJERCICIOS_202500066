package com.ipc1_tarea9.controllers;

import com.ipc1_tarea9.nodes.NodoEstudiante;

/**
 * @author Admin
 */
public class ListaEstudiantes {
    private NodoEstudiante head;
    private NodoEstudiante tail;

    public ListaEstudiantes() {
        this.head = null;
        this.tail = null;
    }
    
    // INSERTAR AL INICIO //////////////////////////////////////////////////////
    public void agregarInicio(String carnet, String nombre, double nota) {
        NodoEstudiante nuevo = new NodoEstudiante(carnet, nombre, nota);
        if (head == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            nuevo.setSiguiente(head);
            head = nuevo;
        }
    }

    // INSERTAR AL FINAL ///////////////////////////////////////////////////////
    public void agregarFinal(String carnet, String nombre, double nota) {
        NodoEstudiante nuevo = new NodoEstudiante(carnet, nombre, nota);
        if (tail == null) {
            head=nuevo;
            tail=nuevo;
            
        } else {
            tail.setSiguiente(nuevo);
            tail=nuevo;
        }
    }

    // ELIMINAR CON CARNET /////////////////////////////////////////////////////
    public void eliminarPorCarnet(String carnet) {
        if (head == null) return;
        
        if (head.getCarnet().equals(carnet)) {
            head = head.getSiguiente();
            if (head == null) tail = null;
            return;
        }

        NodoEstudiante actual = head;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getCarnet().equals(carnet)) {
                if (actual.getSiguiente() == tail) {
                    
                    tail = actual;
                }
                
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            
            actual = actual.getSiguiente();
        }
        System.out.println("CARNET NO ENCONTRADO: " + carnet);
    }

    // BUSCAR /////////////////////////////////////////////////////////////////
    public NodoEstudiante buscarPorCarnet(String carnet) {
        NodoEstudiante actual = head;
        while (actual != null) {
            if (actual.getCarnet().equals(carnet)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    // LISTAR /////////////////////////////////////////////////////////////////
    public void imprimirLista() {
        if (head == null) {
            System.out.println("LISTA VACIA");
            return;
        }
        NodoEstudiante actual = head;
        
        while (actual != null) {
            System.out.println("Carnet: " + actual.getCarnet()
                    + " | Nombre: " + actual.getNombre()
                    + " | Nota: " + actual.getNota());
            actual = actual.getSiguiente();
        }
    }

    // PROMEDIO  //////////////////////////////////////////////////////////////
    public double obtenerPromedio() {
        if (head == null) return 0.0;

        double suma = 0;
        int cantidad = 0;
        
        NodoEstudiante actual = head;
        
        while (actual != null) {
            suma += actual.getNota();
            cantidad++;
            actual = actual.getSiguiente();
        }
        return suma / cantidad;
    }

    // MAYOR NOTA //////////////////////////////////////////////////////////////
    public NodoEstudiante obtenerMejorNota() {
        if (head == null) return null;

        NodoEstudiante mayor = head;
        NodoEstudiante actual = head.getSiguiente();
        
        while (actual != null) {
            if (actual.getNota() > mayor.getNota()) {
                mayor = actual;
            }
            actual = actual.getSiguiente();
        }
        return mayor;
    }
}
