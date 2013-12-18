package org.agoncal.book.javaee7.chapter03;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

  @Inject
  @USA
  //这里的问题USA无法注入的问题无法解决
  //问题：03 Bean Validation(All)中，ZipCodeValidator.checker无法注入???
  private ZipCodeChecker checker;
  private Pattern zipPattern = Pattern.compile("\\d{5}(-\\d{5})?");

    public ZipCodeValidator() {
//        this.checker = new ZipCodeChecker();
    }

  @Override
  public void initialize(ZipCode zipCode) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null)
      return true;

    Matcher m = zipPattern.matcher(value);
//    if (!m.matches())
//      return false;
    return checker.isZipCodeValid(value);
  }
}
