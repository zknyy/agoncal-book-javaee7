package org.agoncal.book.javaee7.chapter02.ex34;


import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Decorator
//a decorator can be declared as an abstract class so that it does not have to 
//implement all the business methods of the interfaces if there are many
public abstract class FromEightToThirteenDigitsDecorator34 implements NumberGenerator34 {

  // ======================================
  // =             Attributes             =
  // ======================================

  //Decorators must have a delegate injection point (annotated with @Delegate), 
  //with the same type as the beans they decorate (here the NumberGenerator interface).
  @Inject
  @Delegate
  private NumberGenerator34 numberGenerator;

  // ======================================
  // =          Business methods          =
  // ======================================

  public String generateNumber() {
    String issn = numberGenerator.generateNumber();
    String isbn = "13-84356" + issn.substring(1);
    return isbn;
  }
}