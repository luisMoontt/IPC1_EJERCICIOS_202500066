package com.ipc1_tarea9.controllers;

import com.ipc1_tarea9.nodes.NodoEstudianteDoble;

/**
 * @author Admin
 */
public class ListaDobleEstudiantes {
    private NodoEstudianteDoble head;
    private NodoEstudianteDoble tail;

    public ListaDobleEstudiantes() {
        this.head = null;
        this.tail = null;
    }
    
    // INSERTAR INICIO ////////////////////////////////////////////////////////////
    public void agregarInicio(String carnet, String nombre, double nota){
        NodoEstudianteDoble nuevo = new NodoEstudianteDoble(carnet, nombre, nota);
        
        if (head == null) {
            head = nuevo;
            tail = nuevo;
            
        } else {
            nuevo.setSiguiente(head);
            head.setAnterior(nuevo);
            head = nuevo;
            
        }
    }

    // INSERTAR FINAL ////////////////////////////////////////////////////////////
    public void agregarFinal(String carnet, String nombre, double nota) {
        NodoEstudianteDoble nuevo = new NodoEstudianteDoble(carnet, nombre, nota);
        
        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
            tail = nuevo;
        }
    }
    
    // INSERTAR ASCENDENTE ///////////////////////////////////////////////////////
    public void insertarOrdenado(String carnet, String nombre, double nota) {
        NodoEstudianteDoble nuevo = new NodoEstudianteDoble(carnet, nombre, nota);

        if (head == null) {
            head = nuevo;
            tail = nuevo;
            
            return;
        }

        if (nota <= head.getNota()) {
            agregarInicio(carnet, nombre, nota);
            return;
        }

        if (nota >= tail.getNota()) {
            agregarFinal(carnet, nombre, nota);
            return;
        }

        NodoEstudianteDoble actual = head.getSiguiente();
        while (actual != null) {
            if (nota <= actual.getNota()) {
                NodoEstudianteDoble previo = actual.getAnterior();
                previo.setSiguiente(nuevo);
                nuevo.setAnterior(previo);
                nuevo.setSiguiente(actual);
                actual.setAnterior(nuevo);
                return;
            }
            actual = actual.getSiguiente();
        }
    }
    
    // ELIMINAR CON CARNET ///////////////////////////////////////////////////////
    public void eliminarPorCarnet(String carnet) {
        if (head == null) return;

        NodoEstudianteDoble actual = head;
        while (actual != null) {
            if (actual.getCarnet().equals(carnet)) {

                if (actual.getAnterior()!=null) {
                    
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                } else {
                    
                    head = actual.getSiguiente();
                }

                
                if (actual.getSiguiente()!=null) {
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                } else {
                    tail = actual.getAnterior();
                }

                return;
            }
            actual = actual.getSiguiente();
        }

        System.out.println("CARNET NO ENCONTRADO: " + carnet);
    }

    // LISTAR  HEAD-TAIL /////////////////////////////////////////////////////////
    public void imprimirAdelante() {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        NodoEstudianteDoble actual = head;
        while (actual != null) {
            System.out.println("Carnet: " + actual.getCarnet()
                    + " | Nombre: " + actual.getNombre()
                    + " | Nota: " + actual.getNota());
            actual = actual.getSiguiente();
        }
    }

    // LISTAR TAIL-HEAD /////////////////////////////////////////////////////////
    public void imprimirAtras() {
        if (tail == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        NodoEstudianteDoble actual = tail;
        while (actual != null) {
            System.out.println("Carnet: " + actual.getCarnet()
                    + " | Nombre: " + actual.getNombre()
                    + " | Nota: " + actual.getNota());
            actual = actual.getAnterior();
        }
    }
}
