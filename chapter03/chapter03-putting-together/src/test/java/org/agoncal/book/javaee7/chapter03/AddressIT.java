package org.agoncal.book.javaee7.chapter03;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import javax.inject.Inject;
import javax.validation.Validation;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class AddressIT {

  // ======================================
  // =              Methods               =
  // ======================================

  @Test
  public void shouldRaiseConstraintViolationCauseInvalidZipCode() {

    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();

    Address address = new Address("233 Spring Street", "New York", "NY", "DummyZip", "USA");

    Set<ConstraintViolation<Address>> violations = validator.validate(address);
    assertEquals(1, violations.size());

    vf.close();
  }
  /*
  @Test
  public void shouldRaiseConstraintViolationCauseInvalidZipCode() {

    //try to inject checker into ZipCodeValidator, but failed???
    Weld weld = new Weld();
    WeldContainer container = weld.initialize();

//    BookService bookService = container.instance().select(BookService.class).get();
//    @Inject 
//    ValidatorFactory vf;
//    @Inject 
//    Validator validator;
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();

     Address address = container.instance().select(Address.class).get();
     address.setStreet1("233 Spring Street");
     address.setCity("New York");
     address.setState("NY");
     address.setZipcode("DummyZip");
     address.setCountry("USA");
     
//    Address address = new Address("233 Spring Street", "New York", "NY", "DummyZip", "USA");

    Set<ConstraintViolation<Address>> violations = validator.validate(address);
    assertEquals(1, violations.size());

    vf.close();
  }
  */
}