/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.in;
import static java.lang.System.out;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Warunika
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private int clientID;

    public ClientHandler(Socket socket, int clientID) {
        this.socket = socket;
        this.clientID = clientID;

    }

    public void run() {
        try {
            PrintWriter out = null;
            BufferedReader in = null;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
            out.println("Welcome to the capitalization server");
            out.println("Your client Id is " + clientID);

            while (true) {
                String input = in.readLine();
                if (input.equals("") || input.equals(".")) {
                    break;
                }
                out.println(input.toUpperCase());
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            IOUtils.closeQuietly(socket);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

    }
}
