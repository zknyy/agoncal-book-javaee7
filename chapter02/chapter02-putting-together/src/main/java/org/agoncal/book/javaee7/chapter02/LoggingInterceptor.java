package org.agoncal.book.javaee7.chapter02;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Interceptor
@Loggable //<-- if there is no Loggable, it throws an exception
public class LoggingInterceptor {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject //the object will be created in class LoggingProducer
    private Logger logger;

    // ======================================
    // =          Business methods          =
    // ======================================

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            logger.exiting("Finally--> "+ic.getTarget().getClass().getName(), ic.getMethod().getName());
        }
    }
}
