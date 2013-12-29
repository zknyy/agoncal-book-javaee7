package org.agoncal.book.javaee7.chapter06;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com
 *         http://www.antoniogoncalves.org
 *         --
 */
public interface Constants {

    /**
     *  对于(持久化)实体管理器的取得:
     *  1. 可以用   EntityManagerFactory emf = Persistence .createEntityManagerFactory(" chapter06PU ");
     *              EntityManager em = emf.createEntityManager();
     *  2. 如果有Web或EJB容器则可以通过 @PersistenceContext 或 JNDI 取得
     *              @PersistenceContext(unitName = "chapter06PU")
     *              private EntityManager em;
     *  3. 另外也可以用@Inject和@Produces来注入
     * 
     * 注意:
     *  1. 03[L4-9]当持久化某个实体时,其引用的对象(如果是实体)也会自动被持久化
     *  2. 实体类没有序列化是因为,实体对象依然是引用传递,而不是值传递
     * 
     *  取得实体对象(数据|引用):
     *  em.find()           //[实体数据]如果没有数据,则返回null
     *  em.getReference()   //[对象引用]只取引用(通过其主键),而不取数据(惰性载入)
     *                      //如果没有引用则产生异常EntityNotFoundException 
     * 
     * 删除实体remove():    //删除后数据从数据库中删除,并脱离持久管理,但是还能当POJO使用
     *                      //删除主表的时候从表数据有可能会被删除,取决于级联关系
     * 
     * 删除孤立实体:        //@OneToOne (fetch = FetchType.LAZY, orphanRemoval=true )
     *                      //1:1关系中,当其关联一方删除时,同时删除另外一方
     *                      //1:N关系中,当主表数据删除时,同时删除其从表的数据
     * 
     * 不要随便用flush():   //可能会由于主从表创建的顺序问题不对并中间又flush,造成事物失败回滚
     *                      //从持久层向数据库中写数据(在事物提交之前)
     * 
     * 数据同步用refresh(): //从数据库向持久层读数据
     *                      //customer.setFirstName(" William ");//先改
     *                      //em.refresh(customer);//然后undo这个更改
     * 
     * 判断一个实体对象是否被持久层管理用:contains()
     * 
     * 将所有实体脱离持久层管理用:clear()
     * 
     * 用merge()更新数据:   //将POJO中的数据同步到持久层(写回数据库)
     *                      //tx.begin();
     *                      //em.merge(customer);
     *                      //tx.commit();
     * SELECT AVG, COUNT, MAX, MIN, SUM.  必须有GROUP BY
     * 数值(ABS, SQRT, MOD, SIZE, INDEX), 
     * 字符串(CONCAT, SUBSTRING, TRIM, LOWER, UPPER, LENGTH, LOCATE)
     * 时间(CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP)
     * 
     * FROM 
     * 
     * WHERE (AND, OR, =, >, >=, <, <=, <>,[NOT] BETWEEN, [NOT] LIKE(_ 单个匹配, % 多个匹配), [NOT] IN, 
     * IS [NOT] NULL, IS [NOT] EMPTY, [NOT] MEMBER [OF]
     * 参数绑定 WHERE c.firstName = ?1 AND c.address.country = ?2
     * 命名绑定 WHERE c.firstName = :fname AND c.address.country = :country
     * 
     * ORDER BY c.age DESC , c.address.country ASC 
     * 
     * DELETE FROM Customer c WHERE c.age = 18
     * 
     *
     * UPDATE Customer c SET c.firstName = 'TOO ELD' WHERE c.age > 80
     *
     * 
     * 查询的写法: Query query = em.createNamedQuery("findWithParam")
     *                              .setParameter("fname", "Vincent")
     *                              .setMaxResults(3);
     * 
     * 5种查询方法:
     *  1. 动态查询:    Query query = em. createQuery (...);
     *                  query.setParameter(...);
     * 
     *  2. 命名查询:    
     * @NamedQueries({ @NamedQuery(...),...,
     *  @NamedQuery(name = "findWithParam", query="select c from Customer c where c.firstName = :fname")
     * })
     * 
     * @NamedQuery ( name = "Customer.FIND_ALL", query ="select c from Customer c")
     * public static final String FIND_ALL = "Customer.findAll";
     * TypedQuery<Customer> query = em.createNamedQuery( Customer.FIND_ALL , Customer.class);
     *              
     *  3. Criteria API:
     * CriteriaBuilder builder = em.getCriteriaBuilder();
     * CriteriaQuery <Customer> criteriaQuery = builder.createQuery(Customer.class);
     * Root <Customer> c = criteriaQuery . from (Customer.class);
     * criteriaQuery . select (c). where (builder.equal( c.get("firstName") , "Vincent"));
     * Query query = em.createQuery( criteriaQuery ).getResultList();
     * List<Customer> customers = query.getResultList();
     * 
     *  4. SQL
     * @NamedNativeQuery(name = "findAll", query="select * from t_customer")
     * 
     * Query query = em.createNativeQuery (" SELECT * FROM t_customer ", Customer.class);
     * List<Customer> customers = query.getResultList();
     * 
     *  5. 存储过程
     * @NamedStoredProcedureQuery (name = "archiveOldBooks", procedureName = "sp_archive_books",
     * parameters = {
     *      @StoredProcedureParameter (name = " archiveDate ", mode = IN, type = Date.class),
     *      @StoredProcedureParameter(name = " warehouse ", mode = IN, type = String.class)
     * })
     * StoredProcedureQuery query = em. createNamedStoredProcedureQuery ("archiveOldBooks");
     * query.setParameter("archiveDate", new Date());
     * query.setParameter("maxBookArchived", 1000);
     * query.execute();
     * 
     * StoredProcedureQuery query = em. createStoredProcedureQuery ("sp_archive_old_books");
     * query. registerStoredProcedureParameter ("archiveDate", Date.class, ParameterMode.IN);
     * query. registerStoredProcedureParameter ("maxBookArchived", Integer.class, ParameterMode.IN);
     * query.setParameter("archiveDate", new Date());
     * query.setParameter("maxBookArchived", 1000);
     * query.execute();
     * 
     * 
     * 设置最大返回总数:
     * Query setMaxResults(int maxResult);
     * int getMaxResults();
     * Query setFirstResult(int startPosition);
     * int getFirstResult();
     * 
     * hints不知道是什么东西???
     * Query setHint(String hintName, Object value);
     * Map<String, Object> getHints();
     *
     * 缓存实体
     * @Entity @Cacheable(true)
     * public class Customer {...}
     * 
     * 在persistence.xml文件中能设置缓存机制:
     * ALL: 缓存所有实体
     * DISABLE_SELECTIVE: 缓存所有非@Cacheable(false)实体
     * ENABLE_SELECTIVE:  缓存所有@Cacheable(true)实体
     * NONE: 不缓存实体
     * UNSPECIFIED: 缓存行为未定义(默认值),由实现机制提供
     * 
     * 并发: 乐观锁,悲观锁
     * 锁类型:
     * OPTIMISTIC: 乐观锁
     * OPTIMISTIC_FORCE_INCREMENT: 乐观锁,并且强制实体列本版递增
     * PESSIMISTIC_READ:    悲观读锁,不需要重读(既保证干净读)
     * PESSIMISTIC_WRITE:   悲观写锁,事物中写串行化
     * PESSIMISTIC_FORCE_INCREMENT: 悲观锁,强制实体列本版递增
     * NONE:        不指定任何锁机制
     * 
     * 实体(表)的本版属性(列):其类型可以是int, Integer, short, Short, long, Long, Timestamp
     * @Version
     * private Integer version;//只读
     * 注意: 实际上,一个实体只要有@Version属性就会自动使用乐观锁
     * 
     * 版本号:
     *  乐观锁,在事物提交之前检查实体的版本号是否跟数据库中的一致,如果不一致则抛出异常(事物回滚)
     *  
     * 实体生命周期的回调Callbacks -- 仅仅用于声明实体类中的回调方法
     * @PrePersist 	在调用EntityManager.persist()之前执行
     * @PostPersist 	在调用EntityManager.persist()之后执行
     * @PreUpdate 	在调用EntityManager.merge() method之前执行
     * @PostUpdate 	在调用EntityManager.merge() method之后执行
     * @PreRemove 	在调用EntityManager.remove()之前执行
     * @PostRemove 	在调用EntityManager.remove()之后执行
     * @PostLoad 	在调用EntityManager.find() 或数据 refreshed 到内存之后执行
     * 注意:没有 @PreLoad方法
     * 1. 方法不能是静态或final类型
     * 2. 一个方法可以标识为多个生命周期事件
     * 3. 可以抛出未检测的运行时异常(引起事物回滚),但是不能抛出检测类型异常
     * 4. 方法能调用JNDI, JDBC, JMS 和 EJBs 但是不能调用EntityManager 或 Query操作
     * 5. 方法可以继承,继承后先调用父类方法
     * 6. 方法中的操作会出发事件的级联(cascading)关系,比如级联删除从表数据
     * 
     * 监听器更加灵活的定义了回调方法的实现,如下(如果参数是Object就意味着所有实体类)
     * @PostLoad
     * @PostPersist
     * @PostUpdate 
     * public void calculateAge(Customer customer) {...}
     * 注意:监听器类必须有无参数的构造器,并参数为这个实体类型(父类型)
     * 
     * 一个实体可以定义多个监听器,如下
     * @EntityListeners({DataValidationListener.class, AgeCalculationListener.class})
     * @Entity
     * public class Customer {...}
     * 
     * 缺省实体监听器定义在xml文件中
     * <persistence-unit-metadata>
     *  <persistence-unit-defaults>
     *   <entity-listeners>
     *      <entity-listener class="org.agoncal.book.javaee7.chapter06.DebugListener"/>
     *   </entity-listeners>
     *  </persistence-unit-defaults>
     *</persistence-unit-metadata>
     * 
     * 但是用@ExcludeDefaultListeners 可以将实体类从缺省监听器中剔除
     * @ExcludeDefaultListeners
     * @Entity
     * public class Customer {...}
     * 
     */
    
  // ======================================
  // =             Constants              =
  // ======================================
  public static final String PERSISTENCE_UNIT_NAME = "chapter06PU";

}
