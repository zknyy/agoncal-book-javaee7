package org.agoncal.book.javaee7.chapter05;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public interface Constants {

    /**
     * 02 [有问题]中用一个类定义了一个主表及其多个附属从表的关系,但是从表没有主键??? 
     * ex02_address是主表[有主键]
     * ex02_city和ex02_country是从表,拥有主表的外键 
     * 
     * 04[05] [复合主键]
     * 类News04中使用了一个@EmbeddedId将并入的类当做主键     
     * 由于使用@Embeddable修饰过的类NewsId04并入[类News04(表ex05_news)]
     * 是以@EmbeddedId方式并入的,所以并入后成为[类News04(表ex05_news)]的主键
     * 
     * 
     * 06 [推荐使用]中使用@IdClass(NewsId06.class)来定义复合主键,效果跟04一样
     * 
     * 09 [推荐使用]对@Lob类型使用惰性载入@Basic(fetch = FetchType.LAZY)
     * 
     * 11 [推荐使用]@Column对列重命名,设定为:非空字段,不能更新
     * 
     * 14 [推荐使用]定义非数据库内的字段用@Transient,
     *    [推荐使用]使用@Temporal将Java中的Date和Calendar类型映射到
     *      TemporalType    中的DATE,TIME,TIMESTAMP
     *      同时也是java.sql中的DATE,TIME,TIMESTAMP
     * 
     * 17 [推荐使用]用@Enumerated(EnumType.STRING)将枚举类型设为数据库字段
     * 
     * 20 不知道起什么作用:@Access(AccessType.FIELD)和@Access(AccessType.PROPERTY)???
     * 
     * 23[27] [推荐使用]主从表,1:N的映射[List]关系用,从表外键自动生成
     * @ElementCollection(fetch = FetchType.LAZY)//1:N关系,惰性载入
     * @CollectionTable(name = "ex27_tag")//从表的名称
     * private List<String> tags = new ArrayList<>();//从表数据对应的List
     * 
     * 24[28] [推荐使用]主从表,1:N的映射[Map]关系用,从表外键自动生成
     * @ElementCollection//1:N关系
     * @CollectionTable(name = "ex28_track")//从表的名称
     * @MapKeyColumn(name = "position")//把key映射为position字段
     * @Column(name = "title")//把value映射为title字段
     * private Map<Integer, String> tracks = new HashMap<>();
     * 
     * 25[29] 通过xml配置文件重新定义了类25--->表29的映射关系
     * <mapping-file>META-INF/ex25_book_mapping.xml</mapping-file>
     * 
     * 29 customer类和表,Address29类 仅仅作为表中的列来构成customer表
     * @Embeddable  用来声明可并入的类
     * @Embedded    在主类(表)中用来声明被并入的类
     * 
     * 如果使用@Column将get方法映射成为列,则需要修改类中成员的访问方式为PROPERTY
     * @Access(AccessType.PROPERTY)//AccessType的两种方式:FIELD和PROPERTY,
     *                              //难道不写的时候默认为FIELD???-->定义在属性上
     * @Access用于修饰类声明
     * 
     * 32[32] 实体主类用FIELD方式,嵌入类用PROPERTY方式定义表列
     * @Entity的Customer32类用@Access(AccessType.FIELD)修饰
     * @Embeddable的Address32类用@Access(AccessType.PROPERTY)修饰
     * 
     */
  // ======================================
  // =             Constants              =
  // ======================================

  public static final String PERSISTENCE_UNIT_NAME = "chapter05PU";

}
