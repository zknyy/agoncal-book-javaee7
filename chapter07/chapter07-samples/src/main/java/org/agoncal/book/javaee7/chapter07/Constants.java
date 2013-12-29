package org.agoncal.book.javaee7.chapter07;


/**
 *
 * @author admin
 */
public class Constants {
    /**
     * GlassFish的设计和实现都遵循了JMX规范,因此也完全支持JMX,这种支持体现在:
     *  1. 它的命令行管理工具asadmin和管理控制台的功能
     *  2. 第三方管理工具比如JConsole的对其访问的支持
     *  3. 通过标准的或GlassFish特有的编程接口AMX对其资源的访问方式
     * 
     * jdbc/__default Java DB database是嵌入式Glassfish服务器的一个嵌入式数据库
     * 这个嵌入式数据库会在嵌入式Glassfish停止时被销毁
     * 
     * 
     * JavaEE 7中EJB只有SessionBean木有EntityBean(被Entity代替)
     * SessionBean有3种类型: @Stateful, @Stateless 和 @Singleton(也是无状态的)
     * SessionBean有3种接口情况: @Local 和/或 @Remote, 或 没有接口
     * 
     * 嵌入式容器(Embedded Container)
     * 容器提供的特性服务:
     *      远程通信:Remote client communication
     *      依赖注入:Dependency injection
     *      状态管理:State management
     *      池:Pooling
     *      组件生命周期:Component life cycle
     *      消息:Messaging
     *      事务管理:Transaction management
     *      安全:Security
     *      支持并发:Concurrency support[EJB中总是线程安全的]
     *      拦截器:Interceptors
     *      异步调用:Asynchronous method invocation
     * 
     * EJB Lite 是EJB的子集
     * 支持列表:Session beans (stateless, stateful, singleton) 	
     *          No-interface view 	
     *          Local interface 	
     *          Interceptors 	
     *          Transaction support 	
     *          Security 	
     *          Embeddable API     
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 另外一种 消息驱动bean: Message-driven beans (MDBs)--在此暂不讨论
     */
}
