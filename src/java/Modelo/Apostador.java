/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Andrei Florez V
 */
public class Apostador {
    private String nombre;
    private double saldo;
    private String estado; 
    private  Apuesta[] apuestas = new Apuesta[20];

    public String getNombre() {
        return nombre;        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void addApuestas (Apuesta apuesta,int pos){
       apuestas[pos] = apuesta;
    }

    public Apuesta[] getApuestas() {
        return apuestas;
    }   
    
    public void asignarEstado(){
        if(saldo<=0)
            estado="Activo";
        else
            estado="Eliminado";
    }

    public String getEstado() {
        return estado;
    }
    
}
