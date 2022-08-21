package engine;

import interfaces.Compute;
import interfaces.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ToyComputeEngine extends UnicastRemoteObject implements Compute { 
   
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    
    public ToyComputeEngine() throws RemoteException {  
        super();
    }

    public Object executeTask(Task t) {
        return t.execute();
    } 

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port); 
        clientSocket = serverSocket.accept();
       
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stop() throws IOException { 
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        String name = "rmi://" + "localhost/ToyServiceEngine"; 
        ToyComputeEngine toyComputeEngine = new ToyComputeEngine(); 

        try {
            Compute engine = new ToyComputeEngine(); 
            Naming.rebind(name, engine); 
            System.out.println("ToyComputeEngine bound");
            toyComputeEngine.start(5000); 
        } catch (Exception e) { 
            System.err.println("ComputeEngine exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
