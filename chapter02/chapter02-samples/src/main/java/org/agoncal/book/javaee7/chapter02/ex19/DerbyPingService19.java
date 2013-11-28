package org.agoncal.book.javaee7.chapter02.ex19;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@ApplicationScoped
public class DerbyPingService19 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private Connection conn;

  // ======================================
  // =          Business methods          =
  // ======================================

  public void ping() throws SQLException {
    System.out.println("--->>> step 4");
//    conn.createStatement().executeQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
    ResultSet rs = conn.createStatement().executeQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
    System.out.println("--->>> step 5");
    rs.next();
    System.out.println("--->>> step 6");
    System.out.println("--->>> SELECT 1 FROM SYSIBM.SYSDUMMY1:"+rs.getString(1));
    System.out.println("--->>> step 7");
  }
}
