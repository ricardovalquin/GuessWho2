/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interfaces.GuessWhoInterface;
import java.util.ArrayList;

/**
 *
 * @author rick
 */
public class GuessWhoServerImplementation implements GuessWhoInterface {
     ArrayList <Users> users;
     ArrayList<Games> games;

     
     public GuessWhoServerImplementation(){
         users = new ArrayList();
         games = new ArrayList();
     }

    @Override
    public boolean LogIn(String playerName) {
        boolean result=false;
        boolean founded=false;

        for(int i=0; i<users.size(); i++){
            if(users.get(i).getName().equals(playerName)){
                founded=true;
                break;
            }
        }

        if(!founded){
           
            for(int i=0; i<users.size(); i++){//Se recorre a todos los usuarios
                //Se le deja el mensaje de entrada del usuario
                users.get(i).setMensaje("El usuario "+ playerName +" inicio sesion");
            }

            users.add(new Users(playerName));
            result=true;
        }
        return result;
    }

    @Override
    public boolean LOgOut(String playerName) {
        boolean result=false;
        int index=-1;

        //Se busca el usuario por el nombre
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getName().equals(playerName)){
                index=i;
            }
        }
        if (index > -1){//Si existe usuario remitente
            users.remove(index);

            for(int i=0; i< users.size(); i++){//Se recorre a todos los usuarios
                //Se le deja el mensaje de salida del usuario
                users.get(i).setMensaje("El usuario "+ playerName +" cerro sesion");
               }
            result=true;
        }
        return result;
    }

    @Override
    public String SeeOnlinePlayers() {
        String players="";

        //Se recorre los usuarios y se concatena los nombres en un String
        for(int i=0; i<users.size(); i++){
            players=players+ "," + users.get(i).getName();
        }
        
        return players;
    }

    @Override
    public boolean Challenge(String retador, String contrincante) {
       //hay que setear estado por defecto nullo o algo y ponerlo reto cuando se mande el reto
        //se tiene que buscar ambos jugadores y ver si están en línea antes de hacer comparaciones
        boolean answer = false;
	boolean founded = false;
	for (int i=0; i< games.size() ; i++) {
            // busca en todas las partidas activas
            if (games.get(i).getEstado().equals("activa")) {//yo estoy libre y solo puedo retar en esaas
                    if (games.get(i).getJugador1().equals(contrincante) ||
                       games.get(i).getJugador2().equals(contrincante)) {
                        founded = true;
                        break;				
                    }			
            }else{
                if ((games.get(i).getJugador1().equals(contrincante) && 
                        games.get(i).getJugador2().equals(retador)) || 
                        (games.get(i).getJugador1().equals(retador) && 
                        games.get(i).getJugador2().equals(contrincante))) {
                founded = true;
                break;				
                }

            }
	}
	if (!founded) {
		games.add(new Games(retador, contrincante));//reto creado
		answer = true;	
	}	

	return answer;
    }
    
    @Override
    public String AskByChallenges(String retado) {
        String retadores=null;
        
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getEstado().equals("reto")) {
                if (games.get(i).getJugador2().equals(retado)) {
                    retadores = retadores + "," + games.get(i).getJugador1();
                }
            }
        }      
        
        
        return retadores;
    }

    @Override
    public int AnswerChallenges(String retado, String retador, String respuesta) {
        int gameIndex = -1;
        
        if (respuesta.equals("Aceptar")) {
            for (int i = 0; i < games.size(); i++) {
                // si la partida es un reto
                if(games.get(i).getEstado().equals("reto")){
                // si es esa partida en específico
                    if (games.get(i).getJugador1().equals(retador) &&
                        games.get(i).getJugador2().equals(retado)) {
                        games.get(i).setEstado("Activa");// set the challenge accepted and starts the game
                        //seleccionar los pjs aleatorios
                        //games.get(i).setJugador1(random character);
                        //games.get(i).setJugador2(random character);
                        games.get(i).setTurno(retador);
                        gameIndex = i;
                        break;
                    }
                }
            }
        }
        
        return gameIndex;
    }

    @Override
    public String SeeCharacter(int partida, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String SeeTurn(int partida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    @Override
    public boolean AskCharacteristic(int partida, String nombre, String característica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AskCharacter(int partida, String retador, String character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }      
    
    public static void main(String arg[]){
        try{
            GuessWhoServerImplementation guess = new GuessWhoServerImplementation();
            
            System.out.println(""+guess.LogIn("Johan"));
            System.out.println(""+guess.LogIn("Yamile"));
            System.out.println(""+guess.LogIn("Adrian"));
            System.out.println(""+guess.LogIn("Susan"));
           
            System.out.println(""+ guess.SeeOnlinePlayers());
///////////////////////////////////////////////////////////////////            
            System.out.println(guess.Challenge("Johan", "Yamile"));
            System.out.println(guess.Challenge("Adrian", "Yamile"));
            System.out.println(guess.Challenge("Yamile", "Susan" ));
////////////////////////////////////////////////////    
            
            System.out.println(""+ guess.AnswerChallenges("Yamile", "Johan", "Aceptar"));
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
}


