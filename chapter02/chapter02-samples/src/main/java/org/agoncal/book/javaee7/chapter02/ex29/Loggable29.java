package org.agoncal.book.javaee7.chapter02.ex29;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
//@InterceptorBinding用于指定一个 "注解" 为 "拦截器绑定类型"
//拦截器绑定 被用于指定 一个 拦截器类 的 绑定 到 目标类或方法
//[限制1]被标记为 "绑定" 类型的 "注解" 必须被用于一个 申明了@Interceptor的"拦截器"类
//[限制2]而且在用这个"绑定" 类型的 "注解" 申明拦截器的同时,此拦截器类还必须要显示的申明一个@Interceptor
//[限制3]@InterceptorBinding只能用于申明 "注解" 类型, 
//[限制4]而且其"绑定" 类型的 "注解" 必须@Target({METHOD, TYPE})
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
//综上所述,@Loggable29是一个 继承了拦截器(拥有了拦截器)的"注解" 
public @interface Loggable29 {
}
