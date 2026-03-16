package com.ipc1_proyecto1.recursividadsuma;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author Luis Monterroso
 */
public class RecursividadSuma {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un nunero para SUMAR");
        int n = sc.nextInt();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("202500066.txt"));
            PrintWriter writer = new PrintWriter(bw)) {
    
            writer.println(sumaRecursiva(n));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader("202500066.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("La suma de 1 hasta "+n+" es: " + linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }  
    }
    
    public static int sumaRecursiva(int number){
        if (number <= 1 ){
            return 1;
        } else {
            return (number + sumaRecursiva(number-1));  
        }   
    }
}
