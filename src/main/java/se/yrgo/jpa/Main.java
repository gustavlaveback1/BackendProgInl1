package se.yrgo.jpa;

import java.util.List;

import jakarta.persistence.*;
import se.yrgo.jpa.entities.Author;
import se.yrgo.jpa.entities.Book;
import se.yrgo.jpa.entities.Reader;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAQueriesPU");
        EntityManager em = emf.createEntityManager();

        // Uppgift 1. Skapa och lagra data.
        em.getTransaction().begin();

        Author author1 = new Author("Gabriel García Márquez", "Colombian");
        Author author2 = new Author("Haruki Murakami", "Japanese");
        Author author3 = new Author("Jane Austen", "British");

        Book book1 = new Book("One Hundred Years of Solitude", "Magic Realism", 1967);
        Book book2 = new Book("Kafka on the Shore", "Fiction", 2002);
        Book book3 = new Book("Norwegian Wood", "Romance", 1987);
        Book book4 = new Book("Pride and Prejudice", "Romance", 1813);
        Book book5 = new Book("Emma", "Romance", 1815);

        // Books to author
        author1.addBook(book1);
        author2.addBook(book2);
        author2.addBook(book3);
        author3.addBook(book4);
        author3.addBook(book5);

        // Persist authors and books
        em.persist(author1);
        em.persist(author2);
        em.persist(author3);

        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.persist(book4);
        em.persist(book5);

        Reader reader1 = new Reader("Alice", "alice@example.com");
        Reader reader2 = new Reader("Bob", "bob@example.com");
        Reader reader3 = new Reader("Charlie", "charlie@example.com");

        // Books to readers
        reader1.addBook(book1);
        reader1.addBook(book4);
        reader2.addBook(book2);
        reader2.addBook(book3);
        reader2.addBook(book5);
        reader3.addBook(book1);
        reader3.addBook(book3);

        // Persist readers
        em.persist(reader1);
        em.persist(reader2);
        em.persist(reader3);

        em.getTransaction().commit();
        System.out.println("All data sparad i databasen!");

        // Uppgift 2. Hämta alla böcker av en specifik författare (JPQL)
        em.getTransaction().begin();
        String requiredName = "haruki murakami";
        TypedQuery<Author> query = em.createQuery(
                "SELECT a from Author a WHERE LOWER(a.name) = :name",
                Author.class);

        query.setParameter("name", requiredName);

        List<Author> result = query.getResultList();
        for (Author a : result) {
            System.out.println("Författare: " + a.getName());

            System.out.println("Böcker:");
            for (Book b : a.getBooks()) {
                System.out.println(b.getTitle());
            }
        }
        em.getTransaction().commit();

        //Uppgift 3. Hämta alla läsare( readers) som har läst en viss bok (member of)
        em.getTransaction().begin();

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}