This example is a copy of : https://github.com/jOOQ/jOOQ/tree/master/jOOQ-examples/jOOQ-spring-boot-example 

- added a dependency to boot starter for aop

``` xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
```

- added an `@Aspect` class 

```java 
@Component
@Aspect
public class TestAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


   @AfterReturning("execution(* org.jooq.example.spring.service..*.*(..))")
   public void logAround(JoinPoint joinPoint) throws Throwable {
        log.info(">< Execution: {} ", joinPoint.getSignature().toShortString());
        log.debug("> Enter: {} with argument[s] = {}", joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs()));

    }
}
```

- Removed the bean creation and the usage of `ExecuteListenerProvider` because it was failing the context to startup :

`JooqSpringBootConfiguration`

``` java 
...
    //@Bean
    public ExecuteListenerProvider executeListenerProvider(ExceptionTranslator exceptionTranslator) {
        return new DefaultExecuteListenerProvider(exceptionTranslator);
    }

    @Bean
    public org.jooq.Configuration jooqConfig(ConnectionProvider connectionProvider,
                                             TransactionProvider transactionProvider, ExecuteListenerProvider executeListenerProvider) {

        return new DefaultConfiguration() //
                .derive(connectionProvider) //
                .derive(transactionProvider) //
                // .derive(executeListenerProvider) //
                .derive(SQLDialect.H2);
    }
 ...

```



**The application takes more thant 30s to start**


### Run it.

Please check out the complete jOOQ repository first, and use Maven to install the latest SNAPSHOT version of jOOQ:

```
$ mvn clean install
```


