package org.agoncal.book.javaee7.chapter02.ex27;

import javax.annotation.PostConstruct;
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
@Interceptors(ProfileInterceptor27.class)
public class CustomerService27 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private EntityManager em;

  // ======================================
  // =          Lifecycle methods         =
  // ======================================

  public CustomerService27(){      
      System.out.println("----------------------->>>Excute Constructor!");
  }
  
  
  //这个方法被拦截器的@PostConstruct方法拦截,
  //这个@PostConstruct会让init()函数在构造函数执行后执行
  @PostConstruct
  public void init() {
    // ...
      System.out.println("---------->>>init()");
  }

  // ======================================
  // =           Public Methods           =
  // ======================================
//这个方法被拥有@AroundInvoke 的 profile()函数拦截
  public void createCustomer(Customer27 customer) {
      System.out.println("---------->>>createCustomer()");
    em.persist(customer);
  }

  public Customer27 findCustomerById(Long id) {
    return em.find(Customer27.class, id);
  }
}