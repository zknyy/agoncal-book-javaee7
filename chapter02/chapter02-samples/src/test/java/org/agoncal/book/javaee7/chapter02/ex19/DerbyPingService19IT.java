package org.agoncal.book.javaee7.chapter02.ex19;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.junit.Assert.assertTrue;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class DerbyPingService19IT {

  // ======================================
  // =             Attributes             =
  // ======================================

  protected static Weld weld;
  protected static WeldContainer container;

  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @BeforeClass
  public static void init() {
    weld = new Weld();
    container = weld.initialize();
  }

  @AfterClass
  public static void close() {
    weld.shutdown();
  }

  // ======================================
  // =              Methods               =
  // ======================================

  @Test
  public void shouldPingDatabase() throws Exception {
    System.out.println("--->>> step a");
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
    System.out.println("--->>> step b");
    Connection conn = DriverManager.getConnection("jdbc:derby:memory:chapter02DB;create=true", "APP2", "APP");
    System.out.println("--->>> step c");
//    conn.createStatement().executeQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");
    ResultSet rs = conn.createStatement().executeQuery("SELECT IBMREQD FROM SYSIBM.SYSDUMMY1");
    System.out.println("--->>> step d");
    rs.next();
    System.out.println("--->>> step e");
    System.out.println("--->>> SELECT IBMREQD FROM SYSIBM.SYSDUMMY1:"+rs.getString(1));
    conn.close();
  }

  @Test
  public void shouldPingDatabaseWithDispose() throws Exception {
    System.out.println("--->>> step 1");
    DerbyPingService19 pingService = container.instance().select(DerbyPingService19.class).get();
    System.out.println("--->>> step 2");
    pingService.ping();
    System.out.println("--->>> step 8");
  }
}