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
     * 实体(@entity)类:其类对应表, 属性对应列
     * 没有表的类:   
     *      非实体类(nonentity), 无对应的表或字段
     *      嵌入类(@Embeddable), 其属性所对应的字段被其他类以has(引用)的方式映射到owner类对应的表结构中
     *      映射超类(Mapped Superclass), 其属性所对应的字段被其他类以is a(继承)的方式映射到owner类对应的表结构中
     *  用@Transient来忽略映射
     * 
     * -----------------------------------------------
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
     * 34[34] Customer34和Address34数据库中主从表的关系，在类层面可以定义1:1[单向unidirectional], 1:1可以nullable,so1:0 或1:1
     * 
     * 39[39] [推荐使用]Customer39和Address39数据库中主从表的关系，在类层面可以定义1:1关系
     * @OneToOne(fetch = FetchType.LAZY)//1:1关系,惰性载入
     * @JoinColumn(name = "add_fk", nullable = false)// 定义外键列名, 不能为空,必须1:1
     * 
     * 40[40] 含混类[无@OneToMany 单向]1:N(数据库中为N:M)关系,[2个类—3个表], Order40和OrderLine40之间类1:N(数据库中为N:M)关系,通过自动生成的连接表EX40_ORDER_EX40_ORDER_LINE来实现
     * 
     * 43[43] 含混类[有@OneToMany 单向]1:N(数据库中为N:M)关系,[2个类—3个表], 通过定义的连接表ex43_jnd_ord_line和外键列名order_fk, order_line_fk来实现
     * @OneToMany//1:N关系
     * @JoinTable(name = "ex43_jnd_ord_line", joinColumns = @JoinColumn(name = "order_fk"), inverseJoinColumns = @JoinColumn(name = "order_line_fk"))//定义的连接表及其两个外键列名order_fk, order_line_fk
     * 
     * 45[45] [推荐使用] 1:N(数据库中为1:N)关系, 通过定义从表外键列名order_fk来实现
     * @OneToMany(fetch = FetchType.EAGER)
     * @JoinColumn(name = "order_fk")
     * private List<OrderLine45> orderLines;
     * 
     * -----------------------------------------------------------------------
     * 拥有者owner [使用@ManyToMany], 
     * 被拥有者reverse owner [使用@ManyToMany(mappedBy = "appearsOnCDs")] 
     * 
     * 注意:无论是1:1还是M:N的双向关系,都可以做为拥有或被拥有者,
     * 但另外一边必须用mappedBy,否则会认为两边都是拥有者,结果是４个表
     * 
     * FetchType:
     * Annotation 	Default Fetching Strategy
     * @OneToOne 	EAGER
     * @ManyToOne 	EAGER
     * @OneToMany 	LAZY
     * @ManyToMany 	LAZY
     * ----------------------------------------------------------------------
     * 
     * 46[46] [推荐使用] M:N(数据库中为M:N)关系, 通过定义的连接表ex46_jnd_art_cd和外键列名artist_fk, cd_fk来实现, 2个类中都有对方的List
     * class Artist46中:
     * @ManyToMany
     * @JoinTable(name = "ex46_jnd_art_cd", joinColumns = @JoinColumn(name = "artist_fk"), inverseJoinColumns = @JoinColumn(name = "cd_fk"))
     * private List<CD46> appearsOnCDs;
     * 
     * class CD46中:
     * @ManyToMany(mappedBy = "appearsOnCDs")
     * private List<Artist46> createdByArtists;
     * 
     * ----------------------------------------------------------------------
     * 49[49] 排序
     * @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
     * @OrderBy("postedDate DESC") //以postedDate字段递减
     * private List<Comment49> comments;
     * 
     * -------------------------------------------
     * 如果数据库不能自动优化其索引结构, 则@OrderColumn 和 @OrderBy是必须有其一的
     * 51[51] [有序列@OrderColumn] 有序列是由主表强加于关联表的..........
     * @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)//自动生成表EX51_NEWS_EX51_COMMENT
     * @OrderColumn(name = "publication_index")//有序列是由主表强加于关联表的
     * private List<Comment51> comments;
     * 
     * 类继承与表结构的对应关系
     * 1. single-table-per-class: [一个表]表示所有类的数据结构
     *          (通过共享冗余[空]列集成,通过特定字段区分)
     *      1.1. 53[53](默认)无注释的时候用一个表(外加一个DTYPE字段)装所有字段
     * 
     *      1.2. 56[56]通过注释来定义(discriminator:鉴别者,鉴别器)
     *      @Entity
     *      @Inheritance(strategy = InheritanceType.SINGLE_TABLE)//用一个表装所有字段(外加一个指定的字段)
     *      @DiscriminatorColumn (name="disc", discriminatorType = DiscriminatorType.CHAR)//定义指定的字段
     *      @DiscriminatorValue("I")//定义当前类在数据库中指定字段的标识值
     * 
     * 2. joined-subclass : [多个表][主从表结构]主从表表示父子类关系,子表共享主表字段
     *          (通过共享主表字段集成,通过特定字段区分)
     *      59[59] 三个表,父类为主表,子类为从表
     *      @Entity
     *      @Inheritance(strategy = InheritanceType.JOINED)//主表
     *      @Table(name = "ex59_item")//主表名称[可以省略]
     *      注意:在主表里面依然有DTYPE字段来区分数据类型,依然可以用
     *          @DiscriminatorColumn和@DiscriminatorValue来定义其列名和标识值
     * 
     * 3. table-per-concrete-class : [多个表][无主从表结构]每个表中有所有字段
     *    60[60](
     *              不需要冗余列,不需要共享表,不需要通过特定字段区分
     *              但是从表中会有主表的冗余数据.    
     *          )
     *      @Entity
     *      @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
     *      @Table(name = "ex60_item")
     * 
     * 
     *      //61[61]可以在子类[CD]中把父类继承来的属性映射为表中不同名的字段上
     *      @AttributeOverrides({
     *          @AttributeOverride(name = "id", column = @Column(name = "cd_id")),
     *          @AttributeOverride(name = "title", column = @Column(name = "cd_title")),
     *          @AttributeOverride(name = "description", column = @Column(name = "cd_description"))
     *      )}
     * 
     * 另外: 被嵌入的类[Embeddables]中的属性也能通过继承传给子类,因此也可以用
     *      AttributeOverride在子类中重新定义
     * 
     * 非实体(nonentity)类[非实体类]: 63[63]就算是实体类的父类,
     *      如果没有声明@Entity(类或属性)就不会映射到数据库(的表或字段)中
     * 
     *      
     * 映射超类@MappedSuperclass (Mapped Superclass):  66[66]
     * 就是通过其父类属性继承来实现其字段在子类映射的表中重用的内嵌类(embeddable classes)
     * 由于映射超类不是表,所以不能出现@Table
     *  @MappedSuperclass
     *  @Inheritance(strategy = InheritanceType.JOINED)//通过继承来映射其属性到字段
     *  
     * 
     * -------------------------------------------
     * 
     * 
     * 
     * 
     */
    // ======================================
  // =             Constants              =
  // ======================================

  public static final String PERSISTENCE_UNIT_NAME = "chapter05PU";

}
