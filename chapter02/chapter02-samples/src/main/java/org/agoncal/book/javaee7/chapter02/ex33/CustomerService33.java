package org.agoncal.book.javaee7.chapter02.ex33;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Transactional
//@Loggable33
public class CustomerService33 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private EntityManager em;

  // ======================================
  // =           Public Methods           =
  // ======================================

  //无论怎么调换这两个拦截器的位置和修改优先权,Loggable33总是会先执行???
  @Auditable33
@Loggable33
  public void createCustomer(Customer33 customer) {
        System.out.println("------------->>>begin createCustomer()");
    em.persist(customer);
        System.out.println("------------->>>end   createCustomer()");
  }

  public Customer33 findCustomerById(Long id) {
        System.out.println("------------->>>begin findCustomerById()");
        Customer33 temp = em.find(Customer33.class, id);
        System.out.println("------------->>>end   findCustomerById()");
        return temp;
    }
}