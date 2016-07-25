package com.example;

import com.example.model.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 *
 *
 */
public class App 
{
    private static final String PERSISTENCE_UNIT_NAME = "books";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // create new books
        Book jpaBook = new Book("JPA");
        Book mavenBook = new Book("Maven");
        em.getTransaction().begin();
        em.persist(jpaBook);
        em.persist(mavenBook);
        em.getTransaction().commit();

        // read the existing entries to console
        Query q = em.createQuery("select b from Book as b");
        List<Book> bookList = q.getResultList();
        for (Book book : bookList) {
            System.out.println(book.getId() +"  "+book.getTitle());
        }
        System.out.println("Size: " + bookList.size());

        //Close the entityManager and factory
        em.close();
        factory.close();

    }
}
