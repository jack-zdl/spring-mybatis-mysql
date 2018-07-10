# spring-mybatis-mysql
```
////////////////////////////////////////////////////////////////////  
//                          _ooOoo_                               //  
//                         o8888888o                              //      
//                         88" . "88                              //      
//                         (| ^_^ |)                              //      
//                         O\  =  /O                              //  
//                      ____/`---'\____                           //                          
//                    .'  \\|     |//  `.                         //  
//                   /  \\|||  :  |||//  \                        //      
//                  /  _||||| -:- |||||-  \                       //  
//                  |   | \\\  -  /// |   |                       //  
//                  | \_|  ''\---/''  |   |                       //          
//                  \  .-\__  `-`  ___/-. /                       //          
//                ___`. .'  /--.--\  `. . ___                     //      
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //  
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //      
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //  
//      ========`-.____`-.___\_____/___.-`____.-'========         //      
//                           `=---='                              //  
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //  
//         佛祖保佑       永无BUG     永不修改                     //  
////////////////////////////////////////////////////////////////////  
```
深入研究spring-mybatis框架对mysql数据库的使用，学习spring+springmvc+mybatis框架学习和使用。
详情请联系作者zhangdelei000@gmail.com
主要讲解mybatis的知识和在mysql下的使用规范，及其其他数据库的使用如redis.
## 为什么需要mybatis对数据库进行操作
```
当我们采用面向对象语言进行编程时，我们采用面向对象分析，设计，编程。
但是到了持久层访问时，又必须重返关系型数据库的访问方式。这是糟糕的。
所以我们需要一个工具，将关系型数据库包装成面向对象的模型，这就是ORM.
采用mybatisORM框架的优势，允许直接编写SQL语句，更加灵活，便于sql语句调优。
```
## 1mysql下mybatis框架的基本sql数据操作语言（DML）语句
```
1 基础sql语句
    insert语句 insert into tbl ()value()
    delete语句 DELETE FROM Person WHERE LastName = 'Wilson'
    update语句 update tbl set a = b where id =id
    select语句 SELECT * FROM Persons WHERE id=id
