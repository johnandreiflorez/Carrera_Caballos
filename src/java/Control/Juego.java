/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Apostador;

/**
 *
 * @author Andrei Florez V
 */
public class Juego {
    Apostador[] jugadores = new Apostador[4];
    int [] posiciones = new int [4];
    double totalGanadores =0, totalPerdedores=0 ;
    public void addApostadores (Apostador apostador, int pos){
        jugadores[pos]=apostador;
        System.out.println("Si Agrego El Jugador");;
    }
    public int sizeApostadores (){
        int cont=0;
        for(int i=0; i<jugadores.length;i++){
            if(jugadores[i]!=null)
                cont++;
        }
        return cont;
    }

    public Apostador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Apostador[] jugadores) {
        this.jugadores = jugadores;
    }
    
    public void correr (){
        posiciones[0] = (int) (Math.random() * 4) + 1;
        
        posiciones[1] = (int) (Math.random() * 4) + 1;
            while(posiciones[1]==posiciones[0])
                posiciones[1] =  (int) (Math.random() * 4) + 1;

        posiciones[2] = (int) (Math.random() * 4) + 1;
            while(posiciones[2]==posiciones[0] || posiciones[1]==posiciones[2])
                posiciones[2] =  (int) (Math.random() * 4) + 1;
        
        posiciones[3] = (int) (Math.random() * 4) + 1;
            while(posiciones[3]==posiciones[0] || posiciones[1]==posiciones[3] || posiciones[2]==posiciones[3])
                posiciones[3] =  (int) (Math.random() * 4) + 1;
    } 

    public int[] getPosiciones() {
        return posiciones;
    }
    public void gananciaPerdida (int ronda){
        vlrTotalganadores(ronda);
            for(int i =0 ; i<sizeApostadores();i++){
                if(!jugadores[i].getEstado().equals("Activo")){
                    if(posiciones[0]==jugadores[i].getApuestas()[ronda].getCaballo().getId()){
                        jugadores[i].setSaldo(ganancia(i,ronda)+jugadores[i].getSaldo());
                    }else
                        jugadores[i].setSaldo(jugadores[i].getSaldo()-jugadores[i].getApuestas()[ronda].getApuesta());
                    jugadores[i].asignarEstado();
                }
            }
    }
    public double ganancia (int i, int ronda){
        double ganancia = (jugadores[i].getApuestas()[ronda].getApuesta()*100/totalGanadores);
        totalGanadores =0;
        ganancia = (ganancia/100)*totalPerdedores;
        totalPerdedores = 0;
        return ganancia;
    }
    public void vlrTotalganadores (int ronda){
        
            for(int i =0 ; i<sizeApostadores();i++){
                if(!jugadores[i].getEstado().equals("Activo")){
                    if(posiciones[0]==jugadores[i].getApuestas()[ronda].getCaballo().getId())
                        totalGanadores += jugadores[i].getApuestas()[ronda].getApuesta();
                    else
                        totalPerdedores +=jugadores[i].getApuestas()[ronda].getApuesta(); 
                }
            }
    }
    
}
