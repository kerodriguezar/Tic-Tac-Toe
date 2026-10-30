/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triqui;

/**
 *
 * @author Kevin
 */
public class Jugador {
    private String nombre;
    private char ficha;
    private int victorias;
    private int derrotas;
    private int empates;
    
    public Jugador(){
        this.victorias = 0;
        this.derrotas = 0;
        this.empates = 0;
    }
    public Jugador(String n){
        nombre = n;
    }
    
    public void setNombre(String n){
        nombre = n;
    }
    public String getNombre(){
        return nombre;
    }
    
    public void setFicha(char s){
        ficha = s;
    }
    public char getFicha(){
        return ficha;
    }
    
    public int getVictorias(){
        return victorias;
    }
    public int getDerrotas(){
        return derrotas;
    }
    public int getEmpates(){
        return empates;
    }
    
    public void ganar(){
        victorias++;
    }
    public void perder(){
        derrotas++;
    }
    public void empatar(){
        empates++;
    }
    
    public String toStr(){
        return "Nombre del jugador: " + nombre + " | Ficha del jugador: " + ficha + " | Victorias: " + victorias + " | Derrotas: " + derrotas + " | Empates: " + empates;
    }
}