2 sql优化
    使用  UNION 或者  UNION ALL
        UNION  在结果中截取不同的值 UNION ALL 在结果中可以允许重复的值
        select org_id from  tsys_user UNION (UNION ALL) select org_id from tsys_organization 
        联合查询 不可以使用 * 代替查询选项
    查询中尽量避免使用* ORACLE在解析的过程中, 会将’*’ 依次转换成所有的列名, 这个工作是通过查询数据字典完成的, 
        这意味着将耗费更多的时间
    整合简单,无关联的数据库访问
        如果你有几个简单的数据库查询语句,你可以把它们整合到一个查询中(即使它们之间没有关系)
    最高效的删除重复记录方法 
        DELETE FROM EMP E WHERE E.ROWID > (SELECT MIN(X.ROWID)
    尽量多的使用commit
        只要有可能,在程序中尽量多使用COMMIT, 这样程序的性能得到提高,需求也会因为COMMIT所释放的资源而减少:
        COMMIT所释放的资源:
        a. 回滚段上用于恢复数据的信息.
        b. 被程序语句获得的锁
        c. redo log buffer 中的空间
        d. ORACLE为管理上述3种资源中的内部花费
    使用表的别名
```
## 2mysql下的事物管理和mybatis的事物管理
```
数据库的4种隔离级别，spring的7中传播行为
    事务四个属性 原子性是基础，隔离性是手段，持久性是目的，最终是一致性。
    事务隔离界别
    seriallizable（TransactionDefinition.ISOLATION_SERIALIZABLE） 不允许脏读 不允许不可以 不允许幻读 
    repeatable_read（TransactionDefinition.ISOLATION_REPEATABLE_READ） 不允许脏读 不允许不可以 允许幻读
    read_committed(TransactionDefinition.ISOLATION_READ_COMMITTED)  不允许脏读 允许不可以 允许幻读
    read_uncommitted(TransactionDefinition.ISOLATION_READ_UNCOMMITTED) 允许脏读  允许不可重复 允许幻读
    Dirty Read 脏读 
    A 事务的查询 查询到  B事务的其中过程中的结果。
    Unrepeatable Read 不可重复读
    A 事务 查询了多次，多次的结果不一致。
    当第二个事务多次访问同一行而且每次读取不同的数据时，会发生不一致的分析问题。不一致的分析与未确认的相关性类似，因为其它事务也是正在更改第二个事务正在读取的数据。然而，在不一致的分析中，第二个事务读取的数据是由已进行了更改的事务提交的。而且，不一致的分析涉及多次（两次或更多）读取同一行，而且每次信息都由其它事务更改；因而该行被非重复读取。
    在一个事务中前后两次读取的结果并不致，导致了不可重复读。
    e.g.
    1.在事务1中，Mary 读取了自己的工资为1000,操作并没有完成
    2.在事务2中，这时财务人员修改了Mary的工资为2000,并提交了事务.
    3.在事务1中，Mary 再次读取自己的工资时，工资变为了2000
    解决办法：如果只有在修改事务完全提交之后才可以读取数据，则可以避免该问题。
    Phantom Read 幻读
    当对某行执行插入或删除操作，而该行属于某个事务正在读取的行的范围时，会发生幻像读问题。事务第一次读的行范围显示出其中一行已不复存在于第二次读或后续读中，因为该行已被其它事务删除。同样，由于其它事务的插入操作，事务的第二次或后续读显示有一行已不存在于原始读中。
     　　e.g. 目前工资为1000的员工有10人。
        1.事务1,读取所有工资为1000的员工。
        2.这时事务2向employee表插入了一条员工记录，工资也为1000
        3.事务1再次读取所有工资为1000的员工 共读取到了11条记录，
    A 
    （required）TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
         这个是最常见的传播行为,所以这个级别通常能满足处理大多数的业务场景。
    (requires_new)TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
        执行新事务完成以后，当前事务恢复再执行。这是一个很有用的传播级别，举一个应用场景：现在有一个发送100个红包的操作，
        在发送之前，要做一些系统的初始化、验证、数据记录操作，然后发送100封红包，然后再记录发送日志，发送日志要求100%的准确，
        如果日志不准确，那么整个父事务逻辑需要回滚。怎么处理整个业务需求呢？就是通过这个PROPAGATION_REQUIRES_NEW 级别的事务传播
        控制就可以完成。发送红包的子事务不会直接影响到父事务的提交和回滚。
    (supports)TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，
        则以非事务的方式继续运行。应用场景较少。
    (not_supported)TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
        非事务执行后在执行当前事务。
    （propagation_never）TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。不能存在上下文事物
    （propagation_mandatory）TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，
        则抛出异常。(上下文必须有事物)
    (propagation_nested)TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；
        如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。
     spring 
        事务超时 - 为了解决事务时间太长，故意设置最大时长，如果超过了，回滚事务。
        只读事务 - 忽略不需要事务的方法。
1 问题：当外部一个事物方法调用内部另二个事物方法时，这里出现了问题，
    外部事物不会滚。内部事物出现异常的回滚，不出现异常不会滚。
  解决：问题原因没有真正的出现异常，我在末尾finally{}返回数据，被认为没有发现异常，所以没有回滚。
  默认的食物传播行为 是
2 问题：事件不回滚。
问题原因没有真正的出现异常，我在末尾finally{}返回数据，被认为没有发现异常，所以没有回滚。
--配置文件出错
@Transactiona
 属性名                                       类型                             说明
isolation     枚举org.springframework.transaction.annotation.Isolation的值   事务隔离级别
noRollbackFor     Class<? extends Throwable>[]           一组异常类，遇到时不回滚。默认为{}
noRollbackForClassName          Stirng[]               一组异常类名，遇到时不回滚，默认为{}
propagation  枚举org.springframework.transaction.annotation.Propagation的值  事务传播行为
readOnly              boolean                                                 事务读写性
rollbackFor           Class<? extends Throwable>[]                 一组异常类，遇到时回滚
rollbackForClassName    Stirng[]                                 一组异常类名，遇到时回滚
timeout               int                                            超时时间，以秒为单位
value                String                         可选的限定描述符，指定使用的事务管理器
```
## 3打印日志信息，将日志下载到某个位置等待查看
```
详情查看log4j.properties.可以控制输出级别，控制台显示，log文件输出。
```
## 4spring的异常处理对应http状态码
```
   1将各种异常用spring异常处理修饰，当发生这个异常时，返回对应的状态码，就相关原因。
   2将异常放在AOP中使用，就不用每个异常手写一次
   3可以将所有的结果都映射为异常，包括204成功无返回结果，映射为异常来返回状态码
```
## 5http接口开发
```
    httpclient发送get post put delete
    使用的jar 是httpcomponents的jar
    不推荐使用httpclient的jar
```
## 6spring的异步消息
```
1异步发送消息 ActiveMQ 消息队列的形式或主题的形式 异步接受消息 消息驱动POJO的类型 基于消息的RPC
他是定义了API不推荐使用
2异步消息机制 AMQP 跨语言 跨平台 2017426必须做完
AMQP 在client端以AMQP的形式发送，在server端以AMQP的形式接受。
github实例代码
https://github.com/chwshuang/rabbitmq/tree/master/src/main/java/com/aitongyi/rabbitmq/publish
AMQP 使用rabbitmq来实现，就像发邮件一样，但是这必须保证消息生产者有一个rabbitmq（需要下载安装的）机制，
消息接受者也有rabbitmq机制来接受。
完成了队列的消息机制。
未完成主题订阅的消息机制
```
## rabbitmq消息
```
```
## spring的consul的服务发现
```
1 服务的注册和发现
2 key vaule形式 配置文件处理
3 健康检查
4 多数据中心
```
## 7spring文件下载
```
第一基于前后端一起的
第二前后端分离，怎么办？
以springmvc restful风格的文件上传，下载
上传文件 第一种是基于流的方式上传文件 CommonsMultipartFile
上传文件 第二种是基于spring的方式 推荐使用spring的方式速度快
```
## 8spring的缓存
```
相关网址：http://www.cnblogs.com/jingmoxukong/p/5975994.html
mybatis的缓存一级缓存和二级缓存 他是对于查询结果的缓存，是执行sql结果的缓存，再次执行的时候会缓存中获得
spring-ehcache的缓存管理   可以对某个方法结果进行缓存，执行该方法时会直接返回之前的结果
配置spring-ehcache.xml
在applicationContext.xml中启动cache注解，
在方法中去使用
@Cacheable("definitionCache") --对结果进行缓存
	public List<DefinitionEntity> listFromCache() {
		return definitionDao.list(null);
}
redis作为内存数据库，作为缓存数据库
“因为redis数据库是将kv键值对存在内存中，只要主机不关机就会数据就会存在，他还会按照某些规则去自行删除”
```
## 9spring的认证
```
shiro 和 security
shiro的管理
    设置spring-shiro管理，加入web.xml中的配置，就可以进行实时检查
    pox.xml添加jar
```
# spring将传递json参数封装为对象和进行校验
```
1 使用jar
 <!--校验数据
            必须依赖正确的jar，之前就是错误的jar
        -->
        <!-- https://mvnrepository.com/artifact/javax.validation/com.springsource.javax.validation -->
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>1.1.0.Final</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>5.0.1.Final</version>
        </dependency>
        <!-- 校验数据jar结束-->
 2创建VO类,添加相关校验注解
 3在controller中添加@valid @RequestBody 就可以校验了在clud的post方法中我是用了这个方法
```
# spring使用拦截器进行登录验证和使用过滤器进行乱码过滤
```
1在springmvc配置文件中设置登录前验证拦截器：作用拦截没有登录的用户
2必须在web.xml中设置过滤器，根据你设置多个过滤器的顺序来设置过滤。
```
# ms-api连接数据库
```
1连接mysql    OK   使用JNDI的连接方式
2连接redis    OK   使用jedis连接方法，采用sentinel（哨兵）的方式连接可以搭建一个redis集群
数据库的长连接和短连接区别（连接池的情况下）
```
# redis的基础操作-增删改查
```
    Redis是单线程的，基于事件驱动的，Redis中有个EventLoop，EventLoop负责对两类事件进行处理：
        一类是IO事件，这类事件是从底层的多路复用器分离出来的。
        一类是定时事件，这类事件主要用来事件对某个任务的定时执行。
    redis对缓存失效二种方法
        1 客户端去查询时进行的消极处理
        2 主线程定时主动处理
    save 接口= update 接口 相同的kay进行存值时会覆盖原先的value
    1 redis缓存失效机制，EXPIRE命令说起。EXPIRE允许用户为某个key指定超时时间，当超过这个时间之后key对应的值会被清除。
    2 redis数据库中有二个位置 
        1 dict位置 存储正常数据
        2 expires使用来存储关联了过期时间的key的
    3 redis查询机制  expireIfNeeded
        1 从expires中查找key的过期时间，如果不存在说明对应key没有设置过期时间，直接返回。
        2 如果是slave机器，则直接返回，因为Redis为了保证数据一致性且实现简单，将缓存失效的主动权交给Master机器，
          slave机器没有权限将key失效。
        3 如果当前是Master机器，且key过期，则master会做两件重要的事情：1）将删除命令写入AOF文件。
          2）通知Slave当前key失效，可以删除了。
        4 master从本地的字典中将key对于的值删除。
    4 主动失效机制
        服务端定时的去检查失效的缓存，如果失效则进行相应的操作。
```
# ms-web监控平台
```
1当压力测试时，页面需要去监控展示出多少个查询业务请求，
多少个查询数据库请求。检测软件业务的状态（多少请求多少查询），检测数据库的状态（多少数据库sql语句查询）
2当压力测试时，每次查询的耗时时间。
```
# http自动测试工具
```
测试接口的连接情况，在密集的请求下应用是否可以继续，为了分布式开发做准备
ab=apache bench测试自动化测试
测试结果 发送get方法并发1000没问题
```
# 分布式开发
```
当一个程序被大规模人使用时，就会触发分布式开发，这就考验着程序的代码质量，
主要问题：
1CPU的消耗分析
2文件IO的消耗分析   文件的上传下载模拟文件IO消耗
3网络IO的消耗分析   远程调用接口来模拟网络IO的消耗
4内存消耗分析       增加线程的使用来模拟内存消耗
5程序执行慢分析

问题：java的并发测试 就是关于多线程，高并发
```
# spring的线程池
```
1 配置线程池
 <bean id="threadPool" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
        <!-- 核心线程数 -->
        <property name="corePoolSize" value="3"/>
        <!-- 最大线程数 -->
        <property name="maxPoolSize" value="10"/>
        <!-- 队列最大长度 >=mainExecutor.maxSize -->
        <property name="queueCapacity" value="25"/>
        <!-- 线程池维护线程所允许的空闲时间 -->
        <property name="keepAliveSeconds" value="300"/>
        <!-- 线程池对拒绝任务(无线程可用)的处理策略 ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,
        1、直接丢弃（DiscardPolicy）
        2、丢弃队列中最老的任务(DiscardOldestPolicy)。
        3、抛异常
        4、将任务分给调用线程来执行。
        如果执行器已关闭,则丢弃. -->
        <property name="rejectedExecutionHandler">
            <bean class="java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy"/>
        </property>
    </bean>
2使用它
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
     threadPoolTaskExecutor.execute(new Runnable(){
                public void run(){
                //you code
                }
            });
```
# mybatis的SQL注入工具
```
测试mybatis的sql有无注入风险。
MYSQL的慢查询   ok 在文件中设置一下
              my.ini    log_slow_queries = showlog
                        #slow_query_log = ON
                        long_query_time = 1
多个MYSQL数据库软件做读写分离
```
# JVM的性能测试及其调优
```
基础知识：
1内存分配
2GC机制
3工具检测
4调优方法
```
# 基础原理和知识
```
1 CAP定理
    一致性(Consitency)，可用性(Availability)和分区容忍性(Partition Tolerance)中三选二。
    CA无法同时选择
    一般看你的业务情况，数据不重要你可以选AP,数据重要你可以选择CP.
2 jdk8的新特性及其使用
java语言新特性
     Lambda表达式和函数式接口(Groovy、Scala类似)
     接口的修改 默认方法和静态方法
     可以使用重复注解，在同一个方法上面可以有重复注解
     扩展的注解的应用范围，注解可以使用在任何元素：局部变量，接口类型，超类，接口实现
java编译器的新特性
    参数名称
java官方库
    增加了date和time类
    增加optional,来解决空指针异常
    增加Streams （java.util.stream）将生成环境的函数式编程引入了Java库中
    提供  Nashorn JavaScript引擎
    Base64加密编码
    并行数组
    并发性
java新工具
    类依赖分析器：jdeps
3 restful和rpc
    restful是一种设计风格,提供了设计原则和约束条件，他不是架构,满足这些约束就是restful架构。
    rpc远程调用,从客户端机器通过参数传递的方式调用另一台机器的一个函数或者一个方法。并得到返回结果。
    RPC回隐藏底层的通讯细节，（不需要直接处理Socket通讯或Http通讯）
    RPC 是一个请求响应模型。客户端发起请求，服务器返回响应（类似于Http的工作方式）
    RPC 在使用形式上像调用本地函数（或方法）一样去调用远程的函数（或方法）。
4 spring使用RMI和Hessian,Burlap,HTTP invoker
    RMI发布，访问远程服务，有时不能穿过防火墙,必须在java环境中
    Hession是基于二进制有带宽优势，Burlap是XML,可读性高，非java环境
    HttP invoker
```
## spring aop
```
    链接地址：http://www.jianshu.com/writer#/notebooks/10847232/notes/12195951
    对于方法的增强叫织入，对类的增强叫引入。
    Spring + AspectJ 使用AspectJ切点表达式。这些注释表达式是AspectJ的特点。
    execution(* aop.demo.GreeImpl.* (..))
    第一个 * 表示方法的返回值是任意的
    第二个 * 表示匹配该类中所有的方法
    (..)表示方法的参数的任意。
    如果你想匹配指定的方法 将第二个 * 改为指定的方法。
    
```
## spring ioc
```
    IoC理论:借助于“第三方”实现具有依赖关系的对象之间的解耦，
        1) 软件系统在没有引入IoC容器之前，对象A依赖对象B，那么A对象在实例化或者运行到某一点的时候，自己必须主动创建对象
          B或者使用已经创建好的对象B，其中不管是创建还是使用已创建的对象B，控制权都在我们自己手上。
        2）如果软件系统引入了Ioc容器之后，对象A和对象B之间失去了直接联系，所以，当对象A实例化和运行时，如果需要对象B的
          话，IoC容器会主动创建一个对象B注入到对象A所需要的地方。
        3）通过前面的对比，可以看到对象A获得依赖对象B的过程，由主动行为变成了被动行为，即把创建对象交给了IoC容器处理，控
          制权颠倒过来了，这就是控制反转的由来！
        IOC原理 这也就是所谓“控制反转”的概念所在：控制权由应用代码中转到了外部容器，控制权的转移，即所谓反转。
    Bean的生命周期：
        spring bean的完整生命周期从创建Spring容器开始，知道spring容器销毁bean.
        bean自身的方法，Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法
        Bean级生命周期接口方法   BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法
        容器级生命周期接口方法　 InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，
        一般称它们的实现类为“后处理器”。
        工厂后处理器接口方法    AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等
        非常有用的工厂后处理器　　接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用
            实例BeanFactoryPostProcessor实例化出 
                容器级别生命周期接口 BeanPostProcessor ，InstantiationAwareBeanPostProcessor 。
                InstantiationAwareBeanPostProcessor 执行before方法对对象属性进行修改
                在执行Bean构造器
                InstantiationAwareBeanPostProcessor 执行Property方法
                为bean注入属性
                调用Bean级生命周期接口方法
                BeanNameAware  BeanFactoryAware
                调用容器级生命周期接口方法　
                    BeanPostProcessor 
                调用Bean级生命周期接口方法
                InitializingBean
                调用Bean自身的方法
                nit-method
                调用容器级生命周期接口方法　
                BeanPostProcessor  after方法 修改对象属性
                InstantiationAwareBeanPostProcessor  after方法 修改对象属性
                ....初始化成功后正常使用......
                调用Bean级生命周期接口方法
                DiposableBean
                调用Bean自身的方法
                destroy-method
                销毁
    测试结果
    现在开始初始化容器
    2014-5-18 15:46:20 org.springframework.context.support.AbstractApplicationContext prepareRefresh
    信息: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@19a0c7c: startup date 
    [Sun May 18 15:46:20 CST 2014]; root of context hierarchy
    2014-5-18 15:46:20 org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
    信息: Loading XML bean definitions from class path resource [springBeanTest/beans.xml]
    这是BeanFactoryPostProcessor实现类构造器！！
    BeanFactoryPostProcessor调用postProcessBeanFactory方法
    这是BeanPostProcessor实现类构造器！！
    这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！
    2014-5-18 15:46:20 org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
    信息: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@9934d4: 
    defining beans [beanPostProcessor,instantiationAwareBeanPostProcessor,beanFactoryPostProcessor,person]; 
    root of factory hierarchy
    InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法
    【构造器】调用Person的构造器实例化
    InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法
    【注入属性】注入属性address
    【注入属性】注入属性name
    【注入属性】注入属性phone
    【BeanNameAware接口】调用BeanNameAware.setBeanName()
    【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()
    BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！
    【InitializingBean接口】调用InitializingBean.afterPropertiesSet()
    【init-method】调用<bean>的init-method属性指定的初始化方法
    BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！
    InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法
    容器初始化成功
    Person [address=广州, name=张三, phone=110]
    现在开始关闭容器！
    【DiposibleBean接口】调用DiposibleBean.destory()
    【destroy-method】调用<bean>的destroy-method属性指定的初始化方法
    spring bean的作用域
        最常用的singleton和prototype。
    1 singleton：单例模式，在整个Spring IoC容器中，使用singleton定义的Bean将只有一个实例
    2 prototype：原型模式，每次通过容器的getBean方法获取prototype定义的Bean时，都将产生一个新的Bean实例
    3 request：对于每次HTTP请求，使用request定义的Bean都将产生一个新实例，即每次HTTP请求将会产生不同的Bean实例。
        只有在Web应用中使用Spring时，该作用域才有效
    4 session：对于每次HTTP Session，使用session定义的Bean都将产生一个新实例。同样只有在Web应用中使用Spring时，
        该作用域才有效   
    5 globalsession：每个全局的HTTP Session，使用session定义的Bean都将产生一个新实例。典型情况下，
        仅在使用portlet context的时候有效。同样只有在Web应用中使用Spring时，该作用域才有效
        
    源码解析
    主要的二个jar，是org。springframework.beans和org.springframework.context
    org,springframework.beans下的factory的BeanFactory接口提供了一个先进的配置机制能够管理任何类型的对象。
    需要配置元数据。
