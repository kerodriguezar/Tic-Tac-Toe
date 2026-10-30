/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triqui;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
/**
 *
 * @author Kevin
 */
public class Juego {
    private Jugador[] jugadores;
    private Jugador computador;
    private byte no_reg;
    private byte modoJuego;
    private Tablero tablero;
    Scanner sc = new Scanner(System.in);
    Random rd = new Random();
    
    public Juego(){
        jugadores = new Jugador[6];
        this.tablero = new Tablero();
        computador = new Jugador();
    }
    
    public Juego(int capacidad){
        jugadores = new Jugador[capacidad];
    }
    
    public Tablero getTablero(){
        return tablero;
    }    
    
    public int buscar(String nombre){
        for (byte i = 0; i < no_reg; i++){
            if (jugadores[i].getNombre().equals(nombre)){
                return i;
            }
        }
        return -1;
    }
    
    public boolean registrarJugador(Jugador j){
        if (buscar(j.getNombre()) == -1){
            if (no_reg < jugadores.length){
                jugadores[no_reg] = j;
                no_reg++;
                return true;
            }
        }
        return false;
    }
    
    public void mostrarJugadoresRegistrados(){
        for (byte i = 0; i < no_reg; i++){
            System.out.println(jugadores[i].toStr());
        }
    }
    
    public void asignarFicha(Jugador j1, Jugador j2){
        j1.setFicha('X');
        j2.setFicha('O');
    }
    
    public int[] seleccionarModo(byte mJ){
        this.modoJuego = mJ;
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();
        int indx1;
        int indx2;
        
        switch(mJ){
            case 1:
                System.out.println("Haz seleccionado el modo de juego: Jugador 1 vs Jugador 2");
                System.out.print("Ingresa el nombre del Jugador 1: ");              
                j1.setNombre(sc.next());                
                registrarJugador(j1);
                indx1 = buscar(j1.getNombre());
                
                System.out.print("Ingresa el nombre del Jugador 2: ");                
                j2.setNombre(sc.next());                
                registrarJugador(j2);
                indx2 = buscar(j2.getNombre());
                
                asignarFicha(j1, j2);
                return new int[]{indx1, indx2}; 
                
            case 2:
                System.out.println("Haz seleccionado el modo de juego: Jugador vs Computador(nivel fácil)");
                System.out.print("Ingresa tu nombre: ");
                j1.setNombre(sc.next());                
                registrarJugador(j1);
                indx1 = buscar(j1.getNombre());
                
                computador.setNombre("Computador(nivel fácil)");
                asignarFicha(j1, computador);
                
                return new int[]{indx1};
                
            case 3:
                System.out.println("Haz seleccionado el modo de juego: Jugador vs Computador(nivel medio)");
                System.out.print("Ingresa tu nombre: ");
                j1.setNombre(sc.next());
                registrarJugador(j1);
                indx1 = buscar(j1.getNombre());
                
                computador.setNombre("Computador(nivel medio)");                
                asignarFicha(j1, computador);
                
                return new int[]{indx1};
        }
        return new int[]{-1, -1};
    }
    
