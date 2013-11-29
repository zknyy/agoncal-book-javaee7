package org.agoncal.book.javaee7.chapter02.ex29;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
//@Interceptor特地说明当前类是个拦截器,并且要与
//@Interceptors进行区分,这个有s的是表示一个类或方法上叠加的一/多个拦截器
@Interceptor
//由于这个@Loggable29是继承于@InterceptorBinding,因此
//1. 这个@Loggable29只能用于申明拦截器类
//2. 在用@Loggable29申明拦截器的同时,此拦截器类还必须要显示的申明一个@Interceptor
@Loggable29
//综上所述,@Loggable29是一个 继承了拦截器(拥有了拦截器)的"注解" 
public class LoggingInterceptor29 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private Logger logger;

  // ======================================
  // =          Business methods          =
  // ======================================

  @AroundInvoke
  public Object logMethod(InvocationContext ic) throws Exception {
    logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
    logger.severe(">>>" + ic.getTarget().toString() + " - " + ic.getMethod().getName());
    try {
      return ic.proceed();
    } finally {
      logger.severe("<<<" + ic.getTarget().toString() + " - " + ic.getMethod().getName());
      logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
    }
  }
}