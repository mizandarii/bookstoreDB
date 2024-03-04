/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;
import entity.Client;
import entity.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import tools.InputProtection;


public class OrderManager {

    private final Scanner scanner;
    private final BookManager bookManager;
    private final ClientManager clientManager;

    public OrderManager(Scanner scanner, ClientManager clientManager, BookManager bookManager) {
        this.scanner = scanner;
        this.clientManager = clientManager;
        this.bookManager = bookManager;
    }

    public Order makeOrder(List<Book> books, List<Client> clients) {
    Order order = new Order();
    bookManager.printListBooks(books);
    System.out.print("Enter the number of desired book: ");
    int bookNumber = InputProtection.intInput(1, books.size());
    Book selectedBook = books.get(bookNumber - 1);

    clientManager.printListClients(clients);
    System.out.print("Enter the number of the client: ");
    int clientNumber = InputProtection.intInput(1, clients.size());
    Client selectedClient = clients.get(clientNumber - 1);

    order.setBook(selectedBook);
    order.setClient(selectedClient);

     System.out.print("Enter the number of units sold: ");
    int unitsSold = InputProtection.intInput(1, Integer.MAX_VALUE);
    order.setUnitsSold(unitsSold);

    order.setOrderDate(new GregorianCalendar().getTime());

    selectedBook.setQuantity(selectedBook.getQuantity() - unitsSold);
    selectedClient.setBalance(selectedClient.getBalance() - selectedBook.getPrice()*unitsSold);

    return order;
}


    public void printListOrders(List<Order> orders) {
        System.out.println("----- List of orders -----");
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. %s sold to %s %s on %s%n ",
                    i + 1,
                    orders.get(i).getBook().getBookName(),
                    orders.get(i).getClient().getName(),
                    orders.get(i).getClient().getSurname(),

                    orders.get(i).getOrderDate()
                   
            );
            System.out.println( orders.get(i).getUnitsSold() + " pieces");
        }
    }
/**
    public void printTotalOrders(List<Order> orders) {
        Map<Book, Integer> totalSalesMap = new HashMap<>();
        for (Order order : orders) {
            if (totalSalesMap.containsKey(order.getBook())) {
                totalSalesMap.put(order.getBook(), totalSalesMap.get(order.getBook()) + 1);
            } else {
                totalSalesMap.put(order.getBook(), 1);
            }
        }

        Map<Book, Integer> sortedTotalSalesMap = totalSalesMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        int n = 1;
        for (Map.Entry<Book, Integer> entry : sortedTotalSalesMap.entrySet()) {
            System.out.printf("%d. %s: %d sales%n",
                    n,
                    entry.getKey().getBookName(),
                    entry.getValue()
            );
            n++;
        }
    }**/

    public void bookRating(List<Order> orders) {
    Map<Book, Integer> bookRatingMap = new HashMap<>();
    int booksTotal = 0;

    for (Order order : orders) {
        
        Book book = order.getBook();
        int unitsSold = order.getUnitsSold();
        booksTotal += unitsSold;
        bookRatingMap.put(book, bookRatingMap.getOrDefault(book, 0) + unitsSold);
    }

    Map<Book, Integer> sortedBookRatingMap = bookRatingMap.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
            ));

    int n = 1;
    double percentRough;
    double percentRounded;
    
    for (Map.Entry<Book, Integer> entry : sortedBookRatingMap.entrySet()) {
        
        percentRough = entry.getValue() * 100/ booksTotal;
        percentRounded = Math.floor(percentRough * 100) / 100;

        System.out.println(n + ". "
                + entry.getKey().getBookName() 
                + ": " + entry.getValue()+ " orders" 
                +" ("+percentRounded+"%)");
        n++;
    }
    }
    
    public void clientRating(List<Order> orders, List<Client> clients) {
    Map<Client, Integer> clientRatingMap = new HashMap<>();
    int bookOrdersTotal = 0;
    for (Order order : orders) {
        Client client = order.getClient();
        int unitsSold = order.getUnitsSold();
        bookOrdersTotal += unitsSold;
        clientRatingMap.put(client, clientRatingMap.getOrDefault(client, 0) + unitsSold);
    }

    Map<Client, Integer> sortedClientRatingMap;
        sortedClientRatingMap = clientRatingMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

    double percentRough;
    double percentRounded;    
    int n = 1;
    for (Map.Entry<Client, Integer> entry : sortedClientRatingMap.entrySet()) {
        
        percentRough = entry.getValue() * 100/ bookOrdersTotal;
        percentRounded = Math.floor(percentRough * 100) / 100;
        System.out.println(n + ". " + entry.getKey().getName() + " " + entry.getKey().getSurname() + ": " + entry.getValue() + " books " + "("+percentRounded+"%)");

        n++;
    }
    
    /**
    ArrayList<Integer> booksID = new ArrayList<Integer>(); 
    ArrayList<String> clientsID = new ArrayList<String>(); 
    int clientBooks;
    for (Order order:orders){
        Long clientIDRunning = order.getClient().getId();
        clientBooks = order.getQuantity();
        
        //System.out.println(client.getName());
        System.out.println(order.getUnitsSold());
        booksID.add(order.getUnitsSold());
        System.out.println(order.getClient().getName());
        clientsID.add(order.getClient().getName());
        
    }
    
    int clientBooks = 0;
    for(Client client:clients){
        clientBooks = 0;
        System.out.println(client.getName());
        Long clientIDRunning = client.getId();
        for (Order order:orders){
            if (order.getClient().getId() == clientIDRunning){
                clientBooks += order.getUnitsSold();
                System.out.println(clientBooks);
            }
        } 
        
    }**/
    }



    public void printListSoldBooks(List<Order> orders) {
    System.out.println("----- List of all sold books -----");
    for (int i = 0; i < orders.size(); i++) {
        System.out.printf("%d. %s: %d units%n",
                i + 1,
                orders.get(i).getBook().getBookName(),
                orders.get(i).getUnitsSold()
        );
    }
    }
    public void productSalesRating(List<Book> books, List<Order> orders) {
        System.out.println("----- Product Order Rating -----");
        bookManager.printListBooks(books);

        System.out.print("Enter the book number to display sales rating: ");
        int choice = InputProtection.intInput(1, books.size()) - 1;
        Book selectedBook = books.get(choice);

        double totalSales = 0;
        double totalRating = 0;

        for (Order order : orders) {
            if (order.getBook().equals(selectedBook)) {
                totalSales += order.getQuantity();
                totalRating += order.getBook().getOrderRating() * order.getQuantity();
            }
        }

        double averageRating = (totalSales > 0) ? totalRating / totalSales : 0;

        System.out.printf("Product: %s%nTotal Sales: %.2f%nAverage Sales Rating: %.2f%n",
                selectedBook.getBookName(), totalSales, averageRating);
    }
}


