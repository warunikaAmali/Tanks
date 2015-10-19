/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Warunika
 */
public class Server {
    private static int clientCount=0;


    public static void main(String[] args) {
        //ExecutorService executorService=new Executors.newCachedThreadPool();
        
        System.out.println("server is starting..");
        ServerSocket serverSocket=null;
        Socket socket = null;
        
        
        try {
            serverSocket = new ServerSocket(4001);
            while (true) {
                Socket accept= serverSocket.accept();
                ClientHandler clientHandler=new ClientHandler(accept, clientCount++);
                clientHandler.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
