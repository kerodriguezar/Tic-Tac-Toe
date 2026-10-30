/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.triqui;
import java.util.Scanner;

public class Triqui {

    public static void main(String[] args) {
        Juego juego = new Juego();
        String tableroTutorial[][] = {{" ","0","1","2"},{"0","(0,0)","(0,1)","(0,2)"},{"1","(1,0)","(1,1)","(1,2)"},{"2","(2,0)","(2,1)","(2,2)"}};                            
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Antes de empezar, aquí van unas breves instrucciones de como funciona el juego.\n- Para registrar tu movimiento en el tablero debes indicar la fila y columna en la que deseas poner tu ficha (X ó O).\n Esquema de posiciones en el tablero: ");
        
        for (int fila = 0; fila < 4; fila++){
            System.out.print(" ");
            for(int columna = 0; columna < 4; columna++){
                if (fila == 0 && columna == 1){
                    System.out.print("  ");
                }
                if (fila == 0 && columna == 1 || fila == 0 && columna == 2 || fila == 0 && columna == 3){
                    System.out.print(tableroTutorial[fila][columna] + "   |   ");
                }else{
                    System.out.print(tableroTutorial[fila][columna] + " | ");
                }
            }
            System.out.println();
            System.out.println("   ------------------------");
        }
        
        System.out.println("- Los turnos entre cada jugador son alternantes en todos los modos de juego.\n- Gana el primer jugador que complete una alineación seguida de tres fichas, sea en fila, columna, o diagonal.");
        
        juego.jugar();
        System.out.print("¿Deseas jugar otra partida?: ");
        String respuesta = sc.next();
        respuesta = respuesta.toUpperCase();
        
        while(respuesta.equals("SI")){
            juego.jugar();
            
            System.out.print("¿Deseas jugar otra partida?: ");
            respuesta = sc.next();
            respuesta = respuesta.toUpperCase();
        }
        
        juego.mostrarJugadoresRegistrados();
        System.out.println("Fin del juego");
        sc.close();    
    }
}
