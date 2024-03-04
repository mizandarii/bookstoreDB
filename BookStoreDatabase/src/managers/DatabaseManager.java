/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;


import entity.Book;
import entity.OrderHistory;
import entity.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin
 */
public class DatabaseManager {
    private EntityManager em;

    public DatabaseManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreDatabasePU");
        this.em = emf.createEntityManager();
    }
    public void saveBook(Book book){
        try {
            em.getTransaction().begin();
            if(book.getId() == null){
                em.persist(book);
            }else{
                em.merge(book);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    public void saveUser(Client client){
        try {
            em.getTransaction().begin();
                if(client.getId() == null){
                    em.persist(client);
                }else{
                    em.merge(client);
                }
            if(client.getId() == null){
                em.persist(client);
            }else{
                em.merge(client);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("User was not saved: "+e);
        }
    }

    public List<Book> getListBooks() {
        return em.createQuery("SELECT book FROM Book book").getResultList();
    }

    public List<Client> getListClients() {
        return em.createQuery("SELECT user FROM Client client").getResultList();
    }
    
    /**
    public List<OrderHistory> getReadingBooks(){
        return  em.createQuery("SELECT history FROM History history WHERE history.returnBook=null")
                .getResultList();
    }**/

    public Client authorization(String login, String password) {
        try {
            return (Client) em.createQuery("SELECT user FROM Client client WHERE client.login = :login AND client.password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Book getBook(Long id) {
        try {
            return em.find(Book.class, id);
        } catch (Exception e) {
            return null;
        }
    }
    public Client getClient(Long id) {
        try {
            return em.find(Client.class,id);
        } catch (Exception e) {
            return null;
        }
    }

    public void saveHistory(OrderHistory history) {
        try {
            em.getTransaction().begin();
            if(history.getId() == null){
                em.persist(history);
            }else{
                em.merge(history);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    OrderHistory getHistory(Long id) {
        try {
            return em.find(OrderHistory.class,id);
        } catch (Exception e) {
            return null;
        }
    }

    List<OrderHistory> getListHistories() {
        return em.createQuery("SELECT h FROM OrderHistory h")
                .getResultList();
    }

  /**  void saveAuthor(Author author) {
        try {
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    Author getAuthor(String firstname, String lastname) {
        try {
            return (Author) em.createQuery("SELECT a FROM Author a WHERE a.fistname = :firstname AND a.lastname = :lastname")
                    .setParameter(firstname, firstname)
                    .setParameter("lastname", lastname)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }**/
    
}