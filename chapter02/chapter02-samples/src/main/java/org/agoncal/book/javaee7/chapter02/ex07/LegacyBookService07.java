package org.agoncal.book.javaee7.chapter02.ex07;

import javax.inject.Inject;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class LegacyBookService07 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  @EightDigits07    //--> Use the specified annotation to get the object of different implementation
  private NumberGenerator07 numberGenerator;

  // ======================================
  // =          Business methods          =
  // ======================================

  public Book07 createBook(String title, Float price, String description) {
    Book07 book = new Book07(title, price, description);
    book.setIsbn(numberGenerator.generateNumber());
    return book;
  }
}
