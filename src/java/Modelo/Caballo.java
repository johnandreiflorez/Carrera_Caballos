/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Andrei Florez V
 */
public class Caballo {
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void nombreCaballo (int id){
        switch (id){
            case 1:
                nombre="Zeus";
            break;
            case 2:
                nombre="Bucefalo";
            break;
            case 3:
                nombre="Millonario";
            
            break;
            case 4:
                nombre="Relampago";
            break;
            default:
                break;
        }
    }
}
