package client;

import interfaces.Compute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.Naming;
import java.util.Scanner;

public class ToyComputeTaskRegistry {

    //Define our Socket variables...
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException { //Method used to initiate a socket connection...
        clientSocket = new Socket(ip, port); //Create a socket instance with IP Address and Port Number as the parameters...
        out = new PrintWriter(clientSocket.getOutputStream(), true); //Used to get he output data stream from the client...
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //Used to get the input data stream into the client...
    }

    public void stopConnection() throws IOException {
        in.close(); //Close the connection i.e input data stream...
        out.close(); //Close the connection i.e output data stream...
        clientSocket.close(); //Close the socket and terminate the overall connection...
    }

    public static void main(String[] args) {

        try {
            String name = "rmi://" + "localhost/ToyServiceEngine"; 
            ToyComputeTaskRegistry toyComputeTaskRegistry = new ToyComputeTaskRegistry(); 
            toyComputeTaskRegistry.startConnection("127.0.0.1", 5000); 
            Compute comp = (Compute) Naming.lookup(name); 
            Scanner sc= new Scanner(System.in);
            System.out.println("Please enter your toy name: ");
            String toyName = sc.nextLine();
            System.out.println("Please enter your toy price: ");
            Integer toyPrice = sc.nextInt();
            AddToyPrice task = new AddToyPrice(toyName, toyPrice); 
            System.out.println(comp.executeTask(task)); 


            //Updation

            Scanner sc1 = new Scanner(System.in);
            System.out.println("Please enter toy to be updated: ");
            String toyName1 = sc1.nextLine();
            System.out.println("Please enter your new toy price: ");
            Integer newToyPrice = sc1.nextInt();
            UpdateToyPrice task1 = new UpdateToyPrice(toyName1, newToyPrice); 
            System.out.println(comp.executeTask(task1)); 
            // Deletion
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Please enter toy to be deleted: ");
            String toyName2 = sc2.nextLine();
            DeleteToyPrice task2 = new DeleteToyPrice(toyName2); //Create a new client task or instance using a specific class i.e DeleteToyPrice...
            System.out.println(comp.executeTask(task2)); //Invoke the DeleteToyPrice client task...


            //Calculate the
            Scanner sc3 = new Scanner(System.in);
            System.out.println("Please enter toy name to get its cost: ");
            String toyName3 = sc3.nextLine();
            System.out.println("Please enter your toy quantity in multiples of 10 only: ");
            int toyQuantity = sc3.nextInt();
            CalculateToyCost task3 = new CalculateToyCost(toyName3, toyQuantity);//Create a new client task or instance using a specific class i.e CalculateToyCost...
            System.out.println(comp.executeTask(task3)); //Invoke the CalculateToyCost client task...


            //Calculate total toy cost and print 
            Scanner sc4 = new Scanner(System.in);
            System.out.println("Please confirm your toy: ");
            String toyname = sc4.nextLine();
            System.out.println("Please confirm your quantity: ");
            int toyQuantity1 = sc4.nextInt();
            System.out.println("Please enter amount to pay: ");
            int amount = sc4.nextInt();
            Scanner sc5 = new Scanner(System.in);
            System.out.println("Please enter cashier's name: ");
            String cashier = sc5.nextLine();
            CalculateCost task4 = new CalculateCost(toyname, cashier, toyQuantity1, amount); //Create a new client task or instance using a specific class i.e CalculateCost...
            System.out.println(comp.executeTask(task4)); //Invoke the CalculateCost client task...
            toyComputeTaskRegistry.stopConnection(); //Terminate the connection...


            //Output any exception 
        } catch (Exception e) {
            System.err.println("Compute exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
