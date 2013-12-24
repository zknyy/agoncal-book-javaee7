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
     */
    
  // ======================================
  // =             Constants              =
  // ======================================
  public static final String PERSISTENCE_UNIT_NAME = "chapter06PU";

}
