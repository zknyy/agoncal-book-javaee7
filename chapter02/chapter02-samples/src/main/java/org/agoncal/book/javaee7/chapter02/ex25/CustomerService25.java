package org.agoncal.book.javaee7.chapter02.ex25;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
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
public class CustomerService25 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private EntityManager em;

  // ======================================
  // =           Public Methods           =
  // ======================================

  //这个拦截器使用的是LoggingInterceptor25中的init构造器@AroundConstruct拦截
  @Interceptors(LoggingInterceptor25.class)
  public CustomerService25(){
      System.out.println("---->>>Execute constructor!");
  }
  
  
  //这个拦截器使用的是LoggingInterceptor25中的logMethod方法调用@AroundInvoke拦截
  @Interceptors(LoggingInterceptor25.class)
  public void createCustomer(Customer25 customer) {
    em.persist(customer);
  }

  public Customer25 findCustomerById(Long id) {
    return em.find(Customer25.class, id);
  }
}