import java.io.*;
import java.util.*;
import java.net.*;

public class node
{
    public node(){}

    public static void main(String args[])
    {
        (new Listener()).start(); //start listening on node port

        try
        {
            //Connecting to Network
            String ip = "127.0.0.1";
            int network = 6580;
            System.out.println("Not Connected");
            Socket clientSocket = new Socket(ip, network);
            System.out.println("Connected");
            
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            //while(true)
            //{
                String msg = "6539,Ping from Node.";
                output.write(msg.getBytes()); //send message
                System.out.println("Pinged Server");
                
            //}
            clientSocket.close();
            output.close();
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }
}

class Listener extends Thread
{
    public void run()
    {
        try
        {
            int port = 6539;
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();

            DataInputStream input = new DataInputStream(socket.getInputStream());
            byte[] buff = new byte[1024];
            input.read(buff);
            String read = new String(buff);
            System.out.println(read);
            
            socket.close();
            server.close();
        } 
        catch (Exception e)
        {
            System.out.println("Listener Exception: " + e);
        }
    }
}
