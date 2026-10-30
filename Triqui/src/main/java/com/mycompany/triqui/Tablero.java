/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triqui;

/**
 *
 * @author Kevin
 */
public class Tablero {
    char[][] casillas;
    
    public Tablero(){
        casillas = new char[3][3];
    }
    
    public void inicializarTablero(){
        for (int fila = 0; fila < 3; fila++){
            for (int columna = 0; columna < 3; columna++){
                casillas[fila][columna] = ' ';
            }
        }
    }
    
    public boolean estaDisponible(int fila, int columna){
        return casillas[fila][columna] == ' ';
    }
    
    public boolean marcarCasilla(int fila, int columna, char ficha){
        if (estaDisponible(fila,columna)){
            casillas[fila][columna] = ficha;
            return true;
        }
        return false;
    }
    
    public char verificarGanador(){
        for (int fila = 0; fila < 3; fila++){
            if (casillas[fila][0] != ' ' && casillas[fila][0] == casillas[fila][1] && casillas[fila][1] == casillas[fila][2]){
                return casillas[fila][0];
            }
        }
        
        for (int columna = 0; columna < 3; columna++){
            if(casillas[0][columna] != ' ' && casillas [0][columna] == casillas[1][columna] && casillas[1][columna] == casillas[2][columna]){
                return casillas[0][columna];
            }
        }
        
        if (casillas[0][0] != ' ' && casillas[0][0] == casillas[1][1] && casillas[1][1] == casillas[2][2]){
            return casillas[0][0];
        }
        
        if (casillas[0][2] != ' ' && casillas[0][2] == casillas[1][1] && casillas[1][1] == casillas[2][0]){
            return casillas[0][2];
        }
        
        return ' ';
    }
    
    public boolean estaLleno(){
        for (int fila = 0; fila < 3; fila++){
            for (int columna = 0; columna < 3; columna++){
                if (casillas[fila][columna] == ' '){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void reiniciarTablero(){
        inicializarTablero();
    }
    
    public void imprimirTablero(){
        System.out.println("-------------");
        for (int fila = 0; fila < 3; fila++){
            System.out.print("| ");
            
            for(int columna = 0; columna < 3; columna++){
                System.out.print(casillas[fila][columna] + " | ");                
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
