/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ipc1.tarea11.ejercicio2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Ejercicio2 {
    public static void main(String[] args) {

        extraerFechas();

        extraerCorreos();

        String prueba1 = "Llama a 5555-1234 o escribe a juan@usac.edu.gt para mas info.";
        System.out.println("Entrada:  " + prueba1);
        System.out.println("Salida:   " + censurarDatos(prueba1));

        String prueba2 = "Contacto: coord.ipc1@ingenieria.usac.edu.gt Tel: 2418-8000";
        System.out.println("\nEntrada:  " + prueba2);
        System.out.println("Salida:   " + censurarDatos(prueba2));

        String prueba3 = "Escribe a aux02.ipc1@gmail.com o llama al 4321-9876 o al 5555-1234.";
        System.out.println("\nEntrada:  " + prueba3);
        System.out.println("Salida:   " + censurarDatos(prueba3));

        System.out.println("\nTEXTO CENSURADO");
        System.out.println(censurarDatos(TEXTO));
    }
    
    private static final String TEXTO =
        "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA\n" +
        "Facultad de Ingenieria — Boletin Informativo 2024-04-25\n" +
        "\n" +
        "Actividades programadas:\n" +
        "- Conferencia el 2024-05-01 a las 10:30 en el Edificio T3\n" +
        "- Examen parcial el 2024-05-15 a las 08:00 en el Edificio S11\n" +
        "- Defensa de proyecto el 2024-06-10 a las 14:30 en el Edificio T7\n" +
        "\n" +
        "Contactos del departamento:\n" +
        "- Coordinador: coord.ipc1@ingenieria.usac.edu.gt Tel: 2418-8000\n" +
        "- Auxiliar 1: aux01_ipc1@ingenieria.usac.edu.gt Tel: 5555-1234\n" +
        "- Auxiliar 2: aux02.ipc1@gmail.com Tel: 4321-9876";

    public static void extraerFechas() {
        Pattern patron = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher matcher = patron.matcher(TEXTO);

        System.out.println("FECHAS");
        while (matcher.find()) {
            System.out.println("Año: " + matcher.group(1) +
                               " | Mes: " + matcher.group(2) +
                               " | Dia: " + matcher.group(3));
        }
    }

    public static void extraerCorreos() {
        Pattern patron = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher = patron.matcher(TEXTO);

        System.out.println("\nCORREOS");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    public static String censurarDatos(String texto) {
        Pattern patronCorreo = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcherCorreo = patronCorreo.matcher(texto);
        String resultado = matcherCorreo.replaceAll("[CORREO]");

        Pattern patronTel = Pattern.compile("\\d{4}-\\d{4}");
        Matcher matcherTel = patronTel.matcher(resultado);
        resultado = matcherTel.replaceAll("[TEL]");

        return resultado;
    }
}
