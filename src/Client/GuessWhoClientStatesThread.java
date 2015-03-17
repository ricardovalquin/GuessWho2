/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Interfaces.GuessWhoInterface;

/**
 *
 * @author rick
 */
public class GuessWhoClientStatesThread extends Thread implements Runnable {
//    preguntar por online
       // public String SeeOnlinePlayers();//

//    preguntar por retos
//            public String AskByChallenges(String retado);

//    preguntar por turno
//                public String SeeTurn(int partida);
//    preguntar por ganador y/o estado de partida
    GuessWhoView GWView = null;
    GuessWhoInterface  GWInterface = null;
    String name = null;
    String message = null;
    String online = null;
    String challenges = null;
    String turn = null;
    int game;
    
    public GuessWhoClientStatesThread(GuessWhoView view, GuessWhoInterface gwInterface, String player, int game){
        GWView = view;
        GWInterface = gwInterface;
        name = player;
        this.game = game;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                online = GWInterface.SeeOnlinePlayers();
                challenges = GWInterface.AskByChallenges(name);
                turn = GWInterface.SeeTurn(game);
//                state = GWInterface.AskByGameState(game);
            }catch(Exception ex){
                System.out.println("Error al sincronizar los datos");
            }
            if (!online.equals("")) GWView.whoIsOnLine(online);
            if (!challenges.equals("")) GWView.ChallengesToMe(challenges);
            if (!turn.equals("")) GWView.seeTurn(turn);
            
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
            
        }
    }
    
}
