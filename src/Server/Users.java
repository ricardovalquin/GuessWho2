/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

/**
 *
 * @author rick
 */
public class Users{

    String name;
    ArrayList <String> mensajes;


    public Users(String nombre) {
        this.name = nombre;
        this.mensajes = new ArrayList();
    }

    public String getMensaje() {
        String mensaje = "";
        if (mensajes != null){
            if(mensajes.size() > 0){
                mensaje = mensajes.get(0);
                mensajes.remove(0);
            }
        }

        return mensaje;
    }
    public void setMensaje(String mensaje) {
        mensajes.add(mensaje);
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

   

    

}
