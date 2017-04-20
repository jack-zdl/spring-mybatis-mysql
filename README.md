# spring-mybatis-mysql
深入研究spring-mybatis框架对mysql数据库的使用，学习spring+springmvc+mybatis框架学习和使用。
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
    insert语句 insert into tbl ()value()
    delete语句 DELETE FROM Person WHERE LastName = 'Wilson'
    update语句 update tbl set a = b where id =id
    select语句 SELECT * FROM Persons WHERE id=id
```
## 2mysql下的事物管理和mybatis的事物管理
```
    1 问题：当外部一个事物方法调用内部另二个事物方法时，这里出现了问题，
        外部事物不会滚。内部事物出现异常的回滚，不出现异常不会滚。
      解决：问题原因没有真正的出现异常，我在末尾finally{}返回数据，被认为没有发现异常，所以没有回滚。
      默认的食物传播行为 是
    2 问题：事件不回滚。
    问题原因没有真正的出现异常，我在末尾finally{}返回数据，被认为没有发现异常，所以没有回滚。
    --配置文件出错
    @Transactiona
     属性名                                           类型                               说明
        isolation         枚举org.springframework.transaction.annotation.Isolation的值    事务隔离级别
        noRollbackFor     Class<? extends Throwable>[]                                    一组异常类，遇到时不回滚。默认为{}
        noRollbackForClassName          Stirng[]                                        一组异常类名，遇到时不回滚，默认为{}
        propagation           枚举org.springframework.transaction.annotation.Propagation的值   事务传播行为
        readOnly              boolean                    事务读写性
        rollbackFor           Class<? extends Throwable>[]                        一组异常类，遇到时回滚
        rollbackForClassName    Stirng[]                          一组异常类名，遇到时回滚
        timeout               int                                超时时间，以秒为单位
        value                String                   可选的限定描述符，指定使用的事务管理器
```
## 3打印日志信息，将日志下载到某个位置等待查看
```
详情查看log4j.properties.可以控制输出级别，控制台显示，log文件输出。
```
## 4spring的异常处理对应http状态码
```

```
## 5http接口开发
```

```