```             
## spring mvc
```
        1.用户向服务器发送请求，请求被Spring 前端控制Servelt DispatcherServlet捕获；
    	2.DispatcherServlet对请求URL进行解析，得到请求资源标识符（URI）。然后根据该URI，调用HandlerMapping获得
            该Handler配置的所有相关的对象（包括Handler对象以及Handler对象对应的拦截器），最后以HandlerExecutionChain
            对象的形式返回；
    	3.DispatcherServlet 根据获得的Handler，选择一个合适的HandlerAdapter。（附注：如果成功获得HandlerAdapter后，
            此时将开始执行拦截器的preHandler(...)方法）
            4.提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)。 在填充Handler的入参过程中，
            根据你的配置，
            Spring将帮你做一些额外的工作：
            HttpMessageConveter:将请求消息（如Json、xml等数据）转换成一个对象，将对象转换为指定的响应信息
            数据转换：对请求消息进行数据转换。如String转换成Integer、Double等
            数据根式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等
            数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中
    	5.Handler执行完成后，向DispatcherServlet 返回一个ModelAndView对象；
    	6.根据返回的ModelAndView，选择一个适合的ViewResolver（必须是已经注册到Spring容器中的ViewResolver)
    	    返回给DispatcherServlet ；
    	7.ViewResolver 结合Model和View，来渲染视图
    	8.将渲染结果返回给客户端
```
## RPC协议
```
    hession协议
```
## VisualVM使用
```
    内部底层 使用JDK内部：JConsole
```
## spring定时器
```
    fixed-delay  个具有固定延迟的周期性触发器
    initial-delay  无论先前的执行需要多长时间，应执行该方法的频率
     fixed-rate  固定速率任务
    
```
## bbbb

