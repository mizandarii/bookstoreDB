package bookstoredatabase;

import managers.SaveManager;
import managers.OrderManager;
import managers.ClientManager;
import managers.BookManager;
import entity.Book;
import entity.Order;
import entity.Client;
import java.util.List;
import java.util.Scanner;
import managers.DatabaseManager;
import tools.InputProtection;

public class App {
    private final Scanner scanner; 
    private List<Book> books;
    private List<Client> clients;
    private List<Order> orders;
    private final BookManager bookManager;
    private final ClientManager clientManager;
    private final OrderManager orderManager;
    private final SaveManager saveManager;
    private final DatabaseManager databaseManager;

    public App() {
        this.scanner = new Scanner(System.in);
        this.saveManager = new SaveManager();
        this.books = saveManager.loadBooks();
        this.clients = saveManager.loadClients();
        this.orders = saveManager.loadOrders();
        this.bookManager = new BookManager();
        this.clientManager = new ClientManager();
        this.orderManager = new OrderManager(scanner, clientManager, bookManager);
        this.databaseManager = new DatabaseManager();
    }

    public void run() {
        boolean repeat = true;
        System.out.println("------- Book store -------");
        System.out.println("Big sale alert! 50% off everything in");
        do {
            System.out.println("List of tasks:");
            System.out.println("0. Exit");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Add a new client");
            System.out.println("4. Display all clients");
            System.out.println("5. Sell a book");
            System.out.println("6. Display all orders");
            System.out.println("7. Books rating");
            System.out.println("8. Edit a book");
            System.out.println("9. Edit a client");
            System.out.println("10. Add money to a client's balance");
            System.out.println("11. Clients rating");


            int task = InputProtection.intInput(0,11); 
            System.out.printf("You select task %d, for exit press \"0\", to continue press \"1\": ",task);
            int toContinue = InputProtection.intInput(0,1);
            
            if(toContinue == 0) continue;
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    this.books.add(bookManager.addBook());

                    break;
                case 2:
                    bookManager.printListBooks(books);
                    break;
                case 3:
                    this.clients.add(clientManager.addClient());

                    break;
                case 4:
                    clientManager.printListClients(clients);
                    break;
                case 5:
                    this.orders.add(orderManager.makeOrder(books, clients));

                    break;
                case 6:
                    //orderManager.printListSoldBooks(orders);
                    orderManager.printListOrders(orders);
                    break;
                case 7:
                    orderManager.bookRating(this.orders);
                    break;
                case 8: 
                    bookManager.editBook(books);

                    break;
                case 9:
                    clientManager.editClient(clients);

                    break;
                case 10:
                    clientManager.addMoney(clients);
                    saveManager.saveClient(clients);
                case 11:
                    orderManager.clientRating(orders, clients);

                    break; 
                  
                default:
                    System.out.println("Invalid task number. Please select a task from the list above.");
            }
            System.out.println("-----------------------");
        }
        while (repeat);
        System.out.println("Till next time");
    }
}