package com.ipc1_tarea9.main;

import com.ipc1_tarea9.controllers.ListaDobleEstudiantes;
import com.ipc1_tarea9.controllers.ListaEstudiantes;

/**
 * @author Admin
 */
public class T9_ListasEnlazadas {

    public static void main(String[] args) {
        ListaEstudiantes ls = new ListaEstudiantes();
        ListaDobleEstudiantes ld = new ListaDobleEstudiantes();
        // TEST
        ls.agregarInicio("202300001", "Ana", 85.0);
        ls.agregarInicio("202300002", "Luis", 90.0);
        ls.agregarFinal("202300003", "María", 78.0);
        ls.eliminarPorCarnet("202300001");
        
        // SIMPLEMENTE ENLAZADA
        ls.buscarPorCarnet("202300003");
        
        ls.imprimirLista();
        ls.obtenerPromedio();
        ls.obtenerMejorNota();
        
        // DOBLEMENTE ENLAZADA
        ld.insertarOrdenado("202400001", "Jorge", 95);
        ld.insertarOrdenado("202400002", "Sebastian", 5);
        ld.insertarOrdenado("202400003", "Pedro", 45);
        ld.imprimirAdelante();
        
        ld.agregarInicio("202500067", "Mia", 88);
        ld.agregarFinal("202500066", "Luis", 61);
        ld.agregarInicio("202500076", "Noely", 99);
        ld.agregarFinal("202500001", "Eliminable", 76);
        ld.eliminarPorCarnet("202400003");
        ld.imprimirAdelante();
        ld.imprimirAtras();
        
        
    }
}
