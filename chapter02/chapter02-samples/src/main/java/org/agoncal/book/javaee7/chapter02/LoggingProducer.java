package org.agoncal.book.javaee7.chapter02;

import java.util.logging.Level;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class LoggingProducer {

  @Produces
  private Logger createLogger(InjectionPoint injectionPoint) {
    //how the level of log work???
    Logger log = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    log.setLevel(Level.ALL);
    return log;
  }
}
