/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Interfaces.GuessWhoInterface;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author rick
 */
public class GuessWhoClientTCP implements GuessWhoInterface{
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;
    
    private static GuessWhoInterface test = null;
    
    public GuessWhoClientTCP(String host, int port){
        try{
            clientSocket = new Socket(InetAddress.getByName(host), port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream((clientSocket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    @Override
    public boolean LogIn(String playerName) {
        return Boolean.parseBoolean(sendMessage("LogIn " + playerName));
    }

    @Override
    public boolean LOgOut(String playerName) {
        return Boolean.parseBoolean(sendMessage("LOgOut " + playerName));
    }

    @Override
    public String SeeOnlinePlayers() {
        return sendMessage("SeeOnlinePlayers ");
    }

    @Override
    public boolean Challenge(String retador, String contrincante) {
        return Boolean.parseBoolean(sendMessage("Challenge " + retador + " " + contrincante));
    }

    @Override
    public int AnswerChallenges(String retado, String retador, String respuesta) {
        return Integer.parseInt(sendMessage("AnswerChallenges " + retado + " " + retador + " " + respuesta));
    }

    @Override
    public String AskByChallenges(String retado) {
        return sendMessage("AskByChallenges " + retado);
    }

    @Override
    public String SeeCharacter(int partida, String nombre) {
        return sendMessage("SeeCharacter " + partida + " " + nombre);
    }

    @Override
    public boolean AskCharacteristic(int partida, String player, String caracteristica) {
        return Boolean.parseBoolean(sendMessage("AskCharacteristic " + partida + " " + player + " " + caracteristica));
    }

    @Override
    public boolean AskCharacter(int partida, String player, String character) {
        return Boolean.parseBoolean(sendMessage("AskCharacter " + partida + " " + player + " " + character));
    }

    @Override
    public String SeeTurn(int partida) {
        return sendMessage("SeeTurn " + partida);
    }
    
    @Override
    public int SeeGame(String player) {
        return Integer.parseInt(sendMessage("SeeGame " + player));
    }
    
    public String sendMessage(String message){
        try{
            out.writeUTF(message);
            out.flush();
            message = in.readUTF();
        }catch(IOException e){
            
        }
        return message;
    }
    
    
//    public static void main(String arg[]){
//        try{
//            test = new GuessWhoClientTCP("localhost", 2015);
//            test.LogIn("Alvaro");
//            test.LogIn("Ricardo");
//            test.LogIn("Andrea");
//            test.LogIn("Claudia");
//            System.out.println("creando retos:");
//            //test.Challenge("Alvaro", "Ricardo");
//            //test.Challenge("Andrea", "Claudia");
//            test.Challenge("Alvaro", "Claudia");
//            System.out.println("preguntando por retos:");
//            System.out.println(""+test.AskByChallenges("Claudia"));
//            System.out.println("respondiendo retos:");
//            //System.out.println("asfd: "+test.AnswerChallenges("Claudia", "Andrea", "Rechazar"));
//            System.out.println(""+test.AnswerChallenges("Claudia", "Alvaro", "Aceptar"));
//            System.out.println("jugando: ");
//            System.out.println(""+test.SeeCharacter(0, "Claudia"));
//            System.out.println(""+test.SeeCharacter(0, "Alvaro"));
//            System.out.println("Viendo que cambie de turno: "+ test.SeeTurn(0));
//            //System.out.println("Preguntando por pj:");
//            //System.out.println(""+test.AskCharacter(0, "Alvaro", "Ana"));
//            //System.out.println("Viendo que cambie de turno: "+ test.SeeTurn(0));
//            //System.out.println("Preguntando por pj:");
//            //System.out.println(""+test.AskCharacter(0, "Claudia", "Patrick"));
//            System.out.println("preguntando por característica");
//            System.out.println(""+test.AskCharacteristic(0, "Alvaro", "eyesColor Verde"));
////                                       AskCharacteristic(0, "Yamile", "hairColor Naranja")
//            
////            while(true){
////                int i = 0;
////                i++;
////            }
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, "Error al conectar", "Chat", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//    }
//    
//
//    }

    
}