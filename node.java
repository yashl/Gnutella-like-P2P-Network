import java.io.*;
import java.util.*;
import java.net.*;

public class node
{
    public node(){}

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter port number for your node! (Any number between 3000 and 8000)");
        int nodePort = scan.nextInt();
        scan.close();
        (new Listener(nodePort)).start(); //start listening on node port

        try
        {
            //Connecting to Network
            String ip = "127.0.0.1";
            int network = 6580;
            System.out.println("Not Connected");
            Socket clientSocket = new Socket(ip, network);
            System.out.println("Connected");
            
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            while(true)
            {
                String msg = nodePort + ",Ping from Node.";
                output.write(msg.getBytes()); //send message
                System.out.println("Pinged Server");
                Thread.sleep(2000);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }
}
class Listener extends Thread
{
    int port = 0;
    public Listener(int x) { port = x; }

    public void run()
    {
        try
        {
            System.out.println("server not started!");
            ServerSocket server = new ServerSocket(port);
            System.out.println("server started!");
            Socket socket = server.accept();

            DataInputStream input = new DataInputStream(socket.getInputStream());

            while(true)
            {
                byte[] buff = new byte[1024];
                input.read(buff);
                String read = new String(buff);
                System.out.println(read);
            }
        } 
        catch (Exception e)
        {
            System.out.println("Listener Exception: " + e);
        }
    }
}
