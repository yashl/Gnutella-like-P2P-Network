import java.io.*;
import java.util.*;
import java.net.*;

import java.util.concurrent.ArrayBlockingQueue;

public class node
{
    public node(){}

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter port number for your node! (Any number between 3000 and 8000)");
        int nodePort = scan.nextInt();
        System.out.println("Eneter port number of node you want to connect to!");
        int network = scan.nextInt();
        scan.close();

        ArrayBlockingQueue<Integer> list = new ArrayBlockingQueue<Integer>(80);
        list.add(new Integer(network)); //add connection to node

        (new Listener(nodePort, list)).start(); //start listening on node port

        try
        {
            ping(list, nodePort);
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    public static void ping(ArrayBlockingQueue<Integer> list, int nodePort)
    {
        try{

            if (list.size() == 0)
            {
                Thread.sleep(2000);
                ping(list, nodePort);
            }

            while (true)
            {
                Integer[] array = list.toArray(new Integer[0]);
                System.out.println(array[0]);
                for(int i = 0; i < list.size(); i++)
                {
                    //Connecting to Network
                    String ip = "127.0.0.1";
                    System.out.println("Not Connected");
                    Socket clientSocket = new Socket(ip, (int) array[i]);
                    System.out.println("Connected");
                    
                    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                    String msg = nodePort + ",Ping from Node.";
                    output.write(msg.getBytes()); //send message
                    System.out.println("Pinged Server");

                    clientSocket.close();
                    output.close();
                }
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
    ArrayBlockingQueue<Integer> list = new ArrayBlockingQueue<Integer>(80);
    
    public Listener(int x, ArrayBlockingQueue<Integer> l) 
    { 
        port = x;
        list = l;
    }

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
