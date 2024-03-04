/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;

import java.util.List;
import java.util.Scanner;
import tools.InputProtection;

/**
 *
 * @author admin
 */
public class BookManager {
    Scanner scanner = new Scanner(System.in);

    public void printListBooks(List<Book> books) {
    System.out.println("----- List of all Books -----");
    for (int i = 0; i < books.size(); i++) {
        String yearString = Integer.toString(books.get(i).getYear());
        
        System.out.println(i + 1 +". " 
                + "Name: "+books.get(i).getBookName() 
                + " | Price: "+books.get(i).getPrice()
                + " | Author: "+ books.get(i).getAuthor()
                + " | Year: "+ yearString
                + " | Stock: "+ books.get(i).getQuantity());
                
                
    }
}
    public void setOrderRating(Book books, double rating) {
        books.setOrderRating(rating);
        System.out.println("Orders rating set successfully for " + books.getBookName() + ": " + rating);
    }
    
    public Book addBook() {
    System.out.println("----- Add a Book -----");
    Book book = new Book();
    System.out.print("Enter book name: ");
    book.setBookName(scanner.nextLine());
    
    System.out.print("Enter price in euros: ");
    double bookPrice = Double.parseDouble(scanner.nextLine());
    book.setPrice(bookPrice);
    
        
    System.out.print("Enter publishing year: ");
    int bookYear = Integer.parseInt(scanner.nextLine());
    book.setYear(bookYear);
        
    //scanner.nextLine();
    
    System.out.print("Enter author: ");
    String bookAuthor = scanner.nextLine();
    book.setAuthor(bookAuthor);
    
    System.out.print("Enter Quantity: ");
    int bookQuantity = Integer.parseInt(scanner.nextLine());
    book.setQuantity(bookQuantity);
    

    return book;
   
}
    

    public void editBook(List<Book> book) {
    
    System.out.println("----- Edit a Book -----");
    printListBooks(book);
    System.out.print("Enter the number of the book to edit: ");
    int bookChoice = InputProtection.intInput(1, book.size()) - 1;
    Book BookToEdit = book.get(bookChoice);
    
    
    
    System.out.println("Press: \n"
            + "1 to edit book name \n"
    + "2 to edit book publishing year \n"
    + "3 to edit book price \n"
    + "4 to edit book author \n"
    + "5 to edit book quantity \n");

    
    int task = InputProtection.intInput(1,5); 
    
    switch (task) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                    BookToEdit.setBookName(newName);
                        }

                case 2:
                    System.out.print("Enter new publishing year: ");
                    int newYear = Integer.parseInt(scanner.nextLine());
                    if (newYear >= 1440){
                        BookToEdit.setYear(newYear);
                    }
                    break;
                case 3:
                    System.out.print("Enter new price: ");
                    double newPrice = InputProtection.doubleInput(-1, Double.MAX_VALUE);
                    if (newPrice >= 0) {
                    BookToEdit.setPrice(newPrice);
                    }
                    break;
                case 4:
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    if (!newAuthor.isEmpty()) {
                    BookToEdit.setAuthor(newAuthor);
                    }
                case 5:
                        System.out.print("Enter new quantity: ");
                        int newQuantity = Integer.parseInt(scanner.nextLine());
                        if (newQuantity >= 0) {
                        BookToEdit.setQuantity(newQuantity);
                    }           

                  
                default:
                    System.out.println("Invalid task number. Please select a task from the list above.");
            }




    
    


    System.out.println("Book edited successfully!");
    printListBooks(book);
    }
}
    
