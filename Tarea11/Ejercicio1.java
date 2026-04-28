/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ipc1.tarea11.ejercicio1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Ejercicio1 {
    public static void main(String[] args) {

        System.out.println("VALIDACION DE CARNET");
        
        String[] carnets = {"202300123", "202512345", "20230012", "2023ABC12", "123456789"};
        
        for (String c : carnets) {
            System.out.println(c + " - " + (validarCarnet(c) ? "Valido" : "Invalido"));
        }

        System.out.println("\n VALIDACION DE CORREO INSTITUCIONAL DE LA USAC");
        String[] correos = {
            "juan.perez@usac.edu.gt",
            "carla_001@usac.edu.gt",
            "juan@gmail.com",
            ".juan@usac.edu.gt",
            "_carlos@usac.edu.gt",
            "maria123@usac.edu.gt"
        };
        for (String correo : correos) {
            System.out.println(correo + " - " + (validarCorreoUSAC(correo) ? "Valido" : "Invalido"));
        }

        System.out.println("\n VALIDACION DE NUMERO DE TELEFONO");
        String[] telefonos = {"5555-1234", "30001234", "1234-5678", "555-1234", "44441234", "9999-1234"};
        for (String tel : telefonos) {
            System.out.println(tel + " - " + (validarTelefono(tel) ? "Valido" : "Invalido"));
        }
    }
    
    
    public static boolean validarCarnet(String carnet) {
        Pattern patron = Pattern.compile("^\\d{9}$");
        Matcher matcher = patron.matcher(carnet);
        return matcher.matches();
    }
    
    public static boolean validarCorreoUSAC(String correo) {
        Pattern patron = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9._]*@usac\\.edu\\.gt$");
        Matcher matcher = patron.matcher(correo);
        return matcher.matches();
    }

    public static boolean validarTelefono(String telefono) {
        Pattern patron = Pattern.compile("^[3456]\\d{3}-?\\d{4}$");
        Matcher matcher = patron.matcher(telefono);
        return matcher.matches();
    }

}
