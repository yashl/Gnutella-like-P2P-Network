import java.io.*;
import java.util.*;
import java.net.*;

public class server
{
    public server(){}

    public static void main(String args[])
    {
        System.out.println("Thread not started!");
        (new Listener()).start();
        System.out.println("Thread started!");        

        /*
        try
        {
            //Connecting to Network
            String ip = "localhost";
            int network = 6538;
            Socket clientSocket = new Socket(ip, network);
            
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            while(true)
            {
                String msg = "6539,Ping from Node.";
                output.write(msg.getBytes()); //send message
                System.out.println("Pinged Server");
                Thread.sleep(2000);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e);
        }
        */
    }
}

class Listener extends Thread
{
    public Listener() {}

    public void run()
    {
        try
        {
            System.out.println("server not started!");
            int port = 6580;
            ServerSocket server = new ServerSocket(port);
            System.out.println("server started!");
            Socket socket = server.accept();

            DataInputStream input = new DataInputStream(socket.getInputStream());

            //while(true)
            //{
                byte[] buff = new byte[1024];
                input.read(buff);
                String read = new String(buff);
                System.out.println(read);
            //}

            server.close();
            socket.close();
            input.close();
        } 
        catch (Exception e)
        {
            System.out.println("Listener Exception: " + e);
        }
    }
}
