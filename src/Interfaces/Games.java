/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;

/**
 *
 * @author rick
 */
public class Games {
    
    private ArrayList<Characters> characters;
     
    private String jugador1 = null;
    private String jugador2 = null;

    private String personaje1 = null;
    private String personaje2 = null;

    private String turno = null; //cambia entre los nombres de los jugadores
    private String estado = null; // iniciada o terminada
    private String ganador = null;

	//GETTERS AND SETTERS

    public Games(String jugador1, String jugador2){// cuando generan partida no está en curso, apenas lo están retando
            this.jugador1 = jugador1;
            this.jugador2 = jugador2;
            personaje1 = "";
            personaje2 = "";
            turno = jugador1;
            estado = "reto";// cuando se acepte se pone activa, cuando se rechaza se pone rechazada, cuando se termina estado terminado

        // each game have a new array of characters
        characters = new ArrayList();
        Characters character;                    //nombre colorCabello  color de ojos piel  barba   bigore  gafas       sexo            
        characters.add(character = new Characters("Ana", "Amarillo", "Verde", "Blanco", "false", "false", "false", "female"));
        characters.add(character = new Characters("Patrick", "Naranja", "Cafe", "Blanco", "true", "true", "false", "male"));
        characters.add(character = new Characters("Craig", "Negro", "Cafe", "Negro", "true", "false", "false", "male"));
        characters.add(character = new Characters("Joe", "Cafe", "Negro", "Blanco", "false", "false", "false", "male"));
        characters.add(character = new Characters("Rebecca", "Naranja", "Azul", "Blanco", "false", "false", "false", "female"));
        characters.add(character = new Characters("Tom", "Cafe", "Cafe", "Blanco", "false", "false", "false", "male"));
        characters.add(character = new Characters("Cindy", "Negro", "Cafe", "Blanco", "false", "false", "false", "female"));
        characters.add(character = new Characters("Heater", "Naranja", "Azul", "Blanco", "false", "false", "true", "female"));
        characters.add(character = new Characters("Eric", "Negro", "Negro", "Blanco", "true", "true", "false", "male"));
        characters.add(character = new Characters("Stella", "Cafe", "Verde", "Blanco", "false", "false", "false", "female"));
        characters.add(character = new Characters("Robert", "Amarillo", "Verde", "Blanco", "false", "false", "false", "male"));
        characters.add(character = new Characters("Sam", "Naranja", "Cafe", "Blanco", "false", "false", "false", "male"));
        characters.add(character = new Characters("Sarah", "Cafe", "Verde", "Blanco", "false", "false", "true", "female"));
        characters.add(character = new Characters("Gary", "Amarillo", "Azul", "Blanco", "false", "false", "false", "male"));
        characters.add(character = new Characters("Mary", "Naranja", "Cafe", "Blanco", "false", "false", "false", "female"));
        characters.add(character = new Characters("Jack", "Naranja", "Azul", "Blanco", "false", "true", "false", "male"));
        characters.add(character = new Characters("Theresa", "Negro", "Negro", "Negro", "false", "false", "true", "female"));
        characters.add(character = new Characters("Luna", "Amarillo", "Negro", "Negro", "false", "false", "tue", "female"));
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    

    public ArrayList<Characters> getCharacters() {
        return characters;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getPersonaje1() {
        return personaje1;
    }

    public void setPersonaje1(String personaje1) {
        this.personaje1 = personaje1;
    }

    public String getPersonaje2() {
        return personaje2;
    }

    public void setPersonaje2(String personaje2) {
        this.personaje2 = personaje2;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
