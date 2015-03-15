/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rick
 */
public class GuesswhoServerTCP {
    private ServerSocket serverSocket = null;
    private Socket GWClientSocket = null;
    private GuessWhoServerThread GWThread = null;
    private GuessWhoServerImplementation GWImplementation = null;
    
    public GuesswhoServerTCP(int port){
        try{
            GWImplementation = new GuessWhoServerImplementation();
            serverSocket = new ServerSocket(port);
        }catch(IOException e){
            
        }
    }
    
    public void daemon(){
        while(true){
            try{
                System.out.println("Waiting for a player...");
                GWClientSocket = serverSocket.accept();
                System.out.println("Player founded");
                System.out.println("asfd" + GWClientSocket.getInetAddress().getHostName());
                 GWThread = new GuessWhoServerThread(GWClientSocket, GWImplementation);
                 GWThread.start();
            }catch(IOException e){
                System.out.println("acá se totio");
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String arg[]){
    GuesswhoServerTCP server = new GuesswhoServerTCP(2015);
    server.daemon();
}
}
