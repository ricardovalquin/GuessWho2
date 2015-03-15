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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SeeOnlinePlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Challenge(String retador, String contrincante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int AnswerChallenges(String retado, String retador, String respuesta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String AskByChallenges(String retado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SeeCharacter(int partida, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AskCharacteristic(int partida, String player, String característica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean AskCharacter(int partida, String player, String character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String SeeTurn(int partida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public static void main(String arg[]){
        try{
            test = new GuessWhoClientTCP("localhost", 2015);
            test.LogIn("Ricardo");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar", "Chat", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
    }
    

    }
}