    public void turno(Jugador j){
        while(true){
            try{
                System.out.print("Digita la fila: ");
                byte fila = sc.nextByte();
                
                System.out.print("Digita la columna: ");
                byte columna = sc.nextByte();
                
                if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3){
                    if (tablero.marcarCasilla(fila, columna, j.getFicha()) == false){
                        System.out.println("Esa casilla ya está ocupada, coloca tu ficha en otra posición.");
                    }else{
                        tablero.marcarCasilla(fila, columna, j.getFicha());
                        tablero.imprimirTablero();
                        break;
                    }                                                                                
                }else{
                    System.out.println("El valor ingresado no es válido.\nRecuerda que tanto para señalar la fila como la columna queseas marcar, debes usar valores desde 0 hasta el 2...");
                }
            }catch(InputMismatchException e){
                System.out.println("Los valores ingresados deben ser números enteros entre el 0 y el 2.");
                sc.nextLine();
            }  
        }       
    }
    
    public void iniciarJuego(){
        tablero.reiniciarTablero();
    }
    
    public void jugar(){
        iniciarJuego();
        System.out.println("Para iniciar el juego primero selecciona el modo de juego");
        while (true){
            try{
                System.out.println("Digita 1 para: Jugador 1 vs Jugador 2 \nDigita 2 para: Jugador vs Computador(nivel fácil)\nDigita 3 para: Jugador vs Computador (nivel medio)");
                byte seleccion = sc.nextByte();
                int[] indices = seleccionarModo(seleccion);
                
                Jugador j1 = jugadores[indices[0]];
                
                if (seleccion != 0){                                                            
                    if (seleccion == 1){
                        
                        Jugador j2 = jugadores[indices[1]];
                        
                        while(true){                                                        
                            System.out.println("Turno: " + j1.getNombre());
                            turno(j1);
                            
                            if (tablero.estaLleno() == true){                                
                                if (tablero.verificarGanador() == ' '){
                                    j1.empatar();
                                    j2.empatar();
                                    
                                    System.out.println(j1.toStr());
                                    System.out.println(j2.toStr());
                                }
                                break;
                            }
                            
                            if(tablero.verificarGanador() == 'X'){
                                j1.ganar();
                                j2.perder();
                                
                                System.out.println(j1.toStr());
                                System.out.println(j2.toStr());
                                break;
                            }
                            
                            System.out.println("Turno: " + j2.getNombre());
                            turno(j2);
                            
                            if(tablero.verificarGanador() == 'O'){
                                j2.ganar();
                                j1.perder();
                                
                                System.out.println(j1.toStr());
                                System.out.println(j2.toStr());
                                break;
                            }                            
                        }
                    }
                    
                    if (seleccion == 2){                        
                        while(true){
                            System.out.println("Turno: " + j1.getNombre());
                            turno(j1);
                        
                            if (tablero.estaLleno() == true){                                
                                if (tablero.verificarGanador() == ' '){
                                    j1.empatar();
                                    
                                    System.out.println(j1.toStr());
                                    break;
                                }                
                            }
                        
                            if (tablero.verificarGanador() == 'X'){
                                j1.ganar();
                            
                                System.out.println(j1.toStr());
                                break;
                            }
                        
                            System.out.println("Turno: Computador");
                            boolean casillaFalsa = false;
                            
                            while(!casillaFalsa){
                                int filaPc = rd.nextInt(3);
                                int columnaPc = rd.nextInt(3);
                                
                                if (tablero.marcarCasilla(filaPc, columnaPc, computador.getFicha()) == true){
                                    tablero.imprimirTablero();
                                    casillaFalsa = true;
                                }
                            }
                               
                            if (tablero.verificarGanador() == 'O'){
                                j1.perder();
                            
                                System.out.println(j1.toStr());
                                break;
                            }
                        }
                    }
                    
                    if (seleccion == 3){
                        while (true){
                            System.out.println("Turno: " + j1.getNombre());
                            turno(j1);
                            
                            if (tablero.estaLleno() == true){
                                if (tablero.verificarGanador() == ' '){
                                    j1.empatar();
                                    
                                    System.out.println(j1.toStr());
                                    break;
                                }
                            }
                            
                            if (tablero.verificarGanador() == 'X'){
                                j1.ganar();
                                
                                System.out.println(j1.toStr());
                                break;
                            }
                            
                            System.out.println("Turno: Computador");
                            boolean movimiento = false;
                            
                            for (int filaPc = 0; filaPc < 3; filaPc++){
                                if (tablero.casillas[filaPc][0] == 'X' && tablero.casillas[filaPc][0] == tablero.casillas[filaPc][1]){
                                    if (tablero.marcarCasilla(filaPc, 2, computador.getFicha()) == true){
                                        tablero.imprimirTablero();
                                        movimiento = true;
                                    }
                                }
                                if (tablero.casillas[filaPc][2] == 'X' && tablero.casillas[filaPc][2] == tablero.casillas[filaPc][1]){
                                    if (tablero.marcarCasilla(filaPc, 0, computador.getFicha()) == true){
                                        tablero.imprimirTablero();
                                        movimiento = true;
                                    }                                    
                                }
                            }
                            
                            for (int columnaPc = 0; columnaPc < 3; columnaPc++){
                                if (tablero.casillas[0][columnaPc] == 'X' && tablero.casillas[0][columnaPc] == tablero.casillas[1][columnaPc]){
                                    if (tablero.marcarCasilla(2, columnaPc, computador.getFicha()) == true){
                                        tablero.imprimirTablero();
                                        movimiento = true;
                                    }
                                }
                                if (tablero.casillas[2][columnaPc] == 'X' && tablero.casillas[2][columnaPc] == tablero.casillas[1][columnaPc]){
                                    if (tablero.marcarCasilla(0, columnaPc, computador.getFicha()) == true){
                                        tablero.imprimirTablero();
                                        movimiento = true;
                                    }
                                }
                            }
                            
                            if (tablero.casillas[0][0] == 'X' && tablero.casillas[0][0] == tablero.casillas[1][1]){
                                if (tablero.marcarCasilla(2, 2, computador.getFicha()) == true){
                                    tablero.imprimirTablero();
                                    movimiento = true;
                                }
                            }
                            if (tablero.casillas[2][2] == 'X' && tablero.casillas[2][2] == tablero.casillas[1][1]){
                                if (tablero.marcarCasilla(0, 0, computador.getFicha()) == true){
                                    tablero.imprimirTablero();
                                    movimiento = true;
                                }
                            }
                            
                            if (tablero.casillas[0][2] == 'X' && tablero.casillas[0][2] == tablero.casillas[1][1]){
                                if (tablero.marcarCasilla(2, 0, computador.getFicha()) == true){
                                    tablero.imprimirTablero();
                                    movimiento = true;
                                }
                            }
                            if (tablero.casillas[2][0] == 'X' && tablero.casillas[2][0] == tablero.casillas[1][1]){
                                if (tablero.marcarCasilla(0, 2, computador.getFicha()) == true){
                                    tablero.imprimirTablero();
                                    movimiento = true;
                                }
                            }                                                        
                            
                            boolean casillaFalsa = false;
                            
                            if (movimiento == false){
                                while(!casillaFalsa){
                                    int filaPc = rd.nextInt(3);
                                    int columnaPc = rd.nextInt(3);
                                    
                                    if (tablero.marcarCasilla(filaPc, columnaPc, computador.getFicha()) == true){
                                        tablero.imprimirTablero();
                                        casillaFalsa = true;
                                    }
                                }
                            }
                            
                            if (tablero.verificarGanador() == 'O'){
                                j1.perder();
                            
                                System.out.println(j1.toStr());
                                break;
                            }
                        }
                    }
                    
                    break;
                    
                }else{
                    System.out.println("Opción no válida");
                }
            }catch(InputMismatchException e){
                System.out.println("El valor ingresado debe ser un número entero entre 1 y 3.");
                sc.nextLine();
            }
        }
        
    }        
    
}
