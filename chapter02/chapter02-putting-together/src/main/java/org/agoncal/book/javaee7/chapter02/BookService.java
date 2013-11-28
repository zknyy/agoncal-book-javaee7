package org.agoncal.book.javaee7.chapter02;

import javax.inject.Inject;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
//@Loggable //<-- the Loggable here is nosence and useless.
public class BookService {

  // ======================================
  // =             Attributes             =
  // ======================================
    
    @Inject
    @ThirteenDigits
    //the key is here, it use the object of Generator with 
    //@ThirteenDigits but @EightDigits
    private NumberGenerator numberGenerator;

  // ======================================
  // =          Business methods          =
  // ======================================

  public Book createBook(String title, Float price, String description) {
    Book book = new Book(title, price, description);
    book.setNumber(numberGenerator.generateNumber());
    return book;
  }
}
