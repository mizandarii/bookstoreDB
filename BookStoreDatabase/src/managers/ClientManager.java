/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Client;
import java.util.List;
import java.util.Scanner;
import tools.InputProtection;

/**
 *
 * @author admin
 */
public class ClientManager {
    Scanner scanner = new Scanner(System.in);
    
        public Client returnNewClient(){
        
        System.out.println("----------Add a client----------");
        
        Client client = new Client();
        
        System.out.println("Name");
        client.setName(scanner.nextLine());
        
        System.out.println("Surname");
        client.setSurname(scanner.nextLine());
        
        System.out.println("Phone number");
        client.setPhone(scanner.nextLine());
        
        System.out.println("City");
        client.setCity(scanner.nextLine());
        
        System.out.println("Balance");
        double clientBalance = Double.parseDouble(scanner.nextLine());
        client.setBalance(clientBalance);
       
        //scanner.nextLine();
        
        
        return client;
        
    }
    public void printListClients(List<Client> clients) {
        System.out.println("----- List of clients -----");
        for (int i = 0; i < clients.size(); i++) {
            
            Client client = clients.get(i);
            double balanceRough = client.getBalance();
            double balanceRounded = Math.floor(balanceRough * 100) / 100;
            System.out.println(i+1 + 
                    ". Name: " + client.getName() + 
                    " | Surname: "+client.getSurname() +
                    " | City: "+ client.getCity() +
                    " | Phone number: "+ client.getPhone() +
                    " | Balance (EUR): "+ balanceRounded);
            
        }
    }
    
    public void editClient(List<Client> clients) {
        System.out.println("----- Edit Client -----");
        printListClients(clients);

        System.out.print("Enter the number of the client to edit: ");
        int clientIndex = Integer.parseInt(scanner.nextLine()) - 1;
        
    System.out.println("Press: \n"
            + "1 to edit client's name \n"
    + "2 to edit last name \n"
    + "3 to edit phone number \n"
    + "4 to edit city \n"
    + "5 to edit balance \n");

    
    int task = InputProtection.intInput(1,5); 
    Client client = clients.get(clientIndex);
    switch (task) {
                case 1:
                    System.out.print("New Firstname: ");
        String newFirstname = scanner.nextLine();
        if (!newFirstname.isEmpty()) {
            client.setName(newFirstname);
            break;
        }
                        

                case 2:
                    System.out.print("New Surname: ");
        String newSurname = scanner.nextLine();
        if (!newSurname.isEmpty()) {
            client.setSurname(newSurname);
        }
                    break;
                case 3:
                   System.out.print("New Phone: ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            client.setPhone(newPhone);
        }
                    break;
                case 4:
                    System.out.print("New City: ");
        String newCity = scanner.nextLine();
        if (!newCity.isEmpty()) {
            client.setCity(newCity);
            break;
        }
        
                case 5:
                        System.out.print("New Balance: ");
        double newBalance;
        newBalance = Double.parseDouble(scanner.nextLine());
        if (newBalance >= 0){
            client.setBalance(newBalance);
            break;
        }        

                  
                default:
                    System.out.println("Invalid task number. Please select a task from the list above.");
            }
        
        if (clientIndex < 0 || clientIndex >= clients.size()) {
            System.out.println("Invalid client selection.");
            return;
        }

        

        

        

        
        
        
        

        System.out.println("Client updated!");
    }
        
    public Client addClient() {
        Client client = new Client();
        System.out.println("----- Add a client -----");
        
        System.out.print("First name: ");
        String clientName = scanner.nextLine();
        client.setName(clientName);

        System.out.print("Last name: ");
        String clientSurname = scanner.nextLine();
        client.setSurname(clientSurname);

        System.out.print("Phone: ");
        String clientPhone = scanner.nextLine();
        client.setPhone(clientPhone);
        
        System.out.print("City: ");
        String clientCity = scanner.nextLine();
        client.setCity(clientCity);
        
        System.out.print("Balance: "); 
        Double clientBalance = scanner.nextDouble();
        client.setBalance(clientBalance);


        System.out.println("New client added!");
        return client;
    }
    
    public Client addMoney(List <Client> clients) {
        
         printListClients(clients);
        
        System.out.println("----- Enter client number -----");
        
        int clientNumber = Integer.parseInt(scanner.nextLine());
        clientNumber-=1;
        Client client = clients.get(clientNumber);
        
        System.out.println("----- Enter amount of money to add -----");
        double addedMoney = Double.parseDouble(scanner.nextLine());
        client.setBalance(client.getBalance() + addedMoney);


        System.out.println("Client balance updated!");
        return client;
    }
    

}
