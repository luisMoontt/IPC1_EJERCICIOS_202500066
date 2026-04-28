package com.ipc1_tarea10.models;

/**
 * @author Admin
 */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamano;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    /**
     * Agrega un elemento al final de la cola.
     */
    public synchronized void encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (fin == null) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
        tamano++;
    }

    /**
     * Remueve y retorna el primer elemento de la cola.
     * Sincronizado para evitar condiciones de carrera entre baristas.
     */
    public synchronized T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamano--;
        return dato;
    }

    /**
     * Retorna el primer elemento sin removerlo.
     */
    public synchronized T frente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }

    /**
     * Verifica si la cola está vacía.
     */
    public synchronized boolean estaVacia() {
        return frente == null;
    }

    /**
     * Retorna la cantidad de elementos en la cola.
     */
    public synchronized int tamano() {
        return tamano;
    }
}
