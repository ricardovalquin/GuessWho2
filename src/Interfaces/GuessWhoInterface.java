/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author rick
 */
public interface GuessWhoInterface {
    public boolean LogIn(String playerName);//
    
    public boolean LOgOut(String playerName);//
    
    public String SeeOnlinePlayers();//
    
    public boolean Challenge(String retador, String contrincante);//
    
    public int AnswerChallenges(String retado, String retador, String respuesta);
    
    // entrega un string que es el que te reta, VA EN EL HILO
    public String AskByChallenges(String retado);
    
    public String SeeCharacter(int partida, String nombre);// see my character
    
    public boolean AskCharacteristic(int partida, String player, String característica);
    
    public boolean AskCharacter (int partida, String player, String character);
    
    public String SeeTurn(int partida);
    
    public int SeeGame(String player);// retorna la partida que se está jugando
    
//    public String AskByGameState(int partida);
    
}
