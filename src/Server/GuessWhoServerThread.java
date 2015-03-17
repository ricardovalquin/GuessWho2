/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interfaces.GuessWhoInterface;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author rick
 */
public class GuessWhoServerThread extends Thread implements Runnable {
    private static GuessWhoInterface gwInterface = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private Socket GWClientsocket = null;
    private String vec[];
    private String returnValue = null,command = null;
    
    public GuessWhoServerThread(Socket clientSocket, GuessWhoServerImplementation GuessWhoServer){
        this.GWClientsocket = clientSocket;
        gwInterface = GuessWhoServer;
        try{
            in = new DataInputStream(GWClientsocket.getInputStream());
            out = new DataOutputStream(GWClientsocket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String demux(String message){
        vec = message.split(" ");
        returnValue = "ok";
        try{
            if (vec[0].equals("LogIn")) {
                returnValue = "" + gwInterface.LogIn(vec[1]);
            }else if(vec[0].equals("LOgOut")){
                gwInterface.LOgOut(vec[1]);
                returnValue = "CerrarSesion";
            }else if (vec[0].equals("SeeOnlinePlayers")) {
                returnValue = gwInterface.SeeOnlinePlayers();
            }else if (vec[0].equals("Challenge")) {
                returnValue = "" + gwInterface.Challenge(vec[1], vec[2]);
            }else if (vec[0].equals("AnswerChallenges")) {
                                                //           retado, retador, respuesta
                returnValue = "" + gwInterface.AnswerChallenges(vec[1], vec[2], vec[3]);//revisar
            }else if (vec[0].equals("AskByChallenges")) {
                returnValue = "" + gwInterface.AskByChallenges(vec[1]);
            }else if (vec[0].equals("SeeCharacter")) {
                returnValue = "" + gwInterface.SeeCharacter(Integer.parseInt(vec[1]), vec[2]);
            }else if (vec[0].equals("AskCharacteristic")) {
                returnValue = "" + gwInterface.AskCharacteristic(Integer.parseInt(vec[1]), vec[2], vec[3]+" "+vec[4]);
//                returnValue = "" + gwInterface.AskCharacteristic(Integer.parseInt(vec[1]), vec[2], vec[3]);
            }else if (vec[0].equals("AskCharacter")) {
                returnValue = "" + gwInterface.AskCharacter(Integer.parseInt(vec[1]), vec[2], vec[3]);
            }else if (vec[0].equals("SeeTurn")) {
                returnValue = "" + gwInterface.SeeTurn(Integer.parseInt(vec[1]));
            }else if (vec[0].equals("SeeGame")) {
                returnValue = "" + gwInterface.SeeGame(vec[1]);
            }
         
            
        }catch(Exception ex){
            returnValue = ex.getMessage();
        }
        
        return returnValue;
    }
    
    @Override
    public void run(){
        command = "COMANDO"; // no se para qué pero bueno
        try{
            for (; !command.equals("CerrarSesion"); out.flush()) {
                command = in.readUTF();
                System.out.println("llego mensaje "+command);
                out.writeUTF(demux(command));
            }
            GWClientsocket.close();
        }catch(IOException e){
            e.printStackTrace();;
        }
    }    
    
}
