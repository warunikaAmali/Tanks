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
import java.util.Scanner;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Warunika
 */
public class Client {
     public static void main(String[] args) {
        Socket socket=null;
        BufferedReader reader=null;
        PrintWriter writer=null;
        Scanner scanner =null;
        try {
            System.out.println("connecting to the server...");
            socket=new Socket("127.0.0.1",4001);
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new PrintWriter(socket.getOutputStream(),true);
            
            for(int i=0;i<2;i++){
                System.out.println(reader.readLine());
            }
            
            scanner=new Scanner(System.in);
            while(true){
                String input=scanner.next();
                writer.println(input);
                String responseFromServer=reader.readLine();
                System.out.println("Capitalized Response "+responseFromServer);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
           IOUtils.closeQuietly(socket);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        
        }
    }
}
