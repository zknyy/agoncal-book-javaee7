package org.agoncal.book.javaee7.chapter09;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish 3
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

  // ======================================
  // =           Public Methods           =
  // ======================================

  public static void main(String[] args) throws NamingException {

    // Looks up the EJB
    Context ctx = new InitialContext();
    BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/chapter09-service-1.0/BookEJB!org.agoncal.book.javaee7.chapter09.BookEJBRemote");

    // Creates an instance of book
    Book book = new Book("The Hitchhiker's Guide to the Galaxy", 12.5F, "Science fiction by Douglas Adams.", "1-24561-799-0", 354, false);

    List<Book> books = bookEJB.findBooks();
    for (Book aBook : books) {
      System.out.println("--- " + aBook);
    }

    book = bookEJB.createBook(book);
    System.out.println("### Book created : " + book);

    book.setTitle("H2G2");
    book = bookEJB.updateBook(book);
    System.out.println("### Book updated : " + book);

    bookEJB.deleteBook(book);
    System.out.println("### Book deleted");
  }
}