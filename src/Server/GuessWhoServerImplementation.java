/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interfaces.Games;
import Interfaces.GuessWhoInterface;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

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
            /*for(int i=0; i<users.size(); i++){//Se recorre a todos los usuarios
                //Se le deja el mensaje de entrada del usuario
                users.get(i).setMensaje("El usuario "+ playerName +" inicio sesion");
            }*/
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
    //falta verificar que el retador no se encuentre en un reto en juego
    public int AnswerChallenges(String retado, String retador, String respuesta) {
        int gameIndex = -1;
        
        if (respuesta.equals("Aceptar")) {
            for (int i = 0; i < games.size(); i++) {
                // si el retado NO está en una partida activa
                if((!games.get(i).getEstado().equals("Activa")) &&
                        (!games.get(i).getJugador1().equals(retado) || //si yo no soy el retador
                        games.get(i).getJugador2().equals(retado))){// si soy el retado
                   
                    if(games.get(i).getEstado().equals("reto")){
                    // si es esa partida en específico
                        if (games.get(i).getJugador1().equals(retador) &&
                            games.get(i).getJugador2().equals(retado)) {
                            games.get(i).setEstado("Activa");// set the challenge accepted and starts the game
                            //seleccionar los pjs aleatorios
                            Random rand = new Random();
                            int randomNum1 = rand.nextInt((17 - 0) + 1) + 0;
                            int randomNum2 = rand.nextInt((17 - 0) + 1) + 0;
                            games.get(i).setPersonaje1(games.get(i).getCharacters().get(1).getName());
                            //System.out.println(""+games.get(i).getPersonaje1());randomNum2
                            games.get(i).setPersonaje2(games.get(i).getCharacters().get(0).getName());
                            //System.out.println(""+games.get(i).getPersonaje2());
                            games.get(i).setTurno(retado);
                            gameIndex = i;
                            break;
                        }
                    }
                }
            }
        }
        else{
            for (int i = 0; i < games.size(); i++) {
                // si la partida es un reto
                if(games.get(i).getEstado().equals("reto")){
                // si es esa partida en específico
                    if (games.get(i).getJugador1().equals(retador) &&
                        games.get(i).getJugador2().equals(retado)) {
                        games.get(i).setEstado("Rechazada");
                        break;
                    }
                }
            }
        }
        
        return gameIndex;
    }

    @Override
    public String SeeCharacter(int partida, String nombre) {
        String pj = null;
        for (int i = 0; i < games.size(); i++) {
            if (i == partida) {
                if (games.get(i).getJugador1().equals(nombre)) {
                    pj = games.get(i).getPersonaje1();
                }else if(games.get(i).getJugador2().equals(nombre)){
                    pj = games.get(i).getPersonaje2();
                }
            }
        }
        return pj;
    }
    
    @Override
    public String SeeTurn(int partida) {
        return games.get(partida).getTurno();
    } 

    @Override
    //se pregunta por la característica del otro jugador, player es quien pregunta
    public boolean AskCharacteristic(int partida, String player, String caracteristica) {
        boolean isCharacteristic = false;
        System.out.println(caracteristica);

         String vec[] = caracteristica.split(" ");
        int position = 0;
        //pelo ojos piel bigote barba gafas sexo
        //0        1   2   3     4      5    6  
        if (games.get(partida).getEstado().equals("Activa")) {
            //System.out.println("Encontro la partida");
            if (SeeTurn(partida).equals(player)) {
        
                switch(vec[0]){
                    case "hairColor":
                        position = 0;
                        break;
                    case "eyesColor":
                        position = 1;
                        System.err.println("Es la pacision 1 color de ojos");
                        break;
                    case "skinColor":
                        position = 2;
                        break;
                    case "moustache":
                        position = 3;
                        break;
                    case "barb":
                        position = 4;
                        break;
                    case "glases":
                        position = 5;
                        break;
                    case "sexo":
                        position = 6;
                        break;
                }   
                // si es el pj1 el que pregunta
                if (games.get(partida).getJugador1().equals(player)) {
                    
                    //obtener el nombre del pj del jugador 2
                    //recorrer los jugadores y comparar nombre
                    for (int i = 0; i < games.get(partida).getCharacters().size(); i++) {
                        if (games.get(partida).getCharacters().get(i).getName().equals(games.get(partida).getPersonaje2())) {
                            
                            if (games.get(partida).getCharacters().get(i).getSelfcharacteristic().get(position).equals(vec[1])){
                                isCharacteristic = true;//devolver true
//                                for (int j = 0; j < games.get(partida).getCharacters().size(); j++) {//setear los que no cumplen caracteristica
//                                    if(!(games.get(partida).getCharacters().get(i).getSelfcharacteristic().get(position).equals(vec[1]))){
//                                        games.get(partida).getCharacters().get(i).setState(false);//!games.get(partida).getCharacters().get(i).isState()
//                                    }
//                                }
                                games.get(partida).setTurno(games.get(partida).getJugador2());
                                break;// break porque ya lo encontró y ya cambió en todos, no tiene que hacer mas

                                //cambiar turno
                            }else{
                                isCharacteristic = false;
//                                for (int j = 0; j < games.get(partida).getCharacters().size(); j++) {//setear los que no cumplen caracteristica
//                                    if((games.get(partida).getCharacters().get(i).getSelfcharacteristic().get(position).equals(vec[1]))){
//                                        games.get(partida).getCharacters().get(i).setState(false);//!games.get(partida).getCharacters().get(i).isState()
//                                    }
//                                }
                                games.get(partida).setTurno(games.get(partida).getJugador2());
                                break;
                            }
                        }
                    }
                }else if(games.get(partida).getJugador2().equals(player)){
                    for (int i = 0; i < games.get(partida).getCharacters().size(); i++) {
                        if (games.get(partida).getCharacters().get(i).getName().equals(games.get(partida).getPersonaje1())) {
                            
                            if (games.get(partida).getCharacters().get(i).getSelfcharacteristic().get(position).equals(vec[1])){
                                isCharacteristic = true;//devolver true
//                                for (int j = 0; j < games.get(partida).getCharacters().size(); j++) {//setear los que no cumplen caracteristica
//                                    if(!(games.get(partida).getCharacters().get(i).getSelfcharacteristic().get(position).equals(vec[1]))){
//                                        games.get(partida).getCharacters().get(i).setState(false);//!games.get(partida).getCharacters().get(i).isState()
//                                    }
//                                }
                                games.get(partida).setTurno(games.get(partida).getJugador1());
                                break;// break porque ya lo encontró y ya cambió en todos, no tiene que hacer mas

                                //cambiar turno
                            }else{
                                isCharacteristic = false;
//                                for (int j = 0; j < games.get(partida).getCharacters().size(); j++) {//setear los que no cumplen caracteristica
//                                    if((games.get(partida).getCharacters().get(i).getSelfcharacteristic().get(position).equals(vec[1]))){
//                                        games.get(partida).getCharacters().get(i).setState(false);//!games.get(partida).getCharacters().get(i).isState()
//                                    }
//                                }
                                games.get(partida).setTurno(games.get(partida).getJugador1());
                                break;
                            }
                        }
                    }
                }
            }
        }    
        return isCharacteristic;
    }

    @Override
    public boolean AskCharacter(int partida, String player, String character) {
        boolean isCharacter = false;
        
        if(SeeTurn(partida).equals(player)){// si es el turno del jugador puede preguntar
                if (games.get(partida).getEstado().equals("Activa")) {
                    if (games.get(partida).getJugador1().equals(player)) {//si es el retador el que pregunta
                        if (games.get(partida).getPersonaje2().equals(character)) {// si el pj preguntado al otro player es
                            isCharacter = true;
                            games.get(partida).setGanador(games.get(partida).getJugador1());
                            games.get(partida).setEstado("Finalizada");
                            //games.get(partida).setTurno(games.get(partida).getJugador2());
                        }
                        else{
                            games.get(partida).setGanador(games.get(partida).getJugador2());
                            games.get(partida).setEstado("Finalizada");
                            //games.get(partida).setTurno(games.get(partida).getJugador2());
                        }
                    }else if(games.get(partida).getJugador2().equals(player)){// si es el retado el que pregunta
                        if (games.get(partida).getPersonaje1().equals(character)) {//si el pj preguntado al otro player es
                            isCharacter = true;
                            games.get(partida).setGanador(games.get(partida).getJugador2());
                            games.get(partida).setEstado("Finalizada");
                        }
                        else{
                            games.get(partida).setTurno(games.get(partida).getJugador1());

                        }
                    }
                }
            }
              
        return isCharacter;
    }  
    
    @Override
    public int SeeGame(String player) {
        int game = -1;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getEstado().equals("Activa")) {
                if (games.get(i).getJugador1().equals(player) || games.get(i).getJugador2().equals(player) ) {
                    game = i;
                }
            }
        }
        return game;
    }
    
    /*public static void main(String arg[]){
        try{
            GuessWhoServerImplementation guess = new GuessWhoServerImplementation();
            System.out.println("LogIN:");
            System.out.println(""+guess.LogIn("Johan"));
            System.out.println(""+guess.LogIn("Yamile"));
            System.out.println(""+guess.LogIn("Adrian"));
            System.out.println(""+guess.LogIn("Susan"));
           
            System.out.println("ver online:");
            System.out.println(""+ guess.SeeOnlinePlayers());
///////////////////////////////////////////////////////////////////            
            System.out.println("Crear retos:");
            System.out.println(guess.Challenge("Johan", "Yamile"));
            System.out.println(guess.Challenge("Adrian", "Yamile"));
            System.out.println(guess.Challenge("Yamile", "Susan" ));
////////////////////////////////////////////////////    
            System.out.println("pregunta por retos:");
            System.out.println(""+guess.AskByChallenges("Yamile"));
            
            System.out.println("responde retos:");
            System.out.println(""+ guess.AnswerChallenges("Yamile", "Johan", "Aceptar"));
            // pendiente por verificar
            System.out.println("acepta un reto que no debe:"+ guess.AnswerChallenges("Adrian", "Yamile", "Aceptar"));
            //System.out.println("acepta un reto que no debe:"+ guess.AnswerChallenges("Susan", "Yamile", "Aceptar"));
            System.out.println("rechaza:"+ guess.AnswerChallenges("Susan", "Yamile", "Rechazar"));
//////////////////////////////////////////////////////////////////        
            //preguntar por personaje
            //System.out.println("preguntando por personaje1 "+ guess.SeeCharacter(0, "Johan"));
            //System.out.println("preguntando por personaje2 "+ guess.SeeCharacter(0, "Yamile"));
            
/////////////////////////////////////
            //viendo turno
            System.out.println("turno inicial: "+ guess.SeeTurn(0));
///////////////////////////////
            //preguntando por caracter
            //System.out.println("preguntando por personajes");
            //preguntando por uno que no se
            //System.out.println("no se cual pj es: "+ guess.AskCharacter(0, "Johan", "Mary"));
            //preguntando por uno que si se
            //System.out.println("si se cual pj es: "+ guess.AskCharacter(0, "Johan", "Ana"));
            //preguntando cuando no es mi turno
            //System.out.println("no es mi turno: "+ guess.AskCharacter(0, "Yamile", "Patrick"));
            
////////////////////////////////////////////////
            //preguntando por característica
            //AskCharacteristic(int partida, String player, String caracteristica) jugador 1 iene 1 jugador 2 tiene 0
            System.out.println("preguntando por caracterisitica: "+guess.AskCharacteristic(0, "Johan", "moustache false"));
            System.out.println("Viendo que cambie de turno: "+ guess.SeeTurn(0));
            System.out.println("preguntando por caracterisitica en sentido contrario: "+guess.AskCharacteristic(0, "Yamile", "hairColor Naranja")); 
            System.out.println("Viendo que cambie de turno: "+ guess.SeeTurn(0));
            System.out.println("preguntando por caracterisitica que no cumple: "+guess.AskCharacteristic(0, "Johan", "skinColor Blanco")); 
            System.out.println("Viendo que cambie de turno: "+ guess.SeeTurn(0));
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
    
}